<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	
	String unostr = request.getParameter("uno");
	int uno = Integer.parseInt(unostr);
	
	FavoriteDAO fdao = new FavoriteDAO();
	out.print(fdao.getList(uno));
	
   
%>