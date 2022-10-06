package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import jdbc.Persona;

public class TestConnection {

	@Test
	public void test() {
		//fail("Not yet implemented");
		//assertTrue( 2 == 2);
		//assertFalse(2 !=3);
		//assertTrue("Ya me joderia que fallase", 2 == 3);
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			fail("No hemos cargado el driver, mira el pom.xml y mete la dependencia, gilipollas");
		}

         try {
			Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos","root","");
			assertNotNull("La conexion no puede ser nula");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("Parece que no existe la bbdd humanos, o la url de conexion no es correcta,submnormal");
		}
		
	}

}
