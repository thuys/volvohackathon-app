package com.deloitte.classes.json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.deloitte.classes.datamodel.Route;
import com.deloitte.classes.datamodel.Truck;
import com.deloitte.jdbc.Database;
import com.deloitte.jdbc.Row;
import com.deloitte.soap.SoapReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.security.core.server.csi.IXSSEncoder;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementation class OverviewServlet
 */
public class JSonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Database db;
	private SoapReader sr;
	
	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
			db = new Database(ds);
			sr = new SoapReader(db);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Truck truck = new Truck();
		truck.id = "aa";

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(truck));
		response.getWriter().println(gson.toJson(truck));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void FillTable(HttpServletResponse response)throws SQLException, IOException {
		List<Row> resultList = db.Read();
		response.getWriter().println(
                  "<p><table border=\"1\"><tr><th colspan=\"5\">"
                  + resultList.size() + " entries in the Database</th></tr>" +
                  (resultList.isEmpty()
                  ? "<tr><td colspan=\"5\">Database is empty</td></tr>"
                  : "<tr><th>ID</th><th>ResponseText</th><th>ResponseCode</th><th>LastMailServer</th><th>GoodEmail</th></tr>"));

		IXSSEncoder xssEncoder = XSSEncoder.getInstance();
		for (Row r : resultList) {
	        response.getWriter().println(
	                    "<tr><td>" + xssEncoder.encodeHTML(r.getId())
						+ "</td><td>"+ xssEncoder.encodeHTML(r.getResponseText())
						+ "</td><td>"+ xssEncoder.encodeHTML(r.getResponseCode())
						+ "</td><td>"+ xssEncoder.encodeHTML(r.getLastMailServer())
						+ "</td><td>" + r.getGoodEmail() + "</td></tr>");
		}
		response.getWriter().println("</table></p>");
}

}
