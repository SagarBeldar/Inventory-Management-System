<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.sql.*"%>
    <%@ page import="DBConnection.Dbconnection"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User Status</title>
</head>
<body>
<%
try {
    int id = Integer.parseInt(request.getParameter("id")); // Corrected parameter name to "id"
    
   
    
    System.out.println(id); // Corrected line

    Connection con = Dbconnection.connect();
    PreparedStatement pstmt = con.prepareStatement("UPDATE issueprocessed SET status = ? WHERE Id = ?");
    pstmt.setString(1, "approved"); // Set status to "approved"
    pstmt.setInt(2, id); // Set id
    int i = pstmt.executeUpdate();
    if (i > 0) {
        System.out.println("Updated row count=" + i);
        response.sendRedirect("c2_viewProcessedData.jsp");
    } else {
        System.out.println("No rows updated.");
    }
} catch (NumberFormatException e) {
    e.printStackTrace();
    // Handle NumberFormatException, for example, by redirecting to an error page
    response.sendRedirect("error.jsp");
} catch (SQLException e) {
    e.printStackTrace();
    // Handle SQLException, for example, by redirecting to an error page
    response.sendRedirect("error.jsp");
}
%>
</body>
</html>
