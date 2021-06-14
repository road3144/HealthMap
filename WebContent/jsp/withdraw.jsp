<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="dao.*" %>
<%
	request.setCharacterEncoding("utf-8");

	String unostr = request.getParameter("uno");
	int uno = Integer.parseInt(unostr);
	if ((new User1DAO()).exists(uno)) {
		new User1DAO().delete(uno);
		out.print("OK");
	}
	else if (!new User1DAO().exists(uno)) {
		out.print("NA");
	}
	else {
		out.print("ER");
	}
%>