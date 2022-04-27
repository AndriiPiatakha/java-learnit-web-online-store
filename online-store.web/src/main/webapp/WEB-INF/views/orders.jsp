<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <title>Management Orders</title>
      
      <shop:css-imports-main/>
   </head>
   <body>
      
      <shop:header/>
   
      <div class="product-page-main">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="prod-page-title">
                     <h2>Active Orders</h2>
                  </div>
               </div>
            </div>
            <div class="row">
             
               <shop:order-table/>
             
            </div>
         </div>
      </div>
      
     <shop:footer/>
     <shop:js-imports-main/>
   </body>
</html>