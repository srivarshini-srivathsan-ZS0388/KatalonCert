package Keywords.helpers

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import groovy.sql.Sql
import java.sql.*
import groovy.json.JsonSlurper
import com.kms.katalon.core.annotation.Keyword


//import com.mysql.jdbc.Connection

public class FirstQuery {

	public static serialNumber
	public static riskTitle
	public static outcomeComments

	private static Connection connection = null;

	@Keyword

	def connectDB(String url, String dbname, String port, String username, String password){

		//Load driver class for your specific database type

		String conn = "jdbc:sqlserver://" + url + ":" + port + ";databaseName=" + dbname

		jdbc:sqlserver://UK-AVAPRE1-DB1.airsweb.local:1433;databaseName=v10_DataStore_PP1Automation


		jdbc:mysql://UK-AVAPRE1-DB1.airsweb.local:1433/v10_DataStore_PP1Automation


		//Class.forName("org.sqlite.JDBC")

		//String connectionString = "jdbc:sqlite:" + dataFile

		if(connection != null && !connection.isClosed()){

			connection.close()
		}

		connection = DriverManager.getConnection(conn, username, password)

		return connection
	}

	/**
	 * execute a SQL query on database
	 * @param queryString SQL query string
	 * @return a reference to returned data collection, an instance of java.sql.ResultSet
	 */

	//Executing the constructed Query and Saving results in resultset

	@Keyword
	def executeQuery(String queryString) {

		Statement stm = connection.createStatement()

		println 'Query String : ' +queryString

		ResultSet jsonString  = stm.executeQuery(queryString)
		jsonString.next()
		serialNumber = jsonString.getString(1)

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(serialNumber)
		serialNumber = parsedJson.SerialNumber


		println("Serial number:- " + serialNumber)

		//return rs
	}


	@Keyword
	def Map executeQuery1(String queryString) {

		println 'Query String : ' +queryString
		Statement stm = connection.createStatement()
		ResultSet jsonString  = stm.executeQuery(queryString)
		jsonString.next()
		String JsonOutput = jsonString.getString(1)

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(JsonOutput)

		println 'Parsed JSON: ' +parsedJson
		return parsedJson
	}


	/***
	 * Get Risk title
	 */

	@Keyword

	def executeQueryRisk(String queryString) {

		Statement stm = connection.createStatement()

		println 'Query String : ' +queryString

		ResultSet jsonString  = stm.executeQuery(queryString)
		jsonString.next()
		riskTitle = jsonString.getString(1)

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(riskTitle)
		riskTitle = parsedJson.RiskAssessmentTitle


		println("Risk  Assessment Title:- " + riskTitle)

		//return rs
	}

	/***
	 * Get Action Outcome
	 */

	@Keyword

	def executeQueryAction(String queryString) {

		Statement stm = connection.createStatement()

		println 'Query String : ' +queryString

		ResultSet jsonString  = stm.executeQuery(queryString)
		jsonString.next()
		outcomeComments = jsonString.getString(1)

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(outcomeComments)
		outcomeComments = parsedJson.Outcome


		println("Risk  Assessment Title:- " + outcomeComments)

		//return rs
	}


	//Closing the connection

	@Keyword

	def closeDatabaseConnection() {

		if(connection != null && !connection.isClosed()){

			connection.close()
		}

		connection = null
	}

	/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */

	@Keyword

	def execute(String queryString) {

		Statement stm = connection.createStatement()

		boolean result = stm.execute(queryString)

		return result
	}
}


