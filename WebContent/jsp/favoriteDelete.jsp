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
	if(fdao.delete(uno, gno)==false){
		out.print("ER");
	}else{
		out.print("OK");
	}
   
%>