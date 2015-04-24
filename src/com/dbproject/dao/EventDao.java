package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.beans.AlertBean;
import com.beans.EventSearchBean;
import com.beans.EventsBean;
import com.beans.StudentBean;
import com.beans.StudentCatBean;
import com.servlet.EventSummary;

public class EventDao {

	public static void insertEvents (EventsBean event){
		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("INSERT INTO EVENTS (NAME, DESCRIPTION, EVENT_DATE, VENUE, TOTAL_SEATS, BOOKED_SEATS, TICKET_PRICE, CATEGORYID, DEPARTMENTID) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)");  
			pst.setString(1, event.getEventName());  
			pst.setString(2, event.getDescription());
			pst.setTimestamp(3, new Timestamp(event.getEventDate().getTime()));
			pst.setString(4, event.getVenue());
			pst.setInt(5, event.getTotalNoOfSeats());
			pst.setInt(6, event.getBookedSeats());
			pst.setDouble(7, event.getTicketPrice());
			pst.setInt(8, event.getCategoryId());
			pst.setInt(9, event.getDepartmentId());

			result = pst.execute();  
			//insertAlerts(event);
		}catch (Exception e) {  
			System.out.println(e);  
		} finally {  
			if(conn!=null){
				ConnectionPool.addConnectionBackToPool(conn);
			}
			if (pst != null) {  
				try {  
					pst.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}

	public static void insertAlerts(EventsBean event) {
		PreparedStatement pst = null;  
		Connection conn=null;
		String str = "There is an event "+event.getEventName() +" added for category ";
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("INSERT INTO STUDENT_ALERTS (PERSON_ID, ALERT_DESCRIPTION, VIEWED) "
							+ " SELECT DISTINCT id, CONCAT(? ,c.category_name) alert_description, 'N'"
							+ " FROM person p, category c, student_category_mapping sc"
							+ " WHERE p.id=sc.s_id"
							+ " AND c.categoryId=sc.c_id"
							+ " AND c.categoryId=?");  
			pst.setString(1, str);  
			pst.setInt(2, event.getCategoryId());

			pst.execute();  
		}catch (Exception e) {  
			System.out.println(e);  
		} finally {  
			if(conn!=null){
				ConnectionPool.addConnectionBackToPool(conn);
			}
			if (pst != null) {  
				try {  
					pst.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}
		
	}

	public static void updateEvents (EventsBean event){
		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("UPDATE EVENTS SET NAME=?, DESCRIPTION=?, EVENT_DATE=?, VENUE=?, TOTAL_SEATS=?, BOOKED_SEATS=?, TICKET_PRICE=? "
							+ " WHERE EVENTID=?");  
			pst.setString(1, event.getEventName());  
			pst.setString(2, event.getDescription());
			pst.setTimestamp(3, new Timestamp(event.getEventDate().getTime()));
			pst.setString(4, event.getVenue());
			pst.setInt(5, event.getTotalNoOfSeats());
			pst.setInt(6, event.getBookedSeats());
			pst.setDouble(7, event.getTicketPrice());
			pst.setInt(8, event.getEventId());

			result = pst.execute();  
		}catch (Exception e) {  
			System.out.println(e);  
		} finally {  
			if(conn!=null){
				ConnectionPool.addConnectionBackToPool(conn);
			}
			if (pst != null) {  
				try {  
					pst.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}
	public static ArrayList<EventsBean> getEvents(EventSearchBean search) {

		ArrayList<EventsBean> result = new ArrayList<EventsBean>();
		Connection conn = null;
		String sql = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		EventsBean event=null;

		try {
			int index=1;
			boolean isAndIncluded=false;
			conn = ConnectionPool.getConnectionFromPool();
			sql="SELECT * FROM EVENTS";
			if(search==null){
				st = conn.prepareStatement(sql);
			}else{

				if(search.getDateFrom()!=null && search.getDateTo()!= null){
					sql = sql+" WHERE EVENT_DATE BETWEEN ? AND ?";
					isAndIncluded=true;
				}
				if(search.getCategoryId() != 0){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql =sql+ " CATEGORYID=?";
				}
				if(search.getDepartmentId() != 0){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql = sql+" DEPARTMENTID=?";
				}

				if(search.getEventName() != null){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql = sql+" upper(NAME) LIKE ?";
				}
				if(search.getDescription() != null){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql = sql+" upper(DESCRIPTION) LIKE ?";
				}
				if(search.getVenue() != null){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql = sql+" upper(VENUE) LIKE ?";
				}
				if(search.getEventId() != 0){
					if (isAndIncluded){
						sql=sql+" AND ";
					}else{
						sql=sql+ " WHERE ";
					}
					isAndIncluded=true;
					sql = sql+" EVENTID=?";
				}
				st = conn.prepareStatement(sql);
			}
			System.out.println("SQL ============== "+sql);

			if(sql.contains("EVENT_DATE")){
				st.setTimestamp(index++, new Timestamp(search.getDateFrom().getTime()));
				st.setTimestamp(index++, new Timestamp(search.getDateTo().getTime()));
			}
			System.out.println("Error here"+st.toString());
			if(sql.contains("CATEGORYID")){
				st.setInt(index++, search.getCategoryId());
			}
			if(sql.contains("DEPARTMENTID")){
				st.setInt(index++, search.getDepartmentId());
			}
			if(sql.contains("NAME")){
				st.setString(index++, "%"+search.getEventName().toUpperCase()+"%");
			}
			if(sql.contains("DESCRIPTION")){
				st.setString(index++, "%"+search.getDescription().toUpperCase()+"%");
			}
			if(sql.contains("VENUE")){
				st.setString(index++, "%"+search.getVenue().toUpperCase()+"%");
			}
			if(sql.contains("EVENTID")){
				st.setInt(index++, search.getEventId());
			}
			rs = st.executeQuery();

			while (rs.next()) 
			{
				event = new EventsBean();
				event.setEventName(rs.getString("NAME"));
				event.setVenue(rs.getString("VENUE"));
				event.setDescription(rs.getString("DESCRIPTION"));
				event.setEventDate(rs.getTimestamp("EVENT_DATE"));
				event.setBookedSeats(rs.getInt("BOOKED_SEATS"));
				event.setTotalNoOfSeats(rs.getInt("TOTAL_SEATS"));
				event.setTicketPrice(rs.getDouble("TICKET_PRICE"));
				event.setCategoryId(rs.getInt("CATEGORYID"));
				event.setDepartmentId(rs.getInt("DEPARTMENTID"));
				event.setEventId(rs.getInt("EVENTID"));
				result.add(event);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					ConnectionPool.addConnectionBackToPool(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public static void summarizeEvents(EventSummary summary, EventSearchBean search) {

		ArrayList<EventsBean> result = new ArrayList<EventsBean>();
		Connection conn = null;
		String sql = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		EventsBean event=null;

		try {
			conn = ConnectionPool.getConnectionFromPool();
			sql="SELECT SUM(BOOKED_SEATS) NO_OF_BOOKINGS, SUM(TICKET_PRICE*BOOKED_SEATS) REVENUE, "
					+ "SUM(TOTAL_SEATS) TOTAL_SEATS FROM EVENTS WHERE EVENT_DATE BETWEEN ? AND ?";
			st=conn.prepareStatement(sql);
			System.out.println("SQL ============== "+sql);

			st.setTimestamp(1, new Timestamp(search.getDateFrom().getTime()));
			st.setTimestamp(2, new Timestamp(search.getDateTo().getTime()));
			System.out.println("Error here"+st.toString());

			rs = st.executeQuery();

			if (rs.next()) 
			{
				summary.setTotalRevenue(rs.getLong("REVENUE"));
				summary.setTotalTicketsSold(rs.getLong("NO_OF_BOOKINGS"));
				summary.setTotalRevenue(rs.getLong("TOTAL_SEATS"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					ConnectionPool.addConnectionBackToPool(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


	}
	
	public static void summarizeEvents(EventSummary summary, ArrayList<EventsBean> bean) {
		double revenue=0;
		long totalSeats=0;
		long bookedSeats=0;
		for(EventsBean beaneve: bean){
			revenue=revenue+(beaneve.getTicketPrice()*beaneve.getBookedSeats());
			totalSeats=totalSeats+beaneve.getTotalNoOfSeats();
			bookedSeats=bookedSeats+beaneve.getBookedSeats();
		}
		summary.setTotalRevenue(revenue);
		summary.setTotalSeats(totalSeats);;
		summary.setTotalTicketsSold(bookedSeats);
	}
	
	public static boolean deletEvent(String eventName)
	{

		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="DELETE FROM EVENTS WHERE UPPER(NAME)=(?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, eventName.toUpperCase());
			ps.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					ConnectionPool.addConnectionBackToPool(con);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static List<AlertBean> fetchAlerts(StudentBean student) {

		List<AlertBean> catMap = new ArrayList<AlertBean>();
		
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="SELECT person_id, alert_description, viewed, alert_id "
					+ "FROM STUDENT_ALERTS "
					+ "WHERE person_id=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, student.getStudentId());
			rs=ps.executeQuery();
			while(rs.next())
			{
				AlertBean bean = new AlertBean();
				bean.setAlertDescription(rs.getString("ALERT_DESCRIPTION"));
				if(rs.getString("VIEWED").equalsIgnoreCase("N")){
					bean.setViewed(false);
				}else{
					bean.setViewed(true);
				}
				bean.setPersonid(rs.getInt("PERSON_ID"));
				bean.setAlertid(rs.getInt("ALERT_ID"));
				catMap.add(bean);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					ConnectionPool.addConnectionBackToPool(con);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return catMap;
	
		
	}
}
