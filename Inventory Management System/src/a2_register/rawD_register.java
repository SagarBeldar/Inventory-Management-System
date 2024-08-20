package a2_register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnection.Dbconnection;

/**
 * Servlet implementation class rawD_register
 */
@WebServlet("/rawD_register")
public class rawD_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rawD_register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = Dbconnection.connect();
		
		 
		   System.out.println("heyyy");
		     try {
		    	 int Id = Integer.parseInt(request.getParameter("mid"));
		         String Name = request.getParameter("Name");
		         int Quantity = Integer.parseInt(request.getParameter("Quantity"));
		         String Unit = request.getParameter("Unit");
		         int CostU = Integer.parseInt(request.getParameter("CostU"));
		         
		         
		         System.out.println("firstName");
		         PreparedStatement pstmt = con.prepareStatement("insert into rawmaterial values (?,?,?,?,?)");
		         pstmt.setInt(1, Id);
		         pstmt.setString(2, Name);
		         pstmt.setInt(3, Quantity);
		         pstmt.setString(4, Unit);
		         pstmt.setInt(5, CostU);
		         
		   
		        
		         System.out.println("heyyy");
		         

		         int rowsInserted = pstmt.executeUpdate();

		         if (rowsInserted > 0) {
		             response.sendRedirect("a2_Raw_M_Data.html");
//		             System.out.println("Insert successful!");
		         } else {
		             response.sendRedirect("error.html");
		         }

		     } catch (SQLException  e) {
		         e.printStackTrace();  // Log the exception details or provide a meaningful message to the user.
		         // You might want to redirect the user to an error page.
		         
		     } 
		     System.out.println("heyyy");
		doGet(request, response);
	}

}
