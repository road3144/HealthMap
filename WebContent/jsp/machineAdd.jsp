<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%

   request.setCharacterEncoding("utf-8");
   String jsonstr = request.getParameter("jsonstr");
   MachineDAO mdao = new MachineDAO();
   if(mdao.insert(jsonstr)){
      out.print("OK");
   }else{
      out.print("ER");
   }
%>