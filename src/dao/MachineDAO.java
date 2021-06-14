package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class MachineDAO {


	public boolean insert(String jsonstr) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			synchronized(this) {
				//phase1 fno설정 하는 코
				String sql = "SELECT mno FROM machine ORDER BY mno DESC LIMIT 1"; 
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
		 		int max = (!rs.next()) ? 0 : rs.getInt("mno");
		 		stmt.close(); rs.close();
		 		
		 		JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject) parser.parse(jsonstr);
				jsonobj.put("mno", max + 1);
				
				
				//phase3
				sql = "INSERT INTO machine(mno, jsonstr) VALUES(?, ?)";
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
	
	
	public boolean delete(int mno) throws NamingException, SQLException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      try {
	         String sql = "DELETE FROM machine WHERE mno = ?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, mno);
	         
	         int count = stmt.executeUpdate();
	         return (count == 1) ? true : false;
	      } finally {
	         if(stmt != null) stmt.close(); 
	         if(conn != null) conn.close();
	      }
	}
	
	public String getList() throws SQLException, NamingException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT jsonstr FROM machine ORDER BY mno";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			
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
	
}
