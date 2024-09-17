package com.itbulls.learnit.onlinestore.web.owasp.uca.problem;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class VulnerablePaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentData = request.getParameter("paymentData");
        String apiUrl = "https://paymentgateway.com/process";

        // Vulnerable code: blindly following redirects
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.getOutputStream().write(paymentData.getBytes("UTF-8"));

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
            String newLocation = connection.getHeaderField("Location");
            // Vulnerability: blindly following redirection
            URL redirectUrl = new URL(newLocation);
            HttpURLConnection redirectConnection = (HttpURLConnection) redirectUrl.openConnection();
            redirectConnection.setRequestMethod("POST");
            redirectConnection.setDoOutput(true);
            redirectConnection.getOutputStream().write(paymentData.getBytes("UTF-8"));
        }

        // Read and process the response
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