package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.EventSearchBean;
import com.beans.EventsBean;
import com.beans.StudentBean;
import com.dbproject.dao.EventDao;
import com.dbproject.dao.StudentBookingDao;

/**
 * Servlet implementation class BookTicketsServlet
 */
@WebServlet("/BookTicketsServlet")
public class BookTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTicketsServlet() {
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
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		String eventId = request.getParameter("eventId");
		int seatRequested = Integer.parseInt((String)request.getParameter("noOfTic"));
		EventSearchBean search = new EventSearchBean();
		search.setEventId(Integer.parseInt(eventId));
		ArrayList<EventsBean> arrlBean = EventDao.getEvents(search);
		StudentBean studentbean = (StudentBean)session.getAttribute("person");
		EventsBean eventBean = new EventsBean();
		if(arrlBean.size()>0){
			eventBean=arrlBean.get(0);
			int availableSeats = eventBean.getTotalNoOfSeats()-eventBean.getBookedSeats();
			if(seatRequested>availableSeats){
				String errorMessage = "Number of seats are more than available seats!! Seats available for this event are "+availableSeats+" Please enter valid number of seats!!";
				session.setAttribute("errorMessage", errorMessage);
				rd=request.getRequestDispatcher("booktickets.jsp");
				rd.forward(request,response);
			}else{
				StudentBookingDao dao = new StudentBookingDao();
				dao.insertStudentBooking(studentbean.getStudentId(), Integer.parseInt(eventId), seatRequested);
				int bookedSeats = eventBean.getBookedSeats()+seatRequested;
				eventBean.setBookedSeats(bookedSeats);
				EventDao.updateEvents(eventBean);
				updateEventInSession(session, eventBean.getEventId(), bookedSeats);
				rd=request.getRequestDispatcher("thankyouinsert.jsp");
				rd.forward(request,response);
			}
			
		}else{
			String errorMessage = "Event was not selected appropriately! Please select the event and try again!!";
			session.setAttribute("errorMessage", errorMessage);
			rd=request.getRequestDispatcher("booktickets.jsp");
			rd.forward(request,response);
		}
		
	}

	private void updateEventInSession(HttpSession session, int eventId, int bookedSeats) {
		try{
			ArrayList<EventsBean> arrlbean = (ArrayList<EventsBean>)session.getAttribute("upcomingevents");
			if(arrlbean != null && arrlbean.size()>0){
				for(EventsBean bean:arrlbean){
					if(bean.getEventId()==eventId){
						bean.setBookedSeats(bookedSeats);
					}
				}
			}
			session.setAttribute("upcomingevents", arrlbean);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
