package ExtentReport;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	public static ExtentTest extenttest;
	public static ExtentReports report = new ExtentReports();

		public static ExtentReports reportCreate(String tcname, String packageName,String reportname) throws Exception {
		try {
			createUserDir("Results/Screenshots");
		    String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			System.out.println(currentDateTime);
			File file = new File(System.getProperty("user.dir")+"/Results/"+"/"+packageName+"/" +reportname + "_" + currentDateTime + ".html");
			ExtentSparkReporter spark = new ExtentSparkReporter(file);
			final File CONF = new File("extent-config.xml");
			report = new ExtentReports();
			report.attachReporter(spark);
			spark.loadXMLConfig(CONF);
			spark.config().setCss("badge.badge-default{display:none}");
			spark.config().setCss(".badge-primary{background-color:#24d5d8}");
			extenttest = report.createTest(tcname);
			
		} catch (IOException e) {
			ExtentReport.extenttest.log(Status.FAIL, e.getMessage());
			ExtentReport.extenttest.log(Status.INFO, e);
			
		}
		return report;
	}
	
	public static void createUserDir(final String dirName) throws IOException {
		final File homeDir = new File(System.getProperty("user.dir"));
	    final File dir = new File(homeDir, dirName);
	    if (!dir.exists() && !dir.mkdirs()) {
	        throw new IOException("Unable to create " + dir.getAbsolutePath());
	    }
	}
	 //This method is to capture the screenshot and return the path of the screenshot.
	public static void getScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		try {
			File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			BufferedImage img = ImageIO.read(screen);
			File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
			ImageIO.write(img, "png", new File(filetest +  "/Results/Screenshots/" +screenshotName+".png"));

			//Log Screenshot in Report
			ExtentReport.extenttest.info("Details of " + "Test Case Failed Screenshot", MediaEntityBuilder
			                .createScreenCaptureFromPath(System.getProperty("user.dir") + "/Results/Screenshots/" +screenshotName+".png").build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//log pass to report Shilpa 05.09.2022
	public static void logPass(String logStatus,String tcname) {
		extenttest.log(Status.PASS, tcname);

	}
	//logfail and add screenshot to report , Shilpa 05.09.2022
	public static void logFail(String logStatus,String tcname,WebDriver driver,Throwable e) throws Throwable {
		extenttest.log(Status.FAIL, tcname);
		
		extenttest.log(Status.INFO, e);
		ExtentReport.getScreenshot(driver, tcname);
	}
	
}
