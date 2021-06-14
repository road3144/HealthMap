<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	String gnostr = request.getParameter("gno");
	int gno = Integer.parseInt(gnostr);
	String unostr = request.getParameter("uno");
	int uno = Integer.parseInt(unostr);
	
	FavoriteDAO fdao = new FavoriteDAO();
	if(fdao.exists(uno, gno)==false){
		out.print("NE");
	}else{
		out.print("EX");
	}
	
   
%>