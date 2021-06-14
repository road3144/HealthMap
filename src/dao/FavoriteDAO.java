package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class FavoriteDAO {

	public boolean insert(int uno, int gno) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			synchronized(this) {
				
				String sql = "INSERT INTO favorite(uno, gno) VALUES(?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, uno);
				stmt.setInt(2, gno);
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
		} finally {
			if(stmt != null) stmt.close(); 
			if(conn != null) conn.close();
		}
	}
	public boolean delete(int uno, int gno) throws NamingException, SQLException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      try {
	         String sql = "DELETE FROM favorite WHERE uno = ? AND gno =?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, uno);
	         stmt.setInt(2, gno);
	         
	         int count = stmt.executeUpdate();
	         return (count == 1) ? true : false;
	      } finally {
	         if(stmt != null) stmt.close(); 
	         if(conn != null) conn.close();
	      }
	}
	public boolean exists(int uno, int gno) throws NamingException, SQLException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	         String sql = "SELECT * FROM favorite WHERE uno = ? AND gno = ?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, uno);
	         stmt.setInt(2, gno);
	         rs = stmt.executeQuery();
	         
	         return rs.next();
	         
	      } finally {
	         if(rs != null) rs.close();
	         if(stmt != null) stmt.close(); 
	         if(conn != null)conn.close();
	      }
	   }
	//즐겨찾기한 짐리스트 조
	public String getList(int uno) throws SQLException, NamingException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	    	  String sql = "SELECT gym.jsonstr FROM favorite RIGHT OUTER JOIN gym on favorite.gno = gym.gno WHERE uno = ?";
	    	  stmt = conn.prepareStatement(sql);
	    	  stmt.setInt(1, uno);
	    	  rs = stmt.executeQuery();
	    	  String str = "["; int cnt = 0;
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
