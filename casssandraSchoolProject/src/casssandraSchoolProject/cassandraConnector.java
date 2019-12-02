package casssandraSchoolProject;

import java.time.Duration;
import java.time.Instant;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class cassandraConnector {

	public static void testTombstone(Session session) {
		// create table(s)
		queryMethods.createTable(session, "testTable");

		// inserts a hundred thousand row into testTable
		queryMethods.insertIntoTableTombstone(session, "testTable");
		// delete all id rows
		queryMethods.deleteIDFromTable(session, "testTable");
		// Tracing performance after deleting it's information
		Instant start = Instant.now();
		queryMethods.selectALLFromTable(session, "testTable");
		Instant finish = Instant.now();
		System.out.println("Time it takes to SELECT *, after deletion in table: ");
		System.out.println(Duration.between(start, finish).toMillis() + " ms");

		// inserts a hundred thousand row into testTable
		queryMethods.insertIntoTableTombstone(session, "testTable");
		// Tracing performance of selecting from table without deleting it's information
		Instant start1 = Instant.now();
		queryMethods.selectALLFromTable(session, "testTable");
		Instant finish1 = Instant.now();
		System.out.println("Time it takes to SELECT *, without deleting: ");
		System.out.println(Duration.between(start1, finish1).toMillis() + " ms");
	}

	public static void testScalability(Session session) {
		// create table(s)
		queryMethods.createTable(session, "testTable2");

		// Tracing performance of inserting random values of hundred thousand rows
		Instant start = Instant.now();
		// query: insert a hundred thousand row of random generated strings
		queryMethods.insertIntoTableScalability(session, "testTable2");
		Instant finish = Instant.now();
		System.out.println("Time it takes to insert a hundred thousand rows of random generated strings: ");
		System.out.println(Duration.between(start, finish).toMillis() + " ms");

	}

	public static void main(String[] args) {

		String serverIP = "127.0.0.1";
		String keyspace = "cassandraProsjekt";

		// Building the cluster object
		Cluster cluster = Cluster.builder().addContactPoint(serverIP).build();

		// create session object
		Session session = cluster.connect(keyspace);

		testTombstone(session);
		testScalability(session);

		session.close();
	}
}
