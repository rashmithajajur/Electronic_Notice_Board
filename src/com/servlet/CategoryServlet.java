package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.StudentBean;
import com.beans.StudentCatBean;
import com.dbproject.dao.StudentDao;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
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
			String[] categories=request.getParameterValues("category");
			HttpSession session=request.getSession(false);
			StudentBean bean = (StudentBean)session.getAttribute("person");
			StudentDao dao=new StudentDao();
			System.out.println("Student Id:: "+bean.getStudentId());
			System.out.println("User Id:: "+bean.getUserid());
			dao.deleteSubscribedCategories(bean.getStudentId());
			for(String cat:categories){
				dao.studentCategorySubscribe(bean.getUserid(), cat);
			}
			fetchSubscribedCategoryList(session);
			RequestDispatcher rd=request.getRequestDispatcher("thankyouupdate.jsp");    
			rd.include(request,response); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void fetchSubscribedCategoryList(HttpSession session) {
		StudentBean bean = (StudentBean)session.getAttribute("person");
		StudentDao dao=new StudentDao();
		HashMap<String, Boolean> categoryMap = new HashMap<String, Boolean>();
		List<String> nonSubscribed = dao.fetchCategories(bean.getUserid(), "NotSubscribed");
		List<String> subscribed = dao.fetchCategories(bean.getUserid(), "subscribed");
		for(String val:nonSubscribed){
			categoryMap.put(val, false);
		}
		for(String val:subscribed){
			categoryMap.put(val, true);
		}
		session.setAttribute("categoryMap", categoryMap);
	}
}
