<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "java.sql.*"%>
    <%@ page import="DBConnection.Dbconnection"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
            String email = request.getParameter("Useremail");
            String password = request.getParameter("upwd");
            System.out.println(email);
  
Connection con=Dbconnection.connect ();

try {
    // Use "pending" as default status and update to "approve"
    PreparedStatement pstmt = con.prepareStatement("UPDATE manager SET Password =? WHERE Email =?");
    pstmt.setString(1, password);
    pstmt.setString(2, email); // Set status to "approve"
    // Set id
    int i = pstmt.executeUpdate();
    if (i > 0) {
        System.out.println("Updated row count=" + i);
        response.sendRedirect("D2_changePass.html");
    } else {
        System.out.println("No rows updated.");
    }
} catch (Exception e) {
    e.printStackTrace();
}
%>
</body>
</html>