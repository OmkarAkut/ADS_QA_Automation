package webdriver.utilities;

/*NOTES:  Running the JDBC functionality requires the OJDBC.jar that contains the oracle driver (version specific to your local JDK).
* Currently this requires a manual download from oracle.com and installation on project classpath (File, Project Structure, Dependencies, click + to add, locate jar).
* The Result Set is a "table" format with rows and columns and contains a cursor.  The result set methods
* can move the cursor around this table and return values.  You can move the cursor to a specific row with a
* "navigation" method and then get the data value from that row by specifying the column (the column parameter
* can either be the column name or the column index (which starts at 1)).  In order to save db value to a java variable, both have to be the same
* type, thus the different AsX methods that return different variable types.
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class JavaDataBaseConnectivity {

    //Oracle DB Endpoint Syntax: "jdbc:oracle:thin:@ipaddress:portnumber:db_name"
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;
    private String sqlQuery = "sqlQuery";

    public JavaDataBaseConnectivity(String dbUrl, String dbUsername, String dbPassword){
        this.DB_URL = dbUrl;
        this.DB_USERNAME = dbUsername;
        this.DB_PASSWORD = dbPassword;
    }

    public JavaDataBaseConnectivity(List<String> dbCredentials){
        this.DB_URL = dbCredentials.get(0);
        this.DB_USERNAME = dbCredentials.get(1);
        this.DB_PASSWORD = dbCredentials.get(2);
    }

    public String getDbUrl() {
        return DB_URL;
    }

    public String getDbUsername() {
        return DB_USERNAME;
    }

    public String getDbPassword() {
        return DB_PASSWORD;
    }

    public String getDbName() {
        String[] sid = DB_URL.split(":");
        return sid[5];
    }

    /**
     * Sets raw SQL query (in test script)
     */
    public void setSqlQuery(String sqlQuery) {
        if (sqlQuery.toLowerCase().contains("select")) {
            this.sqlQuery = sqlQuery;
        } else {
            System.out.println("Potentially Dangerous SQL Statement - Should contain Select keyword - Set To Null");
            this.sqlQuery = null;
        }
    }

    /**
     * Returns current raw SQL query as String (to test script)
     */
    public String getSqlQuery() {
        return this.sqlQuery;
    }

    public int jdbcGetRowCountFromDb(String sqlQuery) throws ClassNotFoundException {
        return jdbcGetResultSetFirstRowValueAsInt(sqlQuery, 1);
    }

    /**
     * Connects to database, passes SQL statement and gets a ResultSet from database
     */
    public int jdbcGetResultSetFirstRowValueAsInt(String sqlQuery, int column) throws  ClassNotFoundException {
        System.out.println("Db Name: " + getDbName());
        ResultSet resultSet;
        int resultSetFirstCellValueInt = 0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            resultSet = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(resultSet != null){
                System.out.println("Printing Result Set");
                resultSet.next();
                resultSetFirstCellValueInt = resultSet.getInt(column);
                System.out.println("Result Set First Cell Value Int: " + resultSetFirstCellValueInt);
            }
            else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return resultSetFirstCellValueInt;
    }

    public String jdbcGetResultSetFirstRowFirstColumnsAsString(String sqlQuery) throws  ClassNotFoundException {
        ResultSet resultSet;
        String resultSetFirstCellValueString = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            resultSet = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(resultSet != null){
                System.out.println("Printing Result Set");
                resultSet.next();
                resultSetFirstCellValueString = resultSet.getString(1);
                System.out.println("Result Set First Cell Value Int: " + resultSetFirstCellValueString);
            }
            else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return resultSetFirstCellValueString;
    }

    public double jdbcGetResultSetFirstRowFirstColumnsAsDouble(String sqlQuery) throws  ClassNotFoundException {
        ResultSet resultSet;
        double resultSetFirstCellValueDouble = 0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            resultSet = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(resultSet != null){
                System.out.println("Printing Result Set");
                resultSet.next();
                resultSetFirstCellValueDouble = resultSet.getDouble(1);
                System.out.println("Result Set First Cell Value Double: " + resultSetFirstCellValueDouble);
            }
            else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return resultSetFirstCellValueDouble;
    }

    public void jdbcGetResultSetAndPrintAsDouble(String sqlQuery, int resultSetRow) throws ClassNotFoundException {
        ResultSet rs;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            rs = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(rs != null) {
                System.out.println("Printing Result Set");
                while (rs.next()) {
                    System.out.println(rs.getDouble(resultSetRow));
                }
            } else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        //return resultSetFirstCellValueDouble;
    }


    public ArrayList<Double> jdbcSaveResultSetToArrayListAsDouble(String sqlQuery, int resultSetRow) throws ClassNotFoundException {
        System.out.println("Db Name: " + getDbName());
        ResultSet rs;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        ArrayList<Double> dataList = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            rs = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(rs != null) {
                System.out.println("Printing Result Set");
                while (rs.next()) {
                    System.out.println(rs.getDouble(resultSetRow));
                    dataList.add(rs.getDouble(resultSetRow));
                }
            } else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return dataList;
    }

    public ArrayList<String> jdbcSaveResultSetToArrayListAsString(
            String sqlQuery, int resultSetColumn)
            throws ClassNotFoundException {
        System.out.println("Db Name: " + getDbName());
        ResultSet rs;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        ArrayList<String> dataList = new ArrayList<>();
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            rs = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if (rs != null) {
                System.out.println("Printing Result Set");
                while (rs.next()) {
                    System.out.println(rs.getString(resultSetColumn));
                    dataList.add(rs.getString(resultSetColumn));
                }
            } else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return dataList;
    }

    public ArrayList<String> jdbcSaveResultSetToArrayListAsStrings(
            String sqlQuery, int resultSetRow) throws ClassNotFoundException {
        System.out.println("Db Name: " + getDbName());
        ResultSet rs;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        ArrayList<String> dataListAsStrings = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Database Connection Established");
            Statement statement = dbConnection.createStatement(); //Create Statement object
            rs = statement.executeQuery(sqlQuery); //Execute SQL Query. Store results in a ResultSet.
            System.out.println("Returning Result Set");
            if(rs != null) {
                System.out.println("Printing Result Set");
                while (rs.next()) {
                    System.out.println(rs.getString(resultSetRow));
                    dataListAsStrings.add(rs.getString(resultSetRow));
                }
            } else {
                System.out.println("Result Set is Null");
            }
        } catch (Exception e) {
            System.out.println("Print Result Set Failed");
            System.out.println(e.getMessage());
            fail();
        }
        return dataListAsStrings;
    }

}
