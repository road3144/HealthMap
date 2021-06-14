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

public class User1DAO {
   public boolean insert(String jsonstr) throws NamingException, SQLException, ParseException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
    	 synchronized(this) {
    		 String sql = "SELECT uno FROM user ORDER BY uno DESC LIMIT 1";
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
             
             int max = (!rs.next()) ? 0 : rs.getInt("uno");
             stmt.close();
             
             JSONObject jsonobj = (JSONObject)(new JSONParser()).parse(jsonstr);
             jsonobj.put("uno", max+1);
             sql = "INSERT INTO user(uno, id, jsonstr) VALUES(?,?,?)";
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, max + 1);
             stmt.setString(2, jsonobj.get("id").toString());
             stmt.setString(3, jsonobj.toJSONString());
             int count = stmt.executeUpdate();
             return (count == 1) ? true : false;
    	 }
      } finally {
         if (stmt != null)
            stmt.close();
         if (conn != null)
            conn.close();
      }
   }
   public boolean update(int uno, String jsonstr) throws NamingException, SQLException, ParseException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	    	 synchronized(this) {
	    		 String sql = "UPDATE user SET jsonstr = ? WHERE uno = ?";
	             stmt = conn.prepareStatement(sql);
	             stmt.setString(1, jsonstr);
	             stmt.setInt(2, uno);
	             int count = stmt.executeUpdate();
	             return (count == 1) ? true : false;
	    	 }
	      } finally {
	         if (stmt != null)
	            stmt.close();
	         if (conn != null)
	            conn.close();
	      }
	   }
   public boolean exists(int uno) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         String sql = "SELECT uno FROM user WHERE uno = ?";
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, uno);
         
         rs = stmt.executeQuery();
         
         return rs.next();
         
      } finally {
         if(rs != null) rs.close();
         if(stmt != null) stmt.close(); 
         if(conn != null)conn.close();
      }
   }

   public boolean delete(int uno) throws NamingException, SQLException {
         Connection conn = ConnectionPool.get();
         PreparedStatement stmt = null;
         try {
            String sql = "DELETE FROM user WHERE uno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, uno);
            
            int count = stmt.executeUpdate();
            return (count == 1) ? true : false;
         } finally {
            if(stmt != null) stmt.close(); 
            if(conn != null) conn.close();
         }
      }
   public String login(String uid, String upass) throws NamingException, SQLException, ParseException { 
        Connection conn = ConnectionPool.get();;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         try {
            String sql = "SELECT jsonstr FROM user WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            rs = stmt.executeQuery();
            if (!rs.next()) return "NE";
            String jsonstr = rs.getString("jsonstr");
            JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr);
            String pass = obj.get("password").toString();
            if (!upass.equals(pass)) return "PE";
            if(uid.equals("kim@abc.com") && upass.equals("111"))return "AD";
            return obj.get("uno").toString();
         } finally {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close(); 
            if(conn != null)conn.close();
         }
      }
   
   public String getList() throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         String sql = "SELECT jsonstr FROM user";
         stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         String str = "[";
         int cnt = 0;
         while (rs.next()) {
            if (cnt++ > 0)
               str += ", ";
            str += rs.getString("jsonstr");
         }
         return str + "]";
      } finally {
         if (rs != null)
            rs.close();
         if (stmt != null)
            stmt.close();
         if (conn != null)
            conn.close();
      }
   }
   
   public String get(int uno) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         String sql = "SELECT jsonstr FROM user WHERE uno = ?";
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, uno);
         
         rs = stmt.executeQuery();
         
         return rs.next() ? rs.getString("jsonstr") : "{}";
         
      } finally {
         if (rs != null) rs.close();
         if (stmt != null) stmt.close(); 
         if (conn != null) conn.close();
      }
   }
}