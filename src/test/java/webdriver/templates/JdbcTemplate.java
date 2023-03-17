package webdriver.templates;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.utilities.JavaDataBaseConnectivity;

/*NOTE:  Running the JDBC requires that the ojdbc.jar (containing jdbc drivers) is installed
on the project classpath, which can be downloaded from Oracle and manually installed under
File, Project Structure.  Currently, this jar is not automatically installed by Maven.
*/

/* Example SqlQuery Syntax:  (Note:  No terminal semi-colon in sql statement)
        -Verify all statements in sql developer first prior to using in test script-
        SELECT column
        FROM Table
        WHERE column_header='value'
        AND column_header BETWEEN 'value1' AND 'value2'
        ORDER BY column_header
        LIMIT 1
    */

/* Actual Multi-Line Example (note single line example in atTest method):
            String sqlQuery = "SELECT a.DIR_FIXED_COST+a.DIR_VAR_COST+a.IND_FIXED_COST+a.IND_VAR_COST " +
            "FROM costmodel2 cm, costmodelscenario2 cms, entdeptcostcontainer2 ed, costitemdataset2 cids, activitycostitem2 a " +
            "WHERE cm.objectid=cms.costmodelid " +
            "and cms.objectid=ed.cstmodscenid " +
            "and ed.objectid=cids.containerid " +
            "and cids.objectid=a.masterid " +
            "AND cm.name='Marina' " +
            "and cms.name='*CM3 TB MHFY05 Before Vol Change_UCQC' " +
            "and ed.entitycode='150' " +
            "and ed.deptcode='2130' " +
            "and a.actdscrcode=1100544";
*/

public class JdbcTemplate {

  //NOTE: DB Connection Information set to null - User must set appropriately for specific db and test scenario
  String db_url_evolve = "jdbc:oracle:thin:@10.204.20.120:1522:qadb01";    //Evolve = "jdbc:oracle:thin:@192.168.210.100:1543:qacurr1";
  String db_username = "qa";      //Evolve = "qa";
  String db_password = "pass";      //Evolve = "pass";

  String sqlQuery = "SELECT objectid FROM Costmodelscenario2 WHERE objectid=1";
  private JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity(db_url_evolve, db_username, db_password);

  @Test
  public void runJdbc() throws ClassNotFoundException,Throwable{
	  ExtentReport.reportCreate("runJdbc", "webdriver.templates", "runJdbc");
    try {
		jdbc.setSqlQuery(sqlQuery);
		System.out.println("getSqlQuery: " +  jdbc.getSqlQuery());

		//Uncomment specific statement below, depending on variable type as int(default), String, or double
		jdbc.jdbcGetResultSetFirstRowValueAsInt(jdbc.getSqlQuery(), 1);
		//jdbc.jdbcGetResultSetFirstRowFirstColumnsAsString(jdbc.getSqlQuery());
		//double testDouble = jdbc.jdbcGetResultSetFirstRowFirstColumnsAsDouble(jdbc.getSqlQuery());
		ExtentReport.logPass("PASS", "runJdbc");
    	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "runJdbc", null, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}
