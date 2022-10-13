package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para crear una nueva Persona id es auto incremental
 * 
 * @author Oskar LLaguno
 *
 */
public class InsertarPersona {
	private final static int ERROR_CODE_DUPLICATE_ENTRY =1062;
	public static void main(String[] args) {

		// recursos autoclosable
		final String SQL = "INSERT INTO personas (nombre,edad,nif) VALUES (?,?,?);";
		try (Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false", "root",
				"");
				PreparedStatement pst = onn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				Scanner sc = new Scanner(System.in);) {
			
			System.out.println("Dime tu nombre, hijo de puta");
			String nombre = sc.nextLine();
			
			System.out.println("Dime tu edad, la concha de tu madre");
			int edad = Integer.parseInt(sc.nextLine());
			
			System.out.println("Dime tu nif, cementerio de piojos");
			String nif = sc.nextLine();
			
			//poner los parametros
			pst.setString(1, nombre);
			pst.setInt(2, edad);
			pst.setString(3, nif);
			int affectedRows = pst.executeUpdate();

			// recuperar el id solo si affected rows = 1
			if (affectedRows == 1) {
				try (ResultSet rsKeys = pst.getGeneratedKeys();) {
					if (rsKeys.next()) {
						int id = rsKeys.getInt(1);
						System.out.println("El ID generado de la persona es " + id);
					}
				}
				System.out.println("Persona insertada analmente");
			}
		} catch (SQLException e) {
			
			if (e.getErrorCode() == ERROR_CODE_DUPLICATE_ENTRY) {
				System.out.println("No escribas un nif que ya existe, pedazo de basura");
			}
		}

	}

}
