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
        HashMap<Integer, Fleet> fleetinfo = dynafleet.getFleet();
        Fleet fleet = fleetinfo.get(fleetinfo.size()-1);
        System.out.println(gson.toJson(fleet));
        if(fleet == null || fleet.trucks == null || fleet.trucks.size()==0){
        	response.getWriter().println(INITIAL_FLEET_DATA);
        }else{
        	response.getWriter().println(gson.toJson(fleet));
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
//	private void fleetData(HttpServletRequest request, HttpServletResponse response) throws IOException{
//
//		/*GsonBuilder builder = new GsonBuilder();
//		Gson gson = builder.create();
//
//		response.getWriter().println(gson.toJson(dataToVisualise.get(1)));
//		
//		Fleet fleet = new Fleet();*/
//
//		Fleet fleet = new Fleet();
//		for(int i = 0; i<1; i++){
//			Truck truck = new Truck();
//			fleet.trucks.add(truck);
//		}
//		GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        System.out.println(fleet.trucks.size());
//        System.out.println(gson.toJson(fleet));
//        
//        if(fleet.trucks.size()==0){
//        	response.getWriter().println(INITIAL_FLEET_DATA);
//        }else{
//        	response.getWriter().println(gson.toJson(fleet));
//        }
//	}

	private String INITIAL_FLEET_DATA =  "{\"trucks\":[{\"id\":\"2000181905\",\"position\":{\"lat\":\"49.301456\",\"lng\":\"2.688319\"}},{\"id\":\"2000229431\",\"position\":{\"lat\":\"61.845862\",\"lng\":\"17.199411\"}},{\"id\":\"2000249643\",\"position\":{\"lat\":\"50.973086\",\"lng\":\"3.320974\"}},{\"id\":\"2000249643\",\"position\":{\"lat\":\"50.973174\",\"lng\":\"3.321363\"}},{\"id\":\"2000227941\",\"position\":{\"lat\":\"49.924752\",\"lng\":\"5.351397\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172679\",\"lng\":\"4.625732\"}},{\"id\":\"2000236871\",\"position\":{\"lat\":\"51.28158\",\"lng\":\"4.211161\"}},{\"id\":\"2000225529\",\"position\":{\"lat\":\"50.442387\",\"lng\":\"4.394558\"}},{\"id\":\"2000149017\",\"position\":{\"lat\":\"51.003311\",\"lng\":\"4.469057\"}},{\"id\":\"2000181905\",\"position\":{\"lat\":\"49.29741\",\"lng\":\"2.690007\"}},{\"id\":\"2000147896\",\"position\":{\"lat\":\"48.270944\",\"lng\":\"5.859693\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769399\",\"lng\":\"4.631292\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172678\",\"lng\":\"4.625726\"}},{\"id\":\"2000164703\",\"position\":{\"lat\":\"51.119373\",\"lng\":\"4.065799\"}},{\"id\":\"2000147896\",\"position\":{\"lat\":\"48.291273\",\"lng\":\"5.856457\"}},{\"id\":\"2000236871\",\"position\":{\"lat\":\"51.27461\",\"lng\":\"4.206452\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172682\",\"lng\":\"4.625722\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172676\",\"lng\":\"4.62573\"}},{\"id\":\"2000121720\",\"position\":{\"lat\":\"50.908496\",\"lng\":\"4.457613\"}},{\"id\":\"2000227941\",\"position\":{\"lat\":\"49.924757\",\"lng\":\"5.351429\"}},{\"id\":\"2000149017\",\"position\":{\"lat\":\"50.965269\",\"lng\":\"4.491259\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769398\",\"lng\":\"4.631281\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769385\",\"lng\":\"4.631264\"}},{\"id\":\"2000164703\",\"position\":{\"lat\":\"51.111153\",\"lng\":\"4.087865\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172735\",\"lng\":\"4.624345\"}},{\"id\":\"2000227941\",\"position\":{\"lat\":\"49.924767\",\"lng\":\"5.351523\"}},{\"id\":\"2000227941\",\"position\":{\"lat\":\"49.924726\",\"lng\":\"5.351454\"}},{\"id\":\"2000204271\",\"position\":{\"lat\":\"50.670131\",\"lng\":\"5.526326\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769432\",\"lng\":\"4.63133\"}},{\"id\":\"2000225529\",\"position\":{\"lat\":\"50.462617\",\"lng\":\"4.399405\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769432\",\"lng\":\"4.631317\"}},{\"id\":\"2000238279\",\"position\":{\"lat\":\"50.653862\",\"lng\":\"3.868897\"}},{\"id\":\"2000121720\",\"position\":{\"lat\":\"50.908659\",\"lng\":\"4.457653\"}},{\"id\":\"2000179301\",\"position\":{\"lat\":\"50.172713\",\"lng\":\"4.624314\"}},{\"id\":\"2000204271\",\"position\":{\"lat\":\"50.670132\",\"lng\":\"5.526123\"}},{\"id\":\"2000125828\",\"position\":{\"lat\":\"51.090718\",\"lng\":\"3.759857\"}},{\"id\":\"2000229431\",\"position\":{\"lat\":\"61.776834\",\"lng\":\"17.119668\"}},{\"id\":\"2000181905\",\"position\":{\"lat\":\"49.224042\",\"lng\":\"2.645075\"}},{\"id\":\"2000204271\",\"position\":{\"lat\":\"50.670146\",\"lng\":\"5.52611\"}},{\"id\":\"2000204271\",\"position\":{\"lat\":\"50.670146\",\"lng\":\"5.52611\"}},{\"id\":\"2000222352\",\"position\":{\"lat\":\"50.769489\",\"lng\":\"4.631335\"}},{\"id\":\"2000238279\",\"position\":{\"lat\":\"50.653862\",\"lng\":\"3.868884\"}},{\"id\":\"2000238279\",\"position\":{\"lat\":\"50.653868\",\"lng\":\"3.86886\"}},{\"id\":\"2000121720\",\"position\":{\"lat\":\"50.903621\",\"lng\":\"4.453047\"}},{\"id\":\"2000238279\",\"position\":{\"lat\":\"50.653915\",\"lng\":\"3.868855\"}},{\"id\":\"2000182906\",\"position\":{\"lat\":\"49.715396\",\"lng\":\"5.601662\"}},{\"id\":\"2000225529\",\"position\":{\"lat\":\"50.468224\",\"lng\":\"4.398281\"}},{\"id\":\"2000204271\",\"position\":{\"lat\":\"50.670136\",\"lng\":\"5.526138\"}},{\"id\":\"2000227941\",\"position\":{\"lat\":\"49.94064\",\"lng\":\"5.347109\"}},{\"id\":\"2000147896\",\"position\":{\"lat\":\"48.374851\",\"lng\":\"5.879225\"}}],\"numberInCheckPointRange\":32,\"colourInCheckPointRange\":\"GREEN\",\"numberOfSpeedingBreaches\":3,\"colourOfSpeedingBreaches\":\"RED\",\"numberOfHoursIdle\":12,\"colourOfHoursIdle\":\"YELLOW\",\"numberOfShocks\":321,\"colourOfShocks\":\"RED\",\"numberInAttentionZone\":12,\"colourofAttentionZone\":\"RED\",\"numberOfLockBreaches\":0,\"colourOfLockBreaches\":\"YELLOW\",\"numberIfUnusualAreas\":4,\"colourOfUnsualAraes\":\"GREEN\",\"numberOfHarshBrakes\":30,\"colourOfHarshBrakes\":\"RED\"}";
}
