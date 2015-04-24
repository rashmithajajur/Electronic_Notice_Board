package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
	public static List<String> getCategories()
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		List<String> result=new ArrayList<String>();
		try{
			String sql="SELECT * FROM category";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
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

	public static boolean insertCategory(String catName, String catDescription)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="INSERT INTO category(category_name,description) VALUES (?,?)";
			con=ConnectionPool.getConnectionFromPool();;
			ps=con.prepareStatement(sql);
			ps.setString(1, catName);
			ps.setString(2, catDescription);
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
		return result;
	}

	public static boolean deleteCategory(String catName)
	{

		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		boolean result=true;
		try{
			String sql="DELETE FROM category WHERE UPPER(category_name)=(?)";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, catName.toUpperCase());
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

	public static int getCategoryIdByName(String categoryName)
	{
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try{
			String sql="SELECT CATEGORYID FROM CATEGORY WHERE CATEGORY_NAME=?";
			con=ConnectionPool.getConnectionFromPool();
			ps=con.prepareStatement(sql);
			ps.setString(1, categoryName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("CATEGORYID");
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
