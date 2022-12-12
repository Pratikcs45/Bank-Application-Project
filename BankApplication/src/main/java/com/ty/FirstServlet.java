package com.ty;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/reg")

public class FirstServlet extends GenericServlet
{
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		
		String fname=req.getParameter("fn");
		String mname= req.getParameter("mn");
		String lname= req.getParameter("ln");
		String mno= req.getParameter("mob");
		int a=Integer.parseInt(mno);
		String uname= req.getParameter("un");
		String password= req.getParameter("pw");
		String cpassword= req.getParameter("cpw");
		double balance=0;
		String s1="KELP";
		
		Random r=new Random();
		int r1=r.nextInt(10000);
		
		String s2=s1+r1;
		
		
		
		String qry="insert into bank.info values(?,?,?,?,?,?,?,?)";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("step 1");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			System.out.println("step 2");
			
			PreparedStatement pstmt= con.prepareStatement(qry);
			System.out.println("step 3");
			
			pstmt.setString(1, s2);
			pstmt.setString(2, fname);
			pstmt.setString(3, mname);
			pstmt.setString(4, lname);
			pstmt.setInt(5, a);
			pstmt.setString(6, uname);
			pstmt.setString(7, password);
			pstmt.setDouble(8, balance);
			
			pstmt.executeUpdate();
			System.out.println("4th done");
			
			
		} 
		
		
		
		
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}
