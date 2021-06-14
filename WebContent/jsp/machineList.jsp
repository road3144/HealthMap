<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="dao.*"%>
<%
	MachineDAO mdao = new MachineDAO();
	out.print(mdao.getList());
%>