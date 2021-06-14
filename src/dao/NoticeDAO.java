package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import util.ConnectionPool;

public class NoticeDAO {
	public boolean insert(String jsonstr) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
				//phase1
				String sql = "SELECT nno FROM notice ORDER BY nno DESC LIMIT 1"; 
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
		 		int max = (!rs.next()) ? 0 : rs.getInt("nno");
		 		stmt.close(); rs.close();
		 		
		 		JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);
				jsonobj.put("nno", max + 1);
				
				//phase3
				sql = "INSERT INTO notice(nno, jsonstr) VALUES(?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, max +1);
				stmt.setString(2, jsonobj.toJSONString());
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
		} finally {
			if(stmt != null) stmt.close(); 
			if(conn != null) conn.close();
		}
	}
			
	public boolean delete(String nno) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      try {
	         String sql = "DELETE FROM notice WHERE nno = ?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, nno);
	         
	         int count = stmt.executeUpdate();
	         return (count == 1) ? true : false;
	      } finally {
	         if(stmt != null) stmt.close(); 
	         if(conn != null) conn.close();
	      }
	}
	
	public String getList() throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT jsonstr FROM notice ORDER BY nno";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			JSONArray feeds = new JSONArray();
			
			String str = "[";
			int cnt = 0;
			while(rs.next()) {
				if (cnt++ > 0) str += ", ";
				str += rs.getString("jsonstr"); 
			}
			return str + "]";
		} finally {
			if(rs != null) rs.close(); 
			if(stmt != null) stmt.close(); 
			if(conn != null) conn.close();
		}
	}
	public String get(int nno) throws NamingException, SQLException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT jsonstr FROM notice WHERE nno = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,nno);
			rs = stmt.executeQuery();
			
			
			String str = "";
			while(rs.next()) {
				str += rs.getString("jsonstr"); 
			}
			return str;
		} finally {
			if(rs != null) rs.close(); 
			if(stmt != null) stmt.close(); 
			if(conn != null) conn.close();
		}
	}
		
}
