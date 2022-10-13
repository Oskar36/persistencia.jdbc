package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para actualizar los datos de una persona
 * 
 * @author Oskar LLaguno
 *
 */
public class ActualizarPersona {

	public static void main(String[] args) {

		// recursos autoclosable
		final String SQL = "UPDATE personas set nombre = ? , edad = ? , nif = ? WHERE id_persona = ? ;";
		try (Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false", "root",
				""); PreparedStatement pst = onn.prepareStatement(SQL); Scanner sc = new Scanner(System.in);) {
			System.out.println("Dime tu nombre, hijo de puta");
			String nombre = sc.nextLine();

			System.out.println("Dime tu edad, la concha de tu madre");
			int edad = Integer.parseInt(sc.nextLine());

			System.out.println("Dime tu nif, tobogan de piojos");
			String nif = sc.nextLine();

			System.out.println("Dime tu id, gordo termotanque");
			int id = Integer.parseInt(sc.nextLine());

			pst.setString(1, nombre);
			pst.setInt(2, edad);
			pst.setString(3, nif);
			pst.setInt(4, id);

			pst.executeUpdate();

			System.out.println("Persona actualizada");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
