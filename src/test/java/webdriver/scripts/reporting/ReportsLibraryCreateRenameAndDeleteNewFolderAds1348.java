package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.globalstatic.LoginStatic;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryCreateRenameAndDeleteNewFolderAds1348 extends LoginStatic {

  //static GeneralElementsMap generalMap;
  static String folderName = "New Folderdelete" + javaGetRandomNumber(1000, printout);

  /** Regression Test for Test Ticket ADS-1348. **/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    //generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
    System.out.println("Test Class: " + ReportsLibraryCreateRenameAndDeleteNewFolderAds1348.class.getSimpleName());
    evolveLoginStaticUser(Users.AutomationTester1);
    goToPage("Report Library");
    waitForAjaxExtJs();
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
  }

  @AfterClass
  public static void teardownScript() {
    driver.switchTo().parentFrame();
  }


@Test
  public void test01aCreateNewReportsFolderAndConfirmRenameAndDeleteButtonsAreEnabled() throws Exception {
    driver.findElement(By.xpath("//div[@role='treeitem']/div[contains(text(),'Folders')]")).click();
    driver.findElement(By.xpath("//div/button[text()='New Folder']")).click();
    driver.findElement(By.xpath("//td[@style='vertical-align: middle;']//div[@class='gwt-TreeItem']//div[@class='gwtQuery-droppable']//img")).click();
    
    @SuppressWarnings("unused")
	Boolean isEnabled = driver.findElement(By.xpath("//div/button[text()='Rename Folder']")).isEnabled();
    Assert.assertTrue("Rename button is enabled", isEnabled);
    //assertElementIsEnabled(driver.findElement(By.xpath("//div/button[text()='Rename Folder']")), printout);
    //assertElementIsEnabled(driver.findElement(By.xpath("//div/button[text()='Delete Folder']")), printout);
  }

  @Test
  public void test02RenameNewReportsFolder() {
    try {
      driver.findElement(By.xpath("//div[contains(@class,'droppable')][contains(text(),'New Folder')]")).click();
      driver.findElement(By.xpath("//div/button[text()='Rename Folder']")).click();
      driver.findElement(
              By.xpath("//div[text()='Rename Folder']/ancestor::tr/following-sibling::tr/descendant::input"))
              .sendKeys(folderName)
      ;
      driver.findElement(
              By.xpath("//div[text()='Rename Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='OK']"))
              .click()
      ;
      assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@class,'droppable')][contains(text(),'"+folderName+"')]")), printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03CancelDeletingNewReportsFolder() {
    try {
      driver.findElement(By.xpath("//div/button[text()='Delete Folder']")).click();
      driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='Cancel']"))
              .click();
      assertElementIsDisplayed(driver.findElement(By.xpath(("//div[contains(@class,'droppable')][contains(text(),'"+folderName+"')]"))),
              printout
      );
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test04DeleteNewReportsFolder() {
    try {
      driver.findElement(By.xpath("//div/button[text()='Delete Folder']")).click();
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::div[text()='"+folderName+" will be deleted. Do you want to continue?']")),
              printout);
      driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='Delete']")).click();
      assertElementIsNotDisplayed(driver.findElement(By.xpath("//div[contains(@class, 'droppable')][contains(text(),'"+folderName+"')]")));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
