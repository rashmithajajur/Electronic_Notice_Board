package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.EventSearchBean;
import com.beans.EventsBean;
import com.dbproject.dao.CategoryDao;
import com.dbproject.dao.EventDao;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventServlet() {
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
			EventSearchBean search = new EventSearchBean();
			Calendar cal = Calendar.getInstance(); 
			HttpSession session=request.getSession();
			String eventName = request.getParameter("ename");
			String eventVenue = request.getParameter("place");
			String dateFrom = request.getParameter("eventdatefrom");
			String dateTo = request.getParameter("eventdateto");
			String category = request.getParameter("category");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(dateFrom!=null && dateFrom.length()>0)
				search.setDateFrom(sdf.parse(dateFrom));
			if(dateTo!=null && dateTo.length()>0)
				search.setDateTo(sdf.parse(dateTo));
			if(eventName!= null && eventName.length()>0)
				search.setEventName(eventName);
			if(eventVenue!=null && eventVenue.length()>0)
				search.setVenue(eventVenue);
			if(category!=null && !category.equalsIgnoreCase("Select")){
				int categoryId=CategoryDao.getCategoryIdByName(category);
				search.setCategoryId(categoryId);
				search.setCategoryName(category);
			}
			ArrayList<EventsBean> beans = EventDao.getEvents(search);
			session.setAttribute("eventfilter", beans);
			session.setAttribute("searchBean", search);
			RequestDispatcher rd=request.getRequestDispatcher("filteredeventlist.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
