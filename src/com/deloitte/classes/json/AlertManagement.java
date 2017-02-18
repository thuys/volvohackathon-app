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
		
		setTruckData(truck, request.getPathInfo().substring(1));

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
		response.getWriter().println(gson.toJson(truck));
	}
	
	private void setTruckData(Truck truck, String nr) {
		
		if(Integer.parseInt(nr) % 3 == 1) {
			
// route https://www.google.be/maps/dir/38.7097735,-9.00675/40.5273495,-7.2239721/42.9204735,-2.6641959/43.5400947,1.3959146/45.8570317,1.2827131/48.79741,2.4441275/''/''/51.9625026,7.546241/53.5844734,10.0188848/@47.3242924,-3.4517978,5.46z/data=!4m18!4m17!1m0!1m0!1m0!1m0!1m0!1m0!1m3!2m2!1d4.3240643!2d50.9000354!1m3!2m2!1d5.4905033!2d51.404935!1m0!1m0!3e0
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
						truck.information.driverScore = 81;
						truck.information.assetScore = 23;
						truck.information.geoScore = 3;
						truck.information.propabilityOfDamage = 0.02;
						
/*		if(Integer.parseInt(nr) % 3 == 1) {
			
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
			truck.information.driverScore = 81;
			truck.information.assetScore = 23;
			truck.information.geoScore = 3;
			truck.information.propabilityOfDamage = 0.02; */
			
		} else if (Integer.parseInt(nr) % 3 == 2) {
			
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
			truck.information.driverScore = 61;
			truck.information.assetScore = 76;
			truck.information.geoScore = 52;
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
			truck.information.driverScore = 14;
			truck.information.assetScore = 83;
			truck.information.geoScore = 47;
			truck.information.propabilityOfDamage = 0.63;
		}
	}

}
