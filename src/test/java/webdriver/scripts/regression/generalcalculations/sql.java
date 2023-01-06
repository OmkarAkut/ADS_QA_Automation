package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class sql {

	public static void main(String[] args) {
		
		
			//				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.204.20.101:1528:STAGING","qa","pass");
			//				System.out.println(connection);
			//				Statement stmt=connection.createStatement();
			//			
			////				ResultSet resultSet = stmt.executeQuery("select t3.name,t1.FINANCIALRECNUM,t1.CALCULATEDPAYMENT,t1.devicepassthrough,t1.copayamount,t4.CALCULATEDPAYMENT,t4.mechanismname,t4.percentage,t5.PAYMENT,t5.code,t5.STATUSINDICATOR,t5.adjustedcopay,t5.codename,t5.codetype,t5.rate,t5.servicedate,t5.percentage,t5.OUTLIERPAYMENTfrom encounterpricinglog2 t1,lineofbusinesscalcresults2 t2,contract2 t3,logentry2 t4,MULTIPLECODERESULT t5where t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid and t1.objectid = t4.encpricinglogid and t4.objectid = t5.BASICLOGENTRYID and t3.name = 'v103 REGRESSION 2021 IPPS AOT A1'order by t1.FINANCIALRECNUM;");  
			//				ResultSet resultSet=stmt.executeQuery("SELECT count (financialrecnum) FROM chargeitem2");
			//				while (resultSet.next()) {				 
			//					   String empId = resultSet.toString();
			//							   //					   String empName = rs.getString("NAME");
			//		 
			//					   System.out.println("EmpId : " + empId);
			////					   System.out.println("EmpName : " + empName); 
			//					}
			////				System.out.println(stmt.executeQuery("SELECT * FROM chargeitem2 "));
			//				connection.close();
//							JavaDataBaseConnectivity jdbc=new JavaDataBaseConnectivity("jdbc:oracle:thin:@10.204.20.101:1528:STAGING","qa","pass");
//							jdbc.setSqlQuery("SELECT count (financialrecnum) FROM chargeitem2");
//							 System.out.println("Sql Query: " +  jdbc.getSqlQuery());  
		Connection dbConnection;
		try {
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@10.204.20.101:1528:STAGING","qa","pass");
			 System.out.println("Database Connection Established");
	            Statement statement = dbConnection.createStatement(); //Create Statement object
//	            ResultSet rs = statement.executeQuery("SELECT * FROM logentry2 "); //Execute SQL Query. Store results in a ResultSet.
	          
	            
//	            ResultSetMetaData rsmd = rs.getMetaData();
	           ResultSet rs=statement.executeQuery("select t3.name,t1.FINANCIALRECNUM,t1.CALCULATEDPAYMENT,t1.devicepassthrough,t1.copayamount,t4.CALCULATEDPAYMENT,t4.mechanismname,t4.percentage,t5.PAYMENT,t5.code,t5.STATUSINDICATOR,t5.adjustedcopay,t5.codename,t5.codetype,t5.rate,t5.servicedate,t5.percentage,t5.OUTLIERPAYMENT from encounterpricinglog2 t1,lineofbusinesscalcresults2 t2,contract2 t3,logentry2 t4,MULTIPLECODERESULT t5 where t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid and t1.objectid = t4.encpricinglogid and t4.objectid = t5.BASICLOGENTRYID  and t3.name = 'v103 REGRESSION 2021 IPPS AOT A1' order by t1.FINANCIALRECNUM");
	           ResultSetMetaData rsmd = rs.getMetaData();
	           System.out.println("Returning Result Set");
	            Integer count = null;
//	            JavaDataBaseConnectivity javaDataBaseConnectivity = new JavaDataBaseConnectivity(null, null, null);
				
	            if(rs != null) {
//	                System.out.println("Printing Result Set");
////	                rs.next();
//	                int resultSetFirstCellValueInt = rs.getInt("CALCULATEDPAYMENT");
//	                System.out.println(resultSetFirstCellValueInt);
//	            	 System.out.println(rsmd.get);
	            	 for(int i=1;i<=rsmd.getColumnCount();i++) {
	            		 System.out.println(rsmd.getColumnName(i));
	            	 }
	                while (rs.next()) {
//	                	int resultSetFirstCellValueInt = rs.getInt("CALCULATEDPAYMENT");
//		                System.out.println(resultSetFirstCellValueInt);
	                	System.out.println(rs.getFloat("CALCULATEDPAYMENT"));
	                   
	                }
	            } 
	            
	          
	           
		}    
	            
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}    
}

		




