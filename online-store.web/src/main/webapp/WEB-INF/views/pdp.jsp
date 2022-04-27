<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <title>Product Details Page</title>
      
      <shop:css-imports-main/>
   </head>
   <body>
      
      <shop:header/>
   
      <div class="product-page-main">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="prod-page-title">
                     <h2>${product.productName}</h2>
                  </div>
               </div>
            </div>
            <div class="row">
             
               <div class="col-md-7 col-sm-8">
                  <div class="md-prod-page">
                     <div class="md-prod-page-in">
                        <div class="page-preview">
                           <div class="preview">
                              <div class="preview-pic tab-content">
                                 <div class="tab-pane active" id="pic-1"><img src="images/product/${product.imgName}" alt="#" /></div>
                              </div>
                           </div>
                        </div>
                        
                     </div>
                     <div class="description-box">
                        <div class="dex-a">
                           <h4>Description</h4>
                           <p>${product.description}</p>
                        </div>
                        
                     </div>
                  </div>
                  
               </div>
               
               <div class="col-md-5 col-sm-16">
                  <div class="price-box-right">
                     <h4>Price</h4>
                     <h3>$${product.price}</h3>
                     <c:if test="${not empty loggedInUser}">
                     	<a href="checkout?id=${product.id}">Buy</a>
                     </c:if>
                     <c:if test="${empty loggedInUser}">
                     	<a href="signin">Buy</a>
                     </c:if>
                     <h5>${orderStatus}</h5>
                     <c:remove var="orderStatus"/>
                     
                  </div>
               </div>
               
            </div>
         </div>
      </div>
      
     <shop:footer/>
     <shop:js-imports-main/>
   </body>
</html>