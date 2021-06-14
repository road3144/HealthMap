<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%

   request.setCharacterEncoding("utf-8");
	String gnostr = request.getParameter("gno");
   int gno = Integer.parseInt(gnostr);
   OwnDAO odao = new OwnDAO();
   String oList = odao.getList(gno);
   out.print(oList);
%>