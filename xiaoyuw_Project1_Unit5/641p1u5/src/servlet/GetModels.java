/*
 * Andrew ID: xiaoyuw
 */
package servlet;

import client.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetModels
 * Use the class and method in Unit 4 to show available models by interacting with the server.
 * Then the Form is created for user(browser).
 */
@WebServlet("/GetModels")
public class GetModels extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetModels() {
		super();
	}

	/**
	 * Use the class and method in Unit 4 to show available models by interacting with the server.
	 * Then the Form is created for user(browser).
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		SelectCarOption sco=new SelectCarOption();
		String models=sco.showCars();
		String cars[]=models.split("\t");
		
		 out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Car Model Configuration</title>");
		    out.println("</head>");
		    
		    out.println("<body>");
		    out.println("<h1>Car Model Configuration</h1>");
		    out.println("<form method=\"get\" action=\"GetOptionSets\" enctype=\"multipart/form-data\">");
		    //for loop to list car radio
		    for(int i=1;i<cars.length;i++){
		    	
		    	out.println("<br> <input type=\"radio\" name=\"model\" value=\""+cars[i]+"\"> "+cars[i]);
		    }
		    //submit button
		    out.println("<br> <br> <input type=\"submit\" value=\"Submit\">");
		    out.println("</form>");
		    out.println("</body>");
		    out.println("</html>");
		
	}

	/**
	 * Identical to doGet()
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
