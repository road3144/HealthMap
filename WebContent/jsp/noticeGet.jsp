<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	String nnostr = request.getParameter("nno");
	int nno = Integer.parseInt(nnostr);
   NoticeDAO ndao = new NoticeDAO();
   String str = ndao.get(nno);
   out.print(str);
%>