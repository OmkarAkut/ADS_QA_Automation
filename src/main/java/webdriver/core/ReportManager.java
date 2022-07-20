//package webdriver.core;
//
//import java.io.File;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.HasFullPageScreenshot;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//
//
//
///**
// * @author Omkar
// * this class will have all methods to generate reports
// *
// */
//public class ReportManager {
//
//	//Extent report variable
//	private static ExtentTest test;
//	private static ExtentReports report;
//	private static String ScreenshotFolder;
//	private static String ReportFolder;
//	private static String ResultDest;
//	public static SoftAssertions softly;
//
//
//	/**
//	 * initializeReport - initialize Extent report, sets up Global report folder having local date and time for local execution
//	 * creates index.html result file under GlobalReport folder
//	 */
//	/**
//	 * 
//	 */
//	private static void initializeReport() {
//
//		String testResultFolderName = "GLOBAL_REPORT";
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		String currentDateTime = dtf.format(now);
//		currentDateTime = currentDateTime.replace(":","-");
//		currentDateTime = currentDateTime.replace("/","-");
//
//		try {
//			testResultFolderName = "GLOBAL_REPORT" + "_" + currentDateTime;
//
//		}
//		catch(Exception e) {
//			System.out.println("Global Report not initialized");
//		}
//
//		File resultFolder = new File("Results\\" + testResultFolderName);
//		resultFolder.mkdirs();
//		ResultDest = resultFolder.toString();
//
//		report = new ExtentReports(ResultDest + "\\index.html", true);
//
//	}
//
//	public static void startTest(String testTitle,String testDescription) throws Exception {
//		softly = new SoftAssertions();
//		if(report==null) {
//			initializeReport();
//		}
//		
//		 test = report.startTest(testTitle,testDescription);
//		 	
//	}
//
//	/**
//	 * endTest - Close report and write everything to document
//	 */
//	/**
//	 * 
//	 */
//	public static void endTest() {
//		report.endTest(test);
//		report.flush();
//		test = null;
//		softly.assertAll();
//
//	}
//
//	/**
//	 * @param webdriver
//	 * @param ScreenshotName
//	 * @return
//	 * @throws Exception
//	 */
//	public static String capture(WebDriver webdriver,String ScreenshotName) throws Exception {
//		
//		TakesScreenshot ts = ((TakesScreenshot) webdriver);
//		File scrFile = ts.getScreenshotAs(OutputType.FILE);
//		File Dest = new File(ResultDest + "\\ScreenshotName" + "_" + System.currentTimeMillis() + ".png");
//		String ScreenshotFilePath = Dest.getAbsolutePath();
//		ScreenshotFilePath = ".."  + ScreenshotFilePath.split("Results")[1];
//		FileUtils.copyFile(scrFile, Dest);
//		return ScreenshotFilePath;
//
//	}
//	
//	/**
//	 * logPass - create logs for action performed during execution
//	 * @param - logMsg
//	 * @throws - IOexception
//	 */
//	public static void logPass(String logMsg) {
//		
//		if(test!= null)
//			test.log(LogStatus.PASS, logMsg);
//				
//	}
//	
//	/**
//	 * logFail - create logs for action performed during execution
//	 * @param - logMsg
//	 * @throws - IOexception
//	 */
//	public static void logFail(String logMsg) throws Exception{
//		
//		if(test!= null) {
//			test.log(LogStatus.FAIL, logMsg);
//		if(driver!=null)
////		logScreenshot;
//		}
//		softly.assertThat(false).isTrue();
//				
//	}
//	
//	public static void logScreenshot(String logMsg) throws Exception{
//		if(test!=null) {
//			String screenshotPath = capture(driver.logMsg);
//			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
//		}
//		
//	}
//
//}
