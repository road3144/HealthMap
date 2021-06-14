<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%

   request.setCharacterEncoding("utf-8");
   String gnostr = request.getParameter("gno");
   int gno = Integer.parseInt(gnostr);
   String mnostr = request.getParameter("mno");
   int mno = Integer.parseInt(mnostr);
   
   OwnDAO odao = new OwnDAO();
   if(odao.insert(gno,mno)){
      out.print("OK");
   }else{
      out.print("ER");
   }
%>