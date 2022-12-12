package com.ty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/wit")

public class Withdraw extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		String accountno=req.getParameter("ano");
		String money=req.getParameter("mo");
		double mon=Double.parseDouble(money);
		
		double val= 0.0;
		
		String qry="select Balance from bank.info where Accountno=?";
		String qry1="update bank.info set Balance=? where Accountno=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("step1");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			System.out.println("step 2");
			
			
			PreparedStatement pstmt= con.prepareStatement(qry);
			System.out.println("step 3");
			
			
			pstmt.setString(1, accountno);
			
			ResultSet rs=pstmt.executeQuery();
			PrintWriter out=resp.getWriter();
			 while(rs.next())
			 {
				  val=rs.getDouble(1);
				  if(val>mon)
				  {
					  val=val-mon;
					  System.out.println(val);
					  out.println("<html><body bgcolor='pink'><h1> your amount is Withdraw</h1></body></html>");
				  }else {
					  
						 out.println("<html><body bgcolor='pink'><h1> Insufficient balance</h1></body></html>");
				  }
				  
			 }
			 
			 
			 PreparedStatement pstmt1 = con.prepareStatement(qry1);
			 pstmt1.setDouble(1, val);
			 pstmt1.setString(2, accountno);
			 pstmt1.executeUpdate();
			 System.out.println("executed");
			 
			 
			 
			 System.out.println("executed all");
		} 

		
		catch (Exception e) {
			
			e.printStackTrace();
		}

		
		
	}

	
}