<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	GymDAO gdao = new GymDAO();	
	String str = gdao.getList();
	out.print(str);
%>