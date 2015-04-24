package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.PersonBean;

public class LoginDao {

	public static boolean validate(String name, String pass, PersonBean bean) {          
		boolean status = false;  
		PreparedStatement pst = null;  
		ResultSet rs = null;  
		Connection conn=null;
		
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("select * from person where userid=? and password=?");  
			pst.setString(1, name);  
			pst.setString(2, pass);  

			rs = pst.executeQuery();  
			status = rs.next();  
			if(status){
				bean.setFname(rs.getString("FNAME"));
				bean.setLname(rs.getString("LNAME"));
				bean.setType(rs.getString("TYPE"));
				bean.setAddress(rs.getString("ADDRESS"));
				bean.setId(rs.getInt("ID"));
				bean.setUserid(rs.getString("USERID"));
			}
		} catch (Exception e) {  
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
			if (rs != null) {  
				try {  
					rs.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
		return status;  
	}  
}  
