package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para buscar una persona por su id <br>
 * Se debe preguntar por consola el id a buscar <br>
 * Si lo encuentra muestra todos sus datos <br>
 * Si no existe indicar mostrar un mensaje <br>
 * @author Oskar LLaguno
 *
 */
public class ListarPersonas {

	public static void main(String[] args) {
		System.out.println("Introduce un id de persona");
		//recursos autoclosable
		final String SQL= "SELECT id_persona, nombre,nif,edad FROM personas WHERE id_persona= ? ;";
		try(Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false","root","");
				PreparedStatement pst= onn.prepareStatement(SQL);
				Scanner sc =new Scanner(System.in);){
			String id_scanner= sc.nextLine();
			pst.setInt(1, Integer.parseInt(id_scanner));
			try(ResultSet rs= pst.executeQuery();){
				int id=0;
				if(rs.next()) {
					id = rs.getInt("id_persona");
					String nombre=rs.getString("nombre");
					String nif=rs.getString("nif");
					int edad=rs.getInt("edad");
					System.out.printf("%-5s %-50s %-9s %-2s \n",id,nombre,nif,edad);
				}else {
					System.out.println("Eres gilipollas, esa id no existe, un saluda, panita");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
