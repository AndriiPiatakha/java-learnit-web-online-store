package com.itbulls.learnit.onlinestore.web.servlets.exceptions;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/error-handler")
public class ErrorHandlerServlet extends HttpServlet {
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      // Analyze the servlet exception       
      Throwable throwable = (Throwable)
      request.getAttribute("jakarta.servlet.error.exception");
      Integer statusCode = (Integer)
      request.getAttribute("jakarta.servlet.error.status_code");
      String servletName = (String)
      request.getAttribute("jakarta.servlet.error.servlet_name");
         
      if (servletName == null) {
         servletName = "Unknown";
      }
      String requestUri = (String)
      request.getAttribute("jakarta.servlet.error.request_uri");
      
      if (requestUri == null) {
         requestUri = "Unknown";
      }

      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Error/Exception Information";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         
      out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n");

      if (throwable == null && statusCode == null) {
         out.println("<h2>Error information is missing</h2>");
         out.println("Please return to the <a href=\"" + 
            response.encodeURL("http://localhost:8080/" + getServletContext().getContextPath()) + 
            "\">Home Page</a>.");
      }
      
      if (statusCode != null) {
         out.println("The status code : " + statusCode);
         out.println("<h2>Error information</h2>");
         out.println("Servlet Name : " + servletName + "</br></br>");
         out.println("The request URI: " + requestUri + "<br><br>");
         
      }
      if (throwable != null) {
    	  out.println("Exception Type : " + throwable.getClass( ).getName( ) + "</br></br>");
          out.println("The exception message: " + throwable.getMessage( ));
      }
      out.println("</body>");
      out.println("</html>");
   }
   
   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      doGet(request, response);
   }
}