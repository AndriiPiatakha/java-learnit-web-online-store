package com.itbulls.learnit.onlinestore.web.owasp.uca.solution;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecurePaymentServlet extends HttpServlet {

    private static final String[] ALLOWED_REDIRECT_DOMAINS = {"paymentgateway.com"};

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentData = request.getParameter("paymentData");
        String apiUrl = "https://paymentgateway.com/process";

        // Establish connection to payment gateway
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.getOutputStream().write(paymentData.getBytes("UTF-8"));

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
            String newLocation = connection.getHeaderField("Location");

            // Validate the redirect URL
            URL redirectUrl = new URL(newLocation);
            if (isAllowedRedirectDomain(redirectUrl)) {
                // Only follow the redirect if it's to a trusted domain
                HttpURLConnection redirectConnection = (HttpURLConnection) redirectUrl.openConnection();
                redirectConnection.setRequestMethod("POST");
                redirectConnection.setDoOutput(true);
                redirectConnection.getOutputStream().write(paymentData.getBytes("UTF-8"));

                // Read and process the redirect response
                BufferedReader in = new BufferedReader(new InputStreamReader(redirectConnection.getInputStream()));
                String inputLine;
                StringBuilder responseString = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseString.append(inputLine);
                }
                in.close();

                // Send the redirect response back to the client
                response.getWriter().write(responseString.toString());
            } else {
                // Handle untrusted redirect
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Redirect to an untrusted domain.");
            }
        } else {
            // Read and process the original response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder responseString = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                responseString.append(inputLine);
            }
            in.close();

            // Send the response back to the client
            response.getWriter().write(responseString.toString());
        }
    }

    // Helper method to check if the domain of the redirect URL is allowed
    private boolean isAllowedRedirectDomain(URL url) {
        for (String domain : ALLOWED_REDIRECT_DOMAINS) {
            if (url.getHost().endsWith(domain)) {
                return true;
            }
        }
        return false;
    }
}
