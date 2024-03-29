package core;

import driver.*;
import model.Response;
import model.Database;

import java.util.List;
import java.util.LinkedList;
import java.io.Closeable;
import java.io.IOException;

/** 
 * This class implements a DBMS with an active
 * connection to a backing database.
 * 
 * Finish implementing the required features
 * but do not modify the protocols.
 */
public class Server implements Closeable {
	@SuppressWarnings("unused")
	private static final String
		STUDENT_NAME  = "Your Name",
		STUDENT_IDNUM = "000000000",
		STUDENT_EMAIL = "email@mix.wvu.edu";
	
	private Database database;
	private AbstractDriver[] drivers;
	
	public Server() {
		this(new Database());
	}
	
	public Server(Database database) {
		this.database = database;

		// TODO: Initialize available drivers in sequence.
		this.drivers = new AbstractDriver[]{
			new Echo()
		};
	}
	
	public Database database() {
		return database;
	}
	
	public List<Response> interpret(String script) {
		/*
		 * TODO: This wrongly assumes the entire script
		 * is a single query. However, there may be
		 * one or more semicolon-delimited queries in
		 * the script to be split and parsed distinctly.
		 */
		String query = script;
		
		/* 
		 * TODO: This only tests the first driver for a response
		 * to the first query. Instead, iterate over every driver
		 * in sequence until one of them gives a non-null response
		 * for the first query, ensuring that the driver for
		 * unrecognized queries is the last driver tested. Then
		 * iterate again for the next query in sequence
		 */
		List<Response> responses = new LinkedList<Response>();
		responses.add(drivers[0].execute(query));
		
		return responses;
	}

	@Override
	public void close() throws IOException {
		/* 
		 * TODO: Optionally, execute any required tasks when the
		 * server is closed. Otherwise, leave unimplemented.
		 */
	}
}
