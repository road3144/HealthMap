<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
String nnostr = request.getParameter("nno");

NoticeDAO ndao = new NoticeDAO();
if(ndao.delete(nnostr)==false){
	out.print("ER");
}else{
	out.print("OK");
}
  
%>