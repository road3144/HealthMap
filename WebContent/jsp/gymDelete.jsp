<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	String gnostr = request.getParameter("gno");
	int gno = Integer.parseInt(gnostr);
	
	GymDAO gdao = new GymDAO();
	
	if(gdao.delete(gno)==false){
		out.print("ER");
	}else{
		out.print("OK");
	}
   
%>