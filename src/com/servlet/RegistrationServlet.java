package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.PersonBean;
import com.dbproject.dao.PersonDao;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");    
		PrintWriter out = response.getWriter();
		String userid=request.getParameter("sjsuid");    
		String password=request.getParameter("password");
		HttpSession session = request.getSession(false);  
		if(session!=null)  
			session.setAttribute("name", userid);
		if(PersonDao.updatePassword(password, userid)==1){
			out.print("<p style=\"color:white\">Successfully Registered! Click <a href='index.jsp'>here</a> to login</p>");
			RequestDispatcher rd=request.getRequestDispatcher("plainpage.jsp");    
			rd.include(request,response);
		}else{
			out.print("<p style=\"color:red\">SJSU ID is not registered in the system. Please contact office administrator</p>");    
			RequestDispatcher rd=request.getRequestDispatcher("plainpage.jsp");    
			rd.include(request,response);
		}
	}

}
