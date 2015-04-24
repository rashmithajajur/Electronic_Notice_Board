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
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet("/ChangePwdServlet")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setContentType("text/html");    
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			session.removeAttribute("message");
			String oldpwd=request.getParameter("oldpwd");
			String newpwd1=request.getParameter("newpwd1");
			String newpwd2=request.getParameter("newpwd2");
			PersonBean person = (PersonBean)session.getAttribute("person");
			int flag=PersonDao.changePassword(person.getUserid(),oldpwd, newpwd1);
			if(flag==1){
				RequestDispatcher rd=request.getRequestDispatcher("thankyouupdate.jsp");
				rd.forward(request,response);  
			}else{
				RequestDispatcher rd=request.getRequestDispatcher("changepassword.jsp");
				session.setAttribute("message", "Incorrect password added in Old Password. Please try again!!");
				rd.forward(request,response);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
