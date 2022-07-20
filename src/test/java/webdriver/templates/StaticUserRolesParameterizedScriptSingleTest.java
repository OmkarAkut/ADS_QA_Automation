package webdriver.templates;

import org.junit.Test;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class StaticUserRolesParameterizedScriptSingleTest extends LoginRolesTesting {

  public StaticUserRolesParameterizedScriptSingleTest() {
    super(Users.AutomationTester1);
  }

  @Test
  public void testAssertUcqcLinkInCostingBubbleIsDisplayed() throws InterruptedException {
    GeneralElementsMap geMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
    waitForAjaxExtJs();
    assertThatElementIsDisplayed(geMap.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
  }

}

