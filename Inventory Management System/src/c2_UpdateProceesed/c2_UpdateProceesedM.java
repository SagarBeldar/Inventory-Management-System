package c2_UpdateProceesed;

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
 * Servlet implementation class c2_UpdateProceesedM
 */
@WebServlet("/c2_UpdateProceesedM")
public class c2_UpdateProceesedM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c2_UpdateProceesedM() {
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
		try {
			     int id = Integer.parseInt(request.getParameter("id"));
		         int Quantity = Integer.parseInt(request.getParameter("Quantity"));
		         
	        // Fetch current balance
	        PreparedStatement pstmtFetch = con.prepareStatement("SELECT Quantity FROM processedmaterial WHERE Id=?");
	        pstmtFetch.setInt(1, id);
	        ResultSet rs = pstmtFetch.executeQuery();

	        if (rs.next()) {
	            int oldQuantity = rs.getInt("Quantity");  // Use a different variable for current balance
	            rs.close();
	            pstmtFetch.close();

	            // Update balance and deposit amount
	            int newQuantity = oldQuantity + Quantity;
	            PreparedStatement pstmtUpdate = con.prepareStatement("UPDATE processedmaterial SET Quantity=? WHERE Id=?");
	            pstmtUpdate.setInt(1, newQuantity);
	            pstmtUpdate.setInt(2, id);
	            int i = pstmtUpdate.executeUpdate();
	            
	            System.out.println("Row updated: " + i);
	            response.sendRedirect("c2_processedData.jsp");
	            pstmtUpdate.close();
	        } else {
	            System.out.println("Qunatity not add....");
	        }

	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		doGet(request, response);
	}

}
