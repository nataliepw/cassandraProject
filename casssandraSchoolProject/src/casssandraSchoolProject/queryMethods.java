package casssandraSchoolProject;

import java.util.Random;
import com.datastax.driver.core.Session;

public class queryMethods {
	
	static Random randomStr = new Random();
	
	public static void createTable(Session s, String tableName) {
		String statement = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
				+ "id int PRIMARY KEY, "
				+ "age text)";
		
		s.execute(statement);
		System.out.println("Table created");
	}

	
	public static void insertIntoTableManually(Session s, String tableName, String statement){
		s.execute(statement);
		
		}
	
	public static void insertIntoTableScalability(Session s, String tableName){
		for(int i = 1; i < 100000; i++) {
			int rand = randomStr.nextInt();
			
		String statement = "INSERT INTO " + tableName + " (id,age) VALUES (" + i + ", '" + rand + "');"; 
		//System.out.println("Executing statement: " + statement);
		s.execute(statement);
		
		}
	}
	
	public static void insertIntoTableTombstone(Session s, String tableName){
		for(int i = 1; i < 100000; i++) {
			String statement = "INSERT INTO " + tableName + " (id,age) VALUES (" + i +  " ,'klsdjf');"; 
			//System.out.println("Executing statement: " + statement);
			
			s.execute(statement);
		
		}
			
	}
		
	
	public static void selectALLFromTable(Session s, String tableName) {
		String statement = "SELECT * FROM " + tableName + ";";
		s.execute(statement);
		
	}
	
	public static void selectFromTableManually(Session s, String tableName, String statement) {
		s.execute(statement);
		
		
	}
	
	public static void deleteIDFromTable(Session s, String tableName) {
		String statement = "TRUNCATE cassandraprosjekt." + tableName + ";";
		s.execute(statement);
	}
	
	
}
