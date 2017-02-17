package com.deloitte.classes.datamodel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FleetData {
	public void loadTable(Connection connection) throws SQLException{
		DatabaseMetaData meta = connection.getMetaData();
        ResultSet rs = meta.getTables(null, null, "EMAIL", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            System.out.println(name);
            if (name.equals("REAL_TIME_DATA")) {
                return;
            }
        }
        
		String query = "CREATE TABLE \"REAL_TIME_DATA\" (\"TRUCK_ID\" VARCHAR(10),"+
		"\"TIMESTAMP\" LONGDATE CS_LONGDATE,"+
		"\"LONG\" VARCHAR(20),"+
		"\"LAT\" VARCHAR(20),"+
		"\"SPEED\" INTEGER CS_INT,"+
		"\"DIRECTION\" VARCHAR(20),"+
		"\"BREAKING\" VARCHAR(1),"+
		"\"SHOCK\" VARCHAR(1),"+
		"\"LOCK_STATUS\" VARCHAR(1),"+
		"\"MOTOR\" VARCHAR(1))";
		connection.prepareStatement(query).execute();
		System.out.println("test");
	}
}
