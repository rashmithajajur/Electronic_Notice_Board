package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.PersonBean;
import com.beans.StudentBean;
import com.dbproject.dao.PersonDao;
import com.dbproject.dao.StudentDao;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			
			String address=request.getParameter("address");
			String lname=request.getParameter("studentlastname");
			String userid=request.getParameter("studentfirstname");
			HttpSession session = request.getSession();
			StudentBean personInSession = (StudentBean)session.getAttribute("person");
			String type="STUDENT";
			PersonBean person = new PersonBean();
			person.setAddress(address);
			person.setLname(lname);
			person.setType(type);
			person.setFname(userid);
			person.setId(personInSession.getStudentId());
			PersonDao.updatePerson(person);
			
			session.setAttribute("username", person.getFname());
			StudentBean bean = (StudentBean)session.getAttribute("person");
			bean.setAddress(address);
			bean.setLname(lname);
			bean.setType(type);
			bean.setFname(userid);
			bean.setId(personInSession.getStudentId());
			session.setAttribute("person", bean);
			RequestDispatcher rd=request.getRequestDispatcher("thankyouupdate.jsp");
			rd.forward(request,response);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
