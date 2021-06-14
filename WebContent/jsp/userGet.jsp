<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="dao.*"%>
<%
	String unostr = request.getParameter("uno");
	if(unostr==""){ 
		out.print("");
	}else{
		int uno = Integer.parseInt(unostr);
		out.print((new User1DAO()).get(uno));
	}
%>