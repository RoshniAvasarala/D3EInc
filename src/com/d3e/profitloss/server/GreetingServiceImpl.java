package com.d3e.profitloss.server;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.d3e.profitloss.client.GreetingService;
import com.d3e.profitloss.entity.Expense;
import com.d3e.profitloss.entity.Invoice;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String ex_name, int ex_qty, double ex_price, String in_name, int in_qty, double in_price, String input) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		String jdbcURL = "jdbc:mysql://localhost:3306/profitloss";
		String username = "root";
		String password = "Roshnirao2301$";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to the database");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Expense.class).addAnnotatedClass(Invoice.class);
		SessionFactory sf = con.buildSessionFactory();
		Session s = sf.openSession();
		
		Double expense_total = ex_price * ex_qty;
		Double invoice_total = in_price * in_qty;
		
		in_name = escapeHtml(in_name);
		Expense expense = new Expense(ex_name, ex_qty, ex_price, expense_total);
		Invoice invoice = new Invoice(in_name, in_qty, in_price, invoice_total);
		
		Transaction tx = s.beginTransaction();
		s.save(expense);
		s.save(invoice);
		tx.commit();
		System.out.println("Saved!!");
		return in_name;
	}

	private String escapeHtml(String in_name) {
		// TODO Auto-generated method stub
		if (in_name == null) {
			return null;
		}
		return in_name.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		
	}

	

	
}
