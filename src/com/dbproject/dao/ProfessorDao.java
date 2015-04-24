package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.ProfessorBean;
import com.beans.StudentBean;
import com.beans.VisingHoursBean;

public class ProfessorDao {

	public void insertVisitingHours(VisingHoursBean bean){

		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="INSERT INTO OFFICE_HOURS(p_id, weekday, timings) VALUES (?,?)";
			con=ConnectionPool.getConnectionFromPool();;
			ps=con.prepareStatement(sql);
			ps.setInt(1, bean.getProfessorId());
			ps.setString(2, bean.getDay());
			ps.setString(3,bean.getTime());
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

	public void updateVisitingHours(VisingHoursBean bean){

		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="UPDATE OFFICE_HOURS SET p_id=?, weekday=?, timings=? WHERE office_hour_id=?";
			con=ConnectionPool.getConnectionFromPool();;
			ps=con.prepareStatement(sql);
			ps.setInt(1, bean.getProfessorId());
			ps.setString(2, bean.getDay());
			ps.setString(3,bean.getTime());
			ps.setInt(4, bean.getVistingHourId());
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
	
	public boolean deleteVisitingHours(int visitingHourId)
	{

			ResultSet rs=null;
			Connection con=null;
			PreparedStatement ps=null;
			boolean result=true;
			try{
				String sql="DELETE FROM OFFICE_HOURS WHERE office_hour_id=(?)";
				con=ConnectionPool.getConnectionFromPool();
				ps=con.prepareStatement(sql);
				ps.setInt(1, visitingHourId);
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

	public static ProfessorBean fetchProfessorInfo(int id) {
		ProfessorBean bean = new ProfessorBean();
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			String sql="SELECT S.P_ID, S.DEPTID, S.SUBJECTS, D.DEPTNAME "
					+ "FROM PROFESSOR S, DEPARTMENT D WHERE S.DEPTID=D.DEPTID AND"
					+ " S.P_ID=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				bean.setDepartmentId(rs.getInt("DEPTID"));
				bean.setDepartmentName(rs.getString("DEPTNAME"));
				bean.setSubjects(rs.getString("SUBJECTS"));
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
}
