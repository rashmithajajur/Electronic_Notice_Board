package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.AlertBean;
import com.beans.EventSearchBean;
import com.beans.EventsBean;
import com.beans.PersonBean;
import com.beans.ProfessorBean;
import com.beans.StudentBean;
import com.dbproject.dao.CategoryDao;
import com.dbproject.dao.DepartmentDao;
import com.dbproject.dao.EventDao;
import com.dbproject.dao.LoginDao;
import com.dbproject.dao.ProfessorDao;
import com.dbproject.dao.StudentDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

			String userid=request.getParameter("sjsuid");    
			String password=request.getParameter("password");   

			HttpSession session = request.getSession(false);  
			if(session!=null)  
				session.setAttribute("name", userid);  
			PersonBean bean = new PersonBean();
			List<String> categories =CategoryDao.getCategories();
			session.setAttribute("categories", categories);
			List<String> department =DepartmentDao.getDepartments();
			session.setAttribute("department", department);
			if(LoginDao.validate(userid, password, bean)){    
				RequestDispatcher rd=null;
				if(bean.getType()!=null && bean.getType().equalsIgnoreCase("admin")){
					
					EventSearchBean search = new EventSearchBean();
					Calendar cal = Calendar.getInstance(); 
					cal.add(Calendar.MONTH, 3);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					search.setDateFrom(sdf.parse(sdf.format(Calendar.getInstance().getTime())));
					search.setDateTo(sdf.parse(sdf.format(cal.getTime())));
					ArrayList<EventsBean> beans = EventDao.getEvents(search);
					EventSummary summary=new EventSummary();
					summary.setEventBeanList(beans);
					EventDao.summarizeEvents(summary, beans);
					session.setAttribute("eventSummary", summary);
					rd=request.getRequestDispatcher("adminindex.jsp");
					session.setAttribute("person", bean);
				}
				else if(bean.getType()!=null && bean.getType().equalsIgnoreCase("professor")){
					
					ProfessorBean professor = new ProfessorBean();
					professor = ProfessorDao.fetchProfessorInfo(bean.getId());
					professor.setProfessorId(bean.getId());
					professor.setFname(bean.getFname());
					professor.setLname(bean.getLname());
					professor.setAddress(bean.getAddress());
					professor.setType(bean.getType());
					professor.setUserid(bean.getUserid());
					EventSearchBean search = new EventSearchBean();
					Calendar cal = Calendar.getInstance(); 
					cal.add(Calendar.MONTH, 3);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					search.setDateFrom(sdf.parse(sdf.format(Calendar.getInstance().getTime())));
					search.setDateTo(sdf.parse(sdf.format(cal.getTime())));
					ArrayList<EventsBean> beans = EventDao.getEvents(search);
					session.setAttribute("person", professor);
					session.setAttribute("upcomingevents", beans);
					
					rd=request.getRequestDispatcher("professorindex.jsp");
					//TODO:: Implement the alerts code and write the call here to display alerts
					//session.setAttribute("person", bean);
				}else{
					StudentBean student = new StudentBean();
					student = StudentDao.fetchStudentInfo(bean.getId());
					student.setStudentId(bean.getId());
					student.setFname(bean.getFname());
					student.setLname(bean.getLname());
					student.setAddress(bean.getAddress());
					student.setType(bean.getType());
					student.setUserid(bean.getUserid());
					EventSearchBean search = new EventSearchBean();
					Calendar cal = Calendar.getInstance(); 
					cal.add(Calendar.MONTH, 3);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					search.setDateFrom(sdf.parse(sdf.format(Calendar.getInstance().getTime())));
					search.setDateTo(sdf.parse(sdf.format(cal.getTime())));
					ArrayList<EventsBean> beans = EventDao.getEvents(search);
					session.setAttribute("person", student);
					fetchSubscribedCategoryList(session);
					fetchAlerts(session, student);
					session.setAttribute("upcomingevents", beans);
					rd=request.getRequestDispatcher("studentindex.jsp");
					
				}
				session.setAttribute("username", bean.getFname());
				rd.forward(request,response);    
			}    
			else{    
				out.print("<p style=\"color:red\">Invalid username or password error</p>");    
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");    
				rd.include(request,response);    
			}    

			out.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void fetchAlerts(HttpSession session, StudentBean student) {
		List<AlertBean> alerts = EventDao.fetchAlerts(student);
		if(alerts!= null && alerts.size()>0){
			int alertCount=0;
			session.setAttribute("alerts", alerts);
			for(AlertBean alertBean:alerts){
				if(!alertBean.isViewed())
				{
					alertCount++;
				}
			}
			session.setAttribute("alertCount", alertCount);
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
