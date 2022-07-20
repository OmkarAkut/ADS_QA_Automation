package webdriver.scripts.contracting.contractmodels;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContractModelsTemplate extends LoginStatic {

  boolean printout = false;
  private static ContractingMap contractingMap;

  @BeforeClass
  public static void setupScript() throws InterruptedException {
    contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
    System.out.println("Test Class: " + ContractModelsTemplate.class.getSimpleName());
    evolveLoginStaticUser(Users.ContractAnalyst1);
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  @Test
  public void test01() {
    WebElement pageTitle = driver.findElement(By.xpath("//div[contains(@class, 'areaModelTitle')][text()='Contracting Model Library']"));
    assertThat(pageTitle.getText(), equalTo("Contracting Model Library"));
  }
}
