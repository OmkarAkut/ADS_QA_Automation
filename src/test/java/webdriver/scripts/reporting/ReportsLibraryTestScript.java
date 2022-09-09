package webdriver.scripts.reporting;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryTestScript extends GoHelper {

	@BeforeClass
	public static void setupScript() throws InterruptedException {
		System.out.println("Test Class: " + ReportsLibraryTestScript.class.getSimpleName());
		Login.loginUser(Users.AutomationTester1);
		goToPage("report library");
	}

	@Test
	public void test01RunStandardReportFromTemplates() {
		//waitForPresenceOfElement(reportDirectoryXpath("Templates", "Costing"));
		//waitForPresenceOfElement("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'Costing')]");
		ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'Costing')]"));
		ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src,'reporting/main.html')]"));
		//Omkar 18/8/2022 : Switching the frame and the element is not clickable directly
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'General Reports')]")).isDisplayed();
		driver.findElement(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'General Reports')]")).click();
		driver.findElement(By.xpath("//input[@type='text' and @class='GJT013UBASB']")).sendKeys("CM Cost Model per RVU");
		driver.switchTo().parentFrame();

	}

	@Test
	public void test02RunStandardReportFromSavedReports() throws InterruptedException {
		goToPage("report library");
	}

	@Test
	public void test01RunFlexReportFromTemplates() throws InterruptedException {
		goToPage("report library");
	}

	@Test
	public void test01RunFlexReportFromSavedReports() throws InterruptedException {
		goToPage("report library");
	}

	public String reportDirectoryXpath (String directory, String subDirectory) {
		return "//div[contains(text(),'" + subDirectory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + directory + "')]";
	}

}
