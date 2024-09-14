package com.itbulls.learnit.onlinestore.web.owasp.urc.problem;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/urc-problem-reset-password")
public class UrcProblemPasswordResetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        // Assuming these services are injected or instantiated
        UserFacade userFacade = new DefaultUserFacade();
        

        User user = userFacade.getUserByEmail(email);
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid email");
            return;
        }

        // Generate a reset code
        String resetCode = generateResetCode();
//        user.setResetPasswordCode(resetCode);
//        user.setResetPasswordCodeExpiryDate(Instant.now().plus(15, ChronoUnit.MINUTES));
        userFacade.updateUser(user);

        // Send the code to user's email
//      NotificationService notificationService = new NotificationService();
//		notificationService.sendResetCode(email, resetCode);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Reset code sent to your email");
    }

    private String generateResetCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
}