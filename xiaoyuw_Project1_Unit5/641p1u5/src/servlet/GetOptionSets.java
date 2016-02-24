/*
 * Andrew ID: xiaoyuw
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.SelectCarOption;
import exception.AutoException;
import model.Automobile;

/**
 * Servlet implementation class GetOptionSets
 *  Use the class and method in Unit 4 to get the selected model and print
 * out the optionset and options in a Form.
 */
@WebServlet("/GetOptionSets")
public class GetOptionSets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOptionSets() {
		super();
	}

	/**
	 * Use the class and method in Unit 4 to get the selected model and print
	 * out the optionset and options in a Form.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String model = request.getParameter("model");

		SelectCarOption sco = new SelectCarOption();
		sco.setChoice(model);
		Automobile at = sco.select();
		// save the automobile as an singleton in this session. Shared by all
		// request.
		request.getSession().setAttribute("model", at);

		String[] optsets = at.getOptsets();
		String[] opts = null;

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Basic Car Choice</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Basic Car Choice</h1>");
		out.println("<form method=\"get\" action=\"Redirect\" enctype=\"multipart/form-data\">");
		out.println("<table BORDER=1 >");
		out.println("<tr>");
		out.println("<td>" + "Make/Model:" + "</td>");
		out.println("<td>" + at.getName() + "</td>");
		out.println("</tr>");

		// for loop to list optionsets and options
		for (String optset : optsets) {
			try {
				opts = at.getOpts(optset);
			} catch (AutoException e) {
				e.printStackTrace();
			}

			out.println("<tr>");
			out.println("<td>" + optset + ":" + "</td>");
			out.println("<td>");
			out.println("<select name=\"" + optset + "\">");
			for (String opt : opts) {
				out.println("<option value=\"" + opt + "\">" + opt + "</option>");
			}
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");

		}
		out.println("</table>");
		// submit button
		out.println("<input type=\"submit\" value=\"Done\">");
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
