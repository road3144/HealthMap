package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class OwnDAO {
	public boolean insert(int gno, int mno) throws NamingException, SQLException, ParseException {
		Connection conn = ConnectionPool.get();
		PreparedStatement stmt = null;
		try {
			synchronized(this) {
				
				String sql = "INSERT INTO own(gno, mno) VALUES(?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, gno);
				stmt.setInt(2, mno);
				
				int count = stmt.executeUpdate();
				return (count == 1) ? true : false;
			}
		} finally {
			if(stmt != null) stmt.close(); 
			if(conn != null) conn.close();
		}
	}
	public boolean delete(int gno, int mno) throws NamingException, SQLException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      try {
	         String sql = "DELETE FROM own WHERE gno = ? AND mno =?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, gno);
	         stmt.setInt(2, mno);
	         
	         int count = stmt.executeUpdate();
	         return (count == 1) ? true : false;
	      } finally {
	         if(stmt != null) stmt.close(); 
	         if(conn != null) conn.close();
	      }
	}
	// 짐이 가지고 있는 기구리스트 조회
	
	public String getList(int gno) throws SQLException, NamingException {
        Connection conn = ConnectionPool.get();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           String sql = "SELECT machine.jsonstr FROM own RIGHT OUTER JOIN machine ON own.mno = machine.mno WHERE gno = ?";
           stmt = conn.prepareStatement(sql);
           stmt.setInt(1, gno);
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
