package com.deloitte.classes.soap;

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

import com.deloitte.jdbc.*;
import com.deloitte.soap.*;
import com.sap.security.core.server.csi.IXSSEncoder;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementation class OverviewServlet
 */
public class OverviewServlet extends HttpServlet {
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
    public OverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<h1>EMAIL TABLE OVERVIEW!</h1>");
		try {
			sr.RunSoap();
			FillTable(response);
			//appendAddForm(response);
		} catch (Exception e) {
			response.getWriter().println(
					"Persistence operation failed with reason: "
							+ e.getMessage());
		}
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
