<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
User1DAO udao = new User1DAO();
	
	String uid = request.getParameter("id");
	String upass = request.getParameter("ps");
	
	String code = udao.login(uid, upass);
	if (code == "NE") {
		out.print("NE"); 
	}
	else if (code == "PE") {
		out.print("PE");
	}
	else if (code == "AD") {
		out.print("AD");
	}
	else {
		out.print(code); 
	}
%>