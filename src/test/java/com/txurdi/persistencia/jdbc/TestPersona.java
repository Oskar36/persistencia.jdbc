package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import jdbc.Persona;

public class TestPersona {

	@Test
	public void test() {
		try {
			Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos", "root", "");
			Statement s = onn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM personas");
			while (rs.next()) {
				Persona p = new Persona(rs.getString("nombre"), rs.getString("nif"), rs.getInt("edad"));
				// assertTrue(p.getNif()!=null && p.getNif()==null);
				assertNotNull("El nombre no debe ser nulo", p.getNombre());
				assertTrue("La edad tiene que ser mayor a 0", p.getEdad() > 0);
			}
		} catch (SQLException e) {
			fail("Parece que no existe la bbdd humanos, o la url de conexion no es correcta,submnormal");
		}
	}

}
