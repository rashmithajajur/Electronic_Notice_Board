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
import com.beans.ProfessorBean;
import com.beans.StudentBean;
import com.dbproject.dao.PersonDao;

/**
 * Servlet implementation class ProfessorServlet
 */
@WebServlet("/ProfessorServlet")
public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorServlet() {
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
		try{
			response.setContentType("text/html");    
			PrintWriter out = response.getWriter();    
			
			String address=request.getParameter("address");
			String lname=request.getParameter("professorlname");
			String userid=request.getParameter("professorfname");
			HttpSession session = request.getSession();
			ProfessorBean personInSession = (ProfessorBean)session.getAttribute("person");
			String type="PROFESSOR";
			PersonBean person = new PersonBean();
			person.setAddress(address);
			person.setLname(lname);
			person.setType(type);
			person.setFname(userid);
			person.setId(personInSession.getProfessorId());
			PersonDao.updatePerson(person);
			
			session.setAttribute("username", person.getFname());
			ProfessorBean bean = (ProfessorBean)session.getAttribute("person");
			bean.setAddress(address);
			bean.setLname(lname);
			bean.setType(type);
			bean.setFname(userid);
			bean.setId(personInSession.getProfessorId());
			session.setAttribute("person", bean);
			RequestDispatcher rd=request.getRequestDispatcher("thankyouupdateprof.jsp");
			rd.forward(request,response);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
