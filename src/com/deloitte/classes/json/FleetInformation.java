package com.deloitte.classes.json;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.deloitte.classes.datamodel.Fleet;
import com.deloitte.classes.datamodel.Truck;
import com.deloitte.classes.soap.SoapDynafleet;
import com.deloitte.classes.soap.SoapSingleton;
import com.deloitte.soap.SoapReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class OverviewServlet
 */
public class FleetInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource db;
	private SoapReader sr;
	private Connection connection;
	
	private SoapSingleton dynafleet;
	

	//PreparedStatement fleetRequestOverall;
	
	@Override
	public void init() throws ServletException {
//		try {
//			InitialContext ctx = new InitialContext();
//			db = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
//			fleetRequestPreparation();
//		} catch (SQLException e) {
//			throw new ServletException(e);
//		} catch (NamingException e) {
//			throw new ServletException(e);
//		}
		dynafleet = SoapSingleton.getInstance();

		
		
		//TODO: inlezen van de data 

	}
	
//	private void fleetRequestPreparation() throws SQLException{
//		connection = db.getConnection();
//		new FleetData().loadTable(connection);
//        try {
//        	//fleetRequestOverall = connection.prepareStatement("select \"TRUCK_ID\",\"TIMESTAMP\",\"LONG\",\"LAT\",\"SPEED\",\"DIRECTION\",\"BREAKING\",\"SHOCK\",\"LOCK_STATUS\",\"MOTOR\" from \"REAL_TIME_DATA\" LIMIT 0,20");
//        } finally {}
//	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletPath());
		System.out.println(request.getPathInfo());

		System.out.println("aa");

		//fleetData(request, response);
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Fleet fleet = dynafleet.getFleet().get(0);
        System.out.println(gson.toJson(fleet));
		response.getWriter().println(gson.toJson(fleet));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void fleetData(HttpServletRequest request, HttpServletResponse response) throws IOException{

		/*GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		response.getWriter().println(gson.toJson(dataToVisualise.get(1)));
		
		Fleet fleet = new Fleet();*/

		Fleet fleet = new Fleet();
		for(int i = 0; i<1; i++){
			Truck truck = new Truck();
			fleet.trucks.add(truck);
		}
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(fleet));
		response.getWriter().println(gson.toJson(fleet));
	}

}
