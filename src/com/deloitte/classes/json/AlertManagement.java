package com.deloitte.classes.json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloitte.classes.datamodel.CheckPoint;
import com.deloitte.classes.datamodel.ColourCode;
import com.deloitte.classes.datamodel.Fleet;
import com.deloitte.classes.datamodel.Position;
import com.deloitte.classes.datamodel.Truck;
import com.deloitte.classes.datamodel.TruckInformation;
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
		try {
			truckData(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void truckData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Truck truck = new Truck();
		
		setTruckData(truck, request.getPathInfo());

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
		response.getWriter().println(gson.toJson(truck));
	}
	
	private void setTruckData(Truck truck, String nr) {
		
		if(nr == "1") {
			
// route https://www.google.be/maps/dir/38.7097735,-9.00675/40.5273495,-7.2239721/42.9204735,-2.6641959/43.5400947,1.3959146/43.4704927,5.6673244/44.4301904,8.8469059/45.6235089,12.441684/47.7968704,13.0776452/@27.581962,-25.3644474,3z/data=!4m2!4m1!3e0			
			truck.id = "217394";
			truck.status = ColourCode.RED;
			
			truck.position.lat = "43.357202";
			truck.position.lng = "-0.455891";
			
			truck.information.checkPoints.add(new CheckPoint(new Position("38.7097735", "-9.00675"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("40.5273495", "-7.2239721"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("42.9204735", "-2.6641959"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("43.5400947", "1.3959146"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("43.4704927", "5.6673244"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("44.4301904", "8.8469059"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("45.6235089", "12.441684"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("47.7968704", "13.0776452"), false));
			
			truck.information.isInCheckPoint = false;
			truck.information.driverScore = 8;
			truck.information.assetScore = 2;
			truck.information.geoScore = 0;
			truck.information.propabilityOfDamage = 0.02;
			
		} else if (nr == "2") {
			
// route https://www.google.be/maps/dir/51.2776014,4.3610318/49.2855436,3.9802494/47.3523456,5.1625687/44.9780056,4.8839635/43.3104101,5.3656424/@43.3115612,5.3644312,15.33z/data=!4m2!4m1!3e0
			truck.id = "196045";
			truck.status = ColourCode.RED;
			
			truck.position.lat = "49.220876";
			truck.position.lng = "3.975877";
			
			truck.information.checkPoints.add(new CheckPoint(new Position("51.2776014", "4.3610318"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("49.2855436", "3.9802494"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("47.3523456", "5.1625687"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("44.9780056", "4.8839635"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("43.3104101", "5.3656424"), false));

			truck.information.isInCheckPoint = true;
			truck.information.driverScore = 6;
			truck.information.assetScore = 7;
			truck.information.geoScore = 5;
			truck.information.propabilityOfDamage = 0.44;
			
		} else {
			
// route https://www.google.be/maps/dir/55.703833,9.5524943/54.7513272,9.3733692/53.5138073,9.9155953/51.298352,9.5626016/49.20913,10.2301796/47.5550047,10.6564199/46.0898586,11.1021334/44.4893836,11.2506364/41.9625546,12.5135679/@42.2728313,11.1705644,8.33z/data=!4m2!4m1!3e0			
			truck.id = "301852";
			truck.status = ColourCode.RED;
			
			truck.position.lat = "47.971990";
			truck.position.lng = "10.167967";
			
			truck.information.checkPoints.add(new CheckPoint(new Position("55.703833", "9.5524943"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("54.7513272", "9.3733692"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("53.5138073", "9.9155953"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("51.298352", "9.5626016"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("49.20913", "10.2301796"), true));
			truck.information.checkPoints.add(new CheckPoint(new Position("47.5550047", "10.6564199"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("46.0898586", "11.1021334"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("44.4893836", "11.2506364"), false));
			truck.information.checkPoints.add(new CheckPoint(new Position("41.9625546", "12.5135679"), false));
			
			truck.information.isInCheckPoint = false;
			truck.information.driverScore = 1;
			truck.information.assetScore = 8;
			truck.information.geoScore = 4;
			truck.information.propabilityOfDamage = 0.63;
		}
	}

}
