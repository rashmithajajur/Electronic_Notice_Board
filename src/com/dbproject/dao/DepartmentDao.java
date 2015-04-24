package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.ProfessorBean;
import com.beans.StudentBean;

public class DepartmentDao {

	public static List<String> getDepartments()
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		List<String> result=new ArrayList<String>();
		try{
			String sql="SELECT * FROM department";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				result.add(rs.getString("deptName"));
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
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}


	public static boolean insertDept(String depName, String description)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="INSERT INTO department(deptName, description) VALUES (?, ?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, depName);
			ps.setString(2, description);
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
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static boolean deleteDept(String depName)
	{

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = true;
		try {
			String sql = "DELETE FROM department WHERE UPPER(deptName)=(?)";
			con = ConnectionPool.getConnectionFromPool();
			ps = con.prepareStatement(sql);
			ps.setString(1, depName.toUpperCase());
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	public static boolean assignDeptToStudent(StudentBean student)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="INSERT INTO STUDENT(s_id,major,deptid) values (?,?,?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, student.getStudentId());
			ps.setString(2, student.getMajor());
			ps.setInt(3, student.getDeptId());
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
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static boolean assignDeptToProfessor(ProfessorBean professor)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="INSERT INTO professor(p_id,subjects,deptid) values (?,?,?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setInt(1, professor.getProfessorId());
			ps.setString(2, professor.getSubjects());
			ps.setInt(3, professor.getDepartmentId());
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
					ConnectionPool.addConnectionBackToPool(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}


	public static int fetchIdByName(String departmentName) {
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try{
			String sql="SELECT DEPTID FROM DEPARTMENT WHERE DEPTNAME=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, departmentName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("DEPTID");
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
	
	public static int getDepartmentIdByName(String departmentName)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try{
			String sql="SELECT DEPTID FROM DEPARTMENT WHERE DEPTNAME=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, departmentName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("DEPTID");
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
}
