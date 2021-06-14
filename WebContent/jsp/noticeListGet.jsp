<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
   NoticeDAO ndao = new NoticeDAO();
   String str = ndao.getList();
   out.print(str);
%>