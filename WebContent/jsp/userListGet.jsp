<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
  User1DAO udao = new User1DAO();
   String str = udao.getList();
   out.print(str);
%>