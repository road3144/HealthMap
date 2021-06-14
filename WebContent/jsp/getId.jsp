<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>

<% 
   Object uid = session.getAttribute("id");
   String id = (String)uid;
   out.print(id);
%>