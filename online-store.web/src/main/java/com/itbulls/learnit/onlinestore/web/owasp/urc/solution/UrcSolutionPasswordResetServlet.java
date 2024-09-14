package com.itbulls.learnit.onlinestore.web.owasp.urc.solution;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/urc-solution-reset-password")
public class UrcSolutionPasswordResetServlet extends HttpServlet {

    private static final int MAX_REQUESTS = 5; // Max requests per time window
    private static final int TIME_WINDOW_IN_MINUTES = 30; // Time window in minutes
    private static final Map<String, RateLimitInfo> rateLimitMap = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        // Check rate limit
        String clientIdentifier = email; // You can use email as a client identifier for rate limiting
        RateLimitInfo info = rateLimitMap.getOrDefault(clientIdentifier, new RateLimitInfo());

        if (info.isExpired() || info.getCount() < MAX_REQUESTS) {
            if (info.isExpired()) {
                info.reset(TIME_WINDOW_IN_MINUTES);
            }
            info.increment();
            rateLimitMap.put(clientIdentifier, info);

            // Assuming these services are injected or instantiated
            UserFacade userService = new DefaultUserFacade();
            User user = userService.getUserByEmail(email);
            if (user == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid email");
                return;
            }

            // Generate a reset code
            String resetCode = generateResetCode();
            userService.updateUser(user);

            // Send the code to user's email
            // NotificationService notificationService = new NotificationService();
            // notificationService.sendResetCode(email, resetCode);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Reset code sent to your email");

        } else {
            resp.setContentType("text/plain");
            resp.getWriter().write("Rate limit exceeded. Try again later.");
            resp.setStatus(429); // Too Many Requests
        }
    }

    private String generateResetCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    private static class RateLimitInfo {
        private Instant expiry;
        private int count;

        void reset(int minutes) {
            this.expiry = Instant.now().plus(minutes, ChronoUnit.MINUTES);
            this.count = 0;
        }

        boolean isExpired() {
            return Instant.now().isAfter(expiry);
        }

        void increment() {
            count++;
        }

        int getCount() {
            return count;
        }
    }
}
