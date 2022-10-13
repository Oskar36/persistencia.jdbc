package com.txurdi.persistencia.migracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class LeerCSV {
	private final static int ERROR_CODE_DUPLICATE_ENTRY =1062;
		public static void main(String[] args) {
			String line = "";  
			String splitBy = "," ;  
			try   
			{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("personas.csv"));  
			br.readLine();
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
			String[] employee = line.split(",");    // use comma as separator  
			// recursos autoclosable
			final String SQL = "INSERT INTO personas (nombre,edad,nif) VALUES (?,?,?);";
			try (Connection onn = DriverManager.getConnection("jdbc:mysql://localhost:3306/humanos?useSSL=false", "root",
					"");
					PreparedStatement pst = onn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);) {								
				//poner los parametros
				pst.setString(1, employee[0]);
				pst.setInt(2,Integer.parseInt(employee[4]));
				pst.setString(3, employee[3]);
				int affectedRows = pst.executeUpdate();
				if(affectedRows==1) {
					System.out.println("Persona creada");
				}

			} catch (SQLException e) {
				
				if (e.getErrorCode() == ERROR_CODE_DUPLICATE_ENTRY) {
					System.out.println("No escribas un nif que ya existe, pedazo de basura");
				}
			}
			}  
			}   
			catch (IOException e)   
			{  
			e.printStackTrace();  
			}  
		}
	}  

