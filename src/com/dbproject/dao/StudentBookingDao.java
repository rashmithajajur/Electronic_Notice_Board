package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.StudentBean;
import com.beans.StudentCatBean;

public class StudentBookingDao {

	public void studentCategorySubscribe(String userid, String categoryName){
		
		int catId = CategoryDao.getCategoryIdByName(categoryName);
		int id = PersonDao.fetchPersonId(userid);
		//deleteSubscribedCategories(id);
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="INSERT INTO STUDENT_CATEGORY_MAPPING(s_id, c_id) VALUES (?,?)";
			con=ConnectionPool.getConnectionFromPool();;
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, catId);
			ps.execute();
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
		
	}
	
	public List<String> fetchCategories(String userId, String type){
		List<String> unsubCat = new ArrayList<String>();
		
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		List<String> result=new ArrayList<String>();
		try{
			String sql; 
			if(type.equals("NotSubscribed")){
				sql="SELECT DISTINCT CATEGORY_NAME FROM CATEGORY cat WHERE CATEGORY_NAME "
						+ "NOT IN (SELECT DISTINCT CATEGORY_NAME FROM CATEGORY cat WHERE CATEGORYID "
						+ "IN (SELECT C_ID FROM STUDENT_CATEGORY_MAPPING WHERE S_ID IN "
						+ "(SELECT DISTINCT S_ID FROM PERSON WHERE USERID=?)))";
			}else{
				sql="SELECT DISTINCT CATEGORY_NAME FROM CATEGORY cat WHERE CATEGORYID "
						+ "IN (SELECT C_ID FROM STUDENT_CATEGORY_MAPPING WHERE S_ID IN "
						+ "(SELECT DISTINCT S_ID FROM PERSON WHERE USERID=?))";
			}
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				result.add(rs.getString("category_name"));
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
		
		return result;
	}
	
	public void deleteSubscribedCategories(int studentid){
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="DELETE FROM student_category_mapping WHERE s_id=(?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, studentid);
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
	}
	
	public List<StudentCatBean> fetchStudentCatMapping(String userid){
		List<StudentCatBean> catMap = new ArrayList<StudentCatBean>();
		
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="SELECT cat.CATEGORY_NAME,map.s_cat_id,map.c_id,map.s_id,p.userId "
					+ "FROM CATEGORY cat,STUDENT_CATEGORY_MAPPING map, PERSON p"
					+ "WHERE cat.categoryId=map.c_id"
					+ "AND map.s_id=p.id"
					+ "AND p.userId=?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			while(rs.next())
			{
				StudentCatBean bean = new StudentCatBean();
				bean.setCategoryId(rs.getInt("C_ID"));
				bean.setCategoryName(rs.getString("CATEGORY_NAME"));
				bean.setStudentCategoryId(rs.getInt("S_CAT_ID"));
				bean.setStudentId(rs.getInt("S_ID"));
				bean.setUserId(rs.getString("USERID"));
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

	public static StudentBean fetchStudentInfo(int id) {
		StudentBean bean = new StudentBean();
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="SELECT S.S_ID, S.DEPTID, S.MAJOR, D.DEPTNAME "
					+ "FROM STUDENT S, DEPARTMENT D WHERE S.DEPTID=D.DEPTID AND"
					+ " S_ID=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				bean.setDeptId(rs.getInt("DEPTID"));
				bean.setDepartmentName(rs.getString("DEPTNAME"));
				bean.setMajor(rs.getString("MAJOR"));
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
		
		return bean;
	}

	public void insertStudentBooking(int studentId, int eventId,
			int seatRequested) {
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="INSERT INTO STUDENT_EVENT_BOOKING(s_id, eventId, no_of_tickets) VALUES (?,?,?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setInt(2, eventId);
			ps.setInt(3, seatRequested);
			ps.execute();
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
		
	}
}
