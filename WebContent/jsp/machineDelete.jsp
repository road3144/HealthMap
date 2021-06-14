<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%

   request.setCharacterEncoding("utf-8");
   String mnostr = request.getParameter("mno");
   int mno = Integer.parseInt(mnostr);
   MachineDAO mdao = new MachineDAO();
   if(mdao.delete(mno)){
      out.print("OK");
   }else{
      out.print("ER");
   }
%>