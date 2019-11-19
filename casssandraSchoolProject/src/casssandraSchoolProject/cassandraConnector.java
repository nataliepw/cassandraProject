package casssandraSchoolProject;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class cassandraConnector {
	
	public static void main(String [] args) {

	String serverIP = "127.0.0.1";
	String keyspace = "cassandraProsjekt";
	
	
	//Building the cluster object
	Cluster cluster = Cluster.builder().addContactPoint(serverIP).build();
	
	//create session object
	Session session = cluster.connect(keyspace);
	
	//create table(s)
	queryMethods.createTable(session, "person");
	System.out.println("Table created");
	
	//insert into table(s)
	queryMethods.insertMillionIntoTable(session,"person");
	
	//Tracing performance

	session.close();
}
}
