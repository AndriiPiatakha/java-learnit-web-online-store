<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags/shop"%>

<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <title>Product Listing Page</title>
      <shop:css-imports-main/>
   </head>
   <body>
      <shop:header/>
      <div class="furniture-box">
         <div class="container">
            <div class="row">
				<div class="furniture-main">
					<h2>${categoryName}</h2>
					<shop:plp-facet-search />
					<shop:plp-product-tiles />
				</div>
			</div>
         </div>
      </div>
     <shop:footer/>
     <shop:js-imports-main/>
     
      <script>
         $( function() {
         	$( "#slider-range" ).slider({
         		range: true,
         		min: 0,
         		max: 2000,
         		values: [ 158, 1230 ],
         		slide: function( event, ui ) {
         			$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
         		}
         	});
         	$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
         		" - $" + $( "#slider-range" ).slider( "values", 1 ) );
         } );
      </script>
      <script src="js/jquery.nicescroll.min.js"></script> 
      <script>
         $(document).ready(function() {
           $("#boxscroll").niceScroll({cursorborder:"",cursorcolor:"#ededed",boxzoom:true}); // First scrollable DIV
         });
      </script>
   </body>
</html>