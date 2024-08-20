package isusedrawD_register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnection.Dbconnection;

/**
 * Servlet implementation class b2_IsusedrawD_register
 */
@WebServlet("/b2_IsusedrawD_register")
public class b2_IsusedrawD_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public b2_IsusedrawD_register() {
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
		    	 int Id = Integer.parseInt(request.getParameter("Id"));
		         String MName = request.getParameter("MName");
		         int Quantity = Integer.parseInt(request.getParameter("Quantity"));
		         String IssuerName = request.getParameter("IssuerN");
		         String IssuedDate = request.getParameter("IssuedD");
		         String IssuerTime = request.getParameter("IssuedT");
		         int CostU = Integer.parseInt(request.getParameter("CostU"));
		         
		         
		         
		         System.out.println("firstName");
		         PreparedStatement pstmt = con.prepareStatement("insert into issueRaw values (?,?,?,?,?,?,?,?)");
		         pstmt.setInt(1, Id);
		         pstmt.setString(2, MName);
		         pstmt.setInt(3, Quantity);
		         pstmt.setString(4, IssuerName);
		         pstmt.setString(5, IssuedDate);
		         pstmt.setString(6, IssuerTime);	         
		         pstmt.setInt(7, CostU);
		         pstmt.setString(8, "pending");
		         
		        
		         System.out.println("heyyy");
		         

		             int rowsInserted = pstmt.executeUpdate();
		             		         
		             
	//	             System.out.println("Insert successful!");		             
		             PreparedStatement pstmtFetch = con.prepareStatement("SELECT Quantity FROM rawMaterial WHERE Id=?");
		 	        pstmtFetch.setInt(1, Id);
		 	        ResultSet rs = pstmtFetch.executeQuery();

		 	        if (rs.next()) {
		 	            int oldQuantity = rs.getInt("Quantity");  // Use a different variable for current balance
		 	            rs.close();
		 	            pstmtFetch.close();

		 	            // Update balance and deposit amount
		 	            int newQuantity = oldQuantity - Quantity;
		 	            PreparedStatement pstmtUpdate = con.prepareStatement("UPDATE rawMaterial SET Quantity=? WHERE Id=?");
		 	            pstmtUpdate.setInt(1, newQuantity);
		 	            pstmtUpdate.setInt(2, Id);
		 	            int i= pstmtUpdate.executeUpdate();
		 	            
		 	            if (i > 0) {
				            
//				             System.out.println("Insert successful!");
		 	           response.sendRedirect("b2_issuesRawM.html");
		 	            }
		             
		         } else {
		             response.sendRedirect("error.html");
		         }

		     } catch (SQLException  e) {
		         e.printStackTrace();  // Log the exception details or provide a meaningful message to the user.
		         // You might want to redirect the user to an error page.
		         
		     }
		doGet(request, response);
	}

}
