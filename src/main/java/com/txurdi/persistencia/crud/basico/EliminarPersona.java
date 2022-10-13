package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para eliminar una Persona
 * 
 * @author Oskar LLaguno
 *
 */
public class EliminarPersona {

	public static void main(String[] args) {

		// recursos autoclosable
		final String SQL = "DELETE FROM personas WHERE id_persona = ? ;";
		try (Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false", "root",
				""); PreparedStatement pst = onn.prepareStatement(SQL); Scanner sc = new Scanner(System.in);) {

			System.out.println("Dime el id, cabeza de rodilla");
			int id = Integer.parseInt(sc.nextLine());

			pst.setInt(1, id);

			pst.executeUpdate();

			System.out.println("Persona eliminada analmente");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
