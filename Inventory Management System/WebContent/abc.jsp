<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import = "java.sql.*"%>
    <%@ page import="DBConnection.Dbconnection"%> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
    <meta name="author" content="AdminKit">
    <meta name="keywords"
        content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="shortcut icon" href="img/icons/icon-48x48.png" />

    <link rel="canonical" href="https://demo-basic.adminkit.io/ui-forms.html" />

    <title>View Charging Station</title>
    <style>
    .Vdata{
      
    }
    </style>

    <link href="css/app.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

<body>
    <div class="wrapper">
       <nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="index.html">
					<span class="align-middle">Inventory Data</span>
				</a>

				<ul class="sidebar-nav">
					<li class="sidebar-header">
						User
					</li>

					<li class="sidebar-item active">
						<a class="sidebar-link" href="">
							<i class="align-middle" data-feather="log-in"></i> <span class="align-middle">View Issued Material Data</span>
						</a>
					</li>

					

				</ul>
				

				
			</div>
		</nav>

        <div class="main">
            <nav class="navbar navbar-expand navbar-light navbar-bg">
                <a class="sidebar-toggle js-sidebar-toggle">
                    <i class="hamburger align-self-center"></i>
                </a>

                <div class="navbar-collapse collapse">
                    <ul class="navbar-nav navbar-align">

                        <li class="nav-item dropdown">
                            <a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#"
                                data-bs-toggle="dropdown">
                                <i class="align-middle" data-feather="settings"></i>
                            </a>

                            <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#"
                                data-bs-toggle="dropdown">
                                <img src="img/avatars/avatar.png" class="avatar img-fluid rounded me-1"
                                    alt="Charles Hall" /> <span class="text-dark">Sagar Beldar</span>
                            </a>
                           
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="content">

                <div class="container-fluid p-0">

                    <div class="mb-3">
                        <h1 class="h3 d-inline align-middle">Raw Material Requested Quotation :</h1>

                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">

                               
<% 
     Connection con=Dbconnection.connect();

 try{
	 
	 int id = Integer.parseInt(request.getParameter("id")); // Corrected parameter name to "id"
	    
	   
	    
	    System.out.println(id); // Corrected line

	  
	    PreparedStatement pstmt = con.prepareStatement("UPDATE issueRaw SET status = ? WHERE Id = ?");
	    pstmt.setString(1, "approved"); // Set status to "approved"
	    pstmt.setInt(2, id); // Set id
	    int i = pstmt.executeUpdate();
	    if (i > 0) {
	        System.out.println("Updated row count=" + i);
	        response.sendRedirect("b2_viewIssueData.jsp");
	    } else {
	        System.out.println("No rows updated.");
	    }
	    
 	PreparedStatement pstmt1 =con.prepareStatement("select * from issueRaw");
 	ResultSet rs=pstmt.executeQuery();
 	
 	%>
<h1 style="margin-top:20px;margin-bottom:20px"></h1>

<table style="margin-top:2px;margin-bottom:20px">
<tr style="margin-top:20px;margin-bottom:20px" class="Vdata">
<th >Raw ID</th>
<th>Material Name</th>
<th>Quantity</th>
<th>Issuer Name</th>
<th>Issuer Date</th>
<th> Issuer Time</th>
<th> Cost Per Unit</th>
<th> Delete</th>
<th> Status</th>
</tr>
<%while(rs.next()){ %>
<div>
<TR style="margin-top:2px;margin-bottom:20px">
<TD> <%=rs.getInt(1) %></TD>
<TD> <%=rs.getString(2) %></TD>
<TD> <%=rs.getInt(3) %></TD> 
<TD> <%=rs.getString(4) %></TD>
<TD> <%=rs.getString(5) %></TD>
<TD> <%=rs.getString(6) %></TD>
<TD> <%=rs.getInt(7) %></TD>

<Td> <a href="b2_issueDeleteRawM.jsp?id=<%=rs.getString(1)%>">delete</a></Td>
<Td> <a href="b2_issueApproval.jsp?id=<%=rs.getString(1)%>">Appprval</a></Td>
</TR>

<div>

<%}
}
catch(Exception e)
{
e.printStackTrace();
} %>
</table>


                            </div>
                        </div>
                    </div>

                </div>

            </main>

            <footer class="footer">
                <div class="container-fluid">
                    <div class="row text-muted">
                        <div class="col-6 text-start">
                            <p class="mb-0">
                                <a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>E-Vehicle
                                        </strong></a> &copy;
                            </p>
                        </div>
                        <div class="col-6 text-end">
                            <ul class="list-inline">
                                <li class="list-inline-item">
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank">Support</a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank">Help Center</a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank">Privacy</a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank">Terms</a>
                                </li>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="index.html">Log out</a>
                            </div>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>

    <script src="js/app.js"></script>

</body>

</html>