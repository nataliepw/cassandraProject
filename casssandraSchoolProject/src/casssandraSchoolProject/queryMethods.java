package casssandraSchoolProject;

import java.util.Random;

import com.datastax.driver.core.Session;

public class queryMethods {
	
	static Random randomStr = new Random();
	
	public static void createTable(Session s, String tableName) {
		String statement = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
				+ "id int PRIMARY KEY, "
				+ "firstname text,"
				+ "lastname text,"
				+ "height text);";
		
		s.execute(statement);
	}
	
	
	public static void insertMillionIntoTable(Session s, String tableName){
		for(int i = 1; i < 1000000; i++) {
			String rand1 = Integer.toString(randomStr.nextInt()),
					rand2 = Integer.toString(randomStr.nextInt()),
					rand3 = Integer.toString(randomStr.nextInt());
			
			
		String statement = "INSERT INTO " + tableName + " (id,firstname,lastname,height) VALUES ("+Integer.toString(i)+ ",'" + rand1 + "', '"+ rand2 +"', '"+ rand3 + "')"; 
		System.out.println("Executing statement: " + statement);
		s.execute(statement);
		}
	}
		
	
	public static void selectFromTable(Session s, String tableName) {
		String statement = "SELECT firstname from " + tableName + " ORDER BY firstname;";
		s.execute(statement);
	}
	
	public static void deleteFromTable(Session s, String tableName) {
		String statement = "DELETE FROM " + tableName + " WHERE id < 50;";
		s.execute(statement);
	}
	
	
}
