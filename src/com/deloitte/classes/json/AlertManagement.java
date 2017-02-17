package com.deloitte.classes.json;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloitte.classes.datamodel.CheckPoint;
import com.deloitte.classes.datamodel.Fleet;
import com.deloitte.classes.datamodel.Truck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class OverviewServlet
 */
public class AlertManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//PreparedStatement fleetRequestOverall;
	
	@Override
	public void init() throws ServletException {
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletPath());
		System.out.println(request.getPathInfo());
		truckData(request, response);
//		try {
//			truckData(request, response);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void truckData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Truck truck = new Truck();
		truck.information.checkPoints.add(new CheckPoint());

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
		response.getWriter().println(gson.toJson(truck));
	}

}
