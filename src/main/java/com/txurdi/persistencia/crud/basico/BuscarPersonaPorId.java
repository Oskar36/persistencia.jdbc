package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejemplo básico para listar todas personas de la bbdd
 * @author Oskar LLaguno
 *
 */
public class BuscarPersonaPorId {

	public static void main(String[] args) {
		System.out.println("Listado de personas:");
		//recursos autoclosable
		final String SQL= "SELECT id_persona, nombre,nif,edad FROM personas ORDER BY nombre ASC LIMIT 500;";
		try(Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false","root","");
				PreparedStatement pst= onn.prepareStatement(SQL);
				ResultSet rs= pst.executeQuery();){
			while(rs.next()) {
				int id = rs.getInt("id_persona");
				String nombre=rs.getString("nombre");
				String nif=rs.getString("nif");
				int edad=rs.getInt("edad");
				System.out.printf("%-5s %-50s %-9s %-2s \n",id,nombre,nif,edad);
				
			}//while
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
