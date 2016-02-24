/*
 * Andrew ID: xiaoyuw
 */
package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.AutoException;
import model.Automobile;

/**
 * Servlet implementation class Redirect.
 * Get request and set the attribute, then redirect the request to JSP.
 */
@WebServlet("/Redirect")
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Redirect() {
		super();
	}

	/**
	 * get request and set the attribute, then redirect the request to JSP
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Automobile at = (Automobile) request.getSession().getAttribute("model");
		Enumeration<String> pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String optset = (String) pNames.nextElement();
			String opt = request.getParameter(optset);
			try {
				at.setOptionChoice(optset, opt);
			} catch (AutoException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("model", at);
		
 
		String target="result.jsp";		
		RequestDispatcher dispatcher = request.getRequestDispatcher(target);
		dispatcher.forward(request, response);

	}

	/**
	 * Identical to doGet
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
