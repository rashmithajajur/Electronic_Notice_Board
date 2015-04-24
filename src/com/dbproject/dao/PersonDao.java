package com.dbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.PersonBean;

public class PersonDao {

	public static void insertPerson(PersonBean person){
		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("INSERT INTO PERSON (FNAME, LNAME, ADDRESS, USERID, PASSWORD, TYPE) "
							+ "VALUES (?,?,?,?,?,?)");  
			pst.setString(1, person.getFname());  
			pst.setString(2, person.getLname());
			pst.setString(3, person.getAddress());
			pst.setString(4, person.getUserid());
			pst.setString(5, person.getPassword());
			pst.setString(6, person.getType());

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
	
	public static void updatePerson(PersonBean person){
		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			pst = conn  
					.prepareStatement("UPDATE PERSON SET FNAME=?, LNAME=?, ADDRESS=?, TYPE=? "
							+ " WHERE ID=?");  
			pst.setString(1, person.getFname());  
			pst.setString(2, person.getLname());
			pst.setString(3, person.getAddress());
			pst.setString(4, person.getType());
			pst.setInt(5, person.getId());

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
	
	public static boolean deletePerson(String userid)
	{

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = true;
		try {
			String sql = "DELETE FROM PERSON WHERE userid=(?)";
			con = ConnectionPool.getConnectionFromPool();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
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
	
	public static int fetchPersonId(String userid){
		int personId=0;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = true;
		
		try {
			String sql = "SELECT ID FROM PERSON WHERE userid=?";
			con = ConnectionPool.getConnectionFromPool();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if(rs.next()){
				personId = rs.getInt("ID");
			}
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
		
		return personId;
	}
	
	public PersonBean getPersonInfo(String userid){
		PersonBean person=null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = true;
		
		try {
			String sql = "SELECT * FROM PERSON WHERE userid=(?)";
			con = ConnectionPool.getConnectionFromPool();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if(rs.next()){
				person=new PersonBean();
				person.setId(rs.getInt("ID"));
				person.setFname(rs.getString("FNAME"));
				person.setLname(rs.getString("LNAME"));
				person.setAddress(rs.getString("ADDRESS"));
				person.setUserid(rs.getString("USERID"));
				person.setType(rs.getString("TYPE"));
			}
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
		
		return person;
	}
	
	public static int changePassword(String userid, String oldpassword, String newPassword){

		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			PreparedStatement pst2 =conn.prepareStatement("SELECT COUNT(*) FROM PERSON WHERE USERID=? AND PASSWORD=?");
			pst2.setString(1, userid);
			pst2.setString(2, oldpassword);
			ResultSet rs = pst2.executeQuery();
			int count =0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			if(count==1){
				pst = conn  
						.prepareStatement("UPDATE PERSON SET PASSWORD=? WHERE USERID=? AND PASSWORD=?");  
				pst.setString(1, newPassword);  
				pst.setString(2, userid);
				pst.setString(3, oldpassword);
				pst.executeUpdate();
				return 1;
			}else{
				return 0;
			}
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
		return 0;
	}
	
	public static int updatePassword(String password, String sjsuid){
		PreparedStatement pst = null;  
		Connection conn=null;
		boolean result = false;
		try {  
			conn=ConnectionPool.getConnectionFromPool();
			PreparedStatement pst2 =conn.prepareStatement("SELECT COUNT(*) FROM PERSON WHERE USERID=?");
			pst2.setString(1, sjsuid);
			ResultSet rs = pst2.executeQuery();
			int count =0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			if(count==1){
				pst = conn  
						.prepareStatement("UPDATE PERSON SET PASSWORD=? "
								+ " WHERE USERID=?");  
				pst.setString(1, password);  
				pst.setString(2, sjsuid);

				result = pst.execute();
				pst2.close();
				rs.close();
				return 1;
			}else{
				return 0;
			}
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
		return 0;
	}
	
}
