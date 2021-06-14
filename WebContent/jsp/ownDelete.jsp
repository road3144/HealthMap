<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	String gnostr = request.getParameter("gno");
	int gno = Integer.parseInt(gnostr);
	String mnostr = request.getParameter("mno");
	int mno = Integer.parseInt(mnostr);
	
	OwnDAO odao = new OwnDAO();
	
	if(odao.delete(gno, mno)==false){
		out.print("ER");
	}else{
		out.print("OK");
	}
   
%>