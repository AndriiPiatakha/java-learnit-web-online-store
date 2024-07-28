package com.itbulls.learnit.onlinestore.web.owasp.ba.solution;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;

import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@WebServlet("/auth-servlet")
public class SecureAuthenticationServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();

	private static final int MAX_REQUESTS_PER_MINUTE = 5; // Set rate limit
	private static final ConcurrentHashMap<String, RequestData> requestCounts = new ConcurrentHashMap<>();

	private static class RequestData {
		private int count;
		private long timestamp;

		public RequestData(int count, long timestamp) {
			this.count = count;
			this.timestamp = timestamp;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String clientIp = req.getRemoteAddr();

		if (!isAllowed(clientIp)) {
			resp.setStatus(429); // 429 Status Code is for Too Many Requests
			resp.getWriter().write("{\"error\": \"Too many requests\"}");
			return;
		}

		String userEmail = req.getParameter("email");
		User user = userFacade.getUserByEmail(userEmail);

		// TODO NEVER Store passwords in DB in non encrypted state!
		if (user != null && MessageDigest.isEqual(user.getPassword().getBytes(), req.getParameter("password").getBytes())) {
			// Generate JWT token
			String token = JwtUtils.generateToken(userEmail);
			resp.setContentType("application/json");
			resp.getWriter().write("{\"token\": \"" + token + "\"}");
			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private boolean isAllowed(String clientIp) {
		long currentTimeMillis = System.currentTimeMillis();
		RequestData requestData = requestCounts.get(clientIp);

		if (requestData == null) {
			requestData = new RequestData(1, currentTimeMillis);
			requestCounts.put(clientIp, requestData);
			return true;
		}

		if (currentTimeMillis - requestData.getTimestamp() > TimeUnit.MINUTES.toMillis(1)) {
			requestData.setCount(1);
			requestData.setTimestamp(currentTimeMillis);
			return true;
		}

		if (requestData.getCount() < MAX_REQUESTS_PER_MINUTE) {
			requestData.setCount(requestData.getCount() + 1);
			return true;
		}

		return false;
	}
}
