package com.deloitte.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

public class Database {

    private DataSource dataSource;

	public Database(DataSource source) throws SQLException{
		this.dataSource = source;
		CheckTable();
	}
	
	public List<Row> Read()throws SQLException{
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID, RESPONSETEXT, RESPONSECODE, LASTMAILSERVER, GOODEMAIL FROM EMAIL");
        	//PreparedStatement pstmt = connection.prepareStatement("SELECT ID, FIRSTNAME, LASTNAME FROM PERSONS");

        	ResultSet rs = pstmt.executeQuery();
            ArrayList<Row> list = new ArrayList<Row>();
            while (rs.next()) {
            	String id = rs.getString(1);
                String ResponseText = rs.getString(2);
                String ResponseCode = rs.getString(3);
                String LastMailServer = rs.getString(4);
                String GoodEmail = rs.getString(5);
                Row r = new Row(ResponseText, ResponseCode, LastMailServer, GoodEmail);
                r.setId(id);
                list.add(r);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
	}
	
	public void Write(Row r) throws SQLException {
		Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO EMAIL (ID, ResponseText, ResponseCode, LastMailServer, GoodEmail) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, r.getResponseText());
            pstmt.setString(3, r.getResponseCode());
            pstmt.setString(4, r.getLastMailServer());
            pstmt.setString(5, r.getGoodEmail());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }		
	}
	
	private void CheckTable() throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            if (!existsTable(connection)) {
                createTable(connection);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
	
    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "EMAIL", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            System.out.println(name);
            if (name.equals("EMAIL")) {
                return true;
            }
        }
        return false;
    }
    
    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE EMAIL "
                        + "(ID VARCHAR(255) PRIMARY KEY, "
                        + "ResponseText VARCHAR (255),"
                        + "ResponseCode VARCHAR (255),"
                        + "LastMailServer VARCHAR (255),"
                        + "GoodEmail VARCHAR (255))");
        pstmt.executeUpdate();
    }

}


