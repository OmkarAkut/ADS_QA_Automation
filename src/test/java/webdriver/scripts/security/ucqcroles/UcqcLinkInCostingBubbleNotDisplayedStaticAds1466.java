package webdriver.scripts.security.ucqcroles;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import webdriver.globalstatic.LoginStatic;
import webdriver.users.Users;

@RunWith(Parameterized.class)
public class UcqcLinkInCostingBubbleNotDisplayedStaticAds1466 extends LoginStatic {

  private String user;

  /**
   Covers dev story ADS-241.
   Unit Cost Quick Calculation Quick Link does not display for other standard roles
   Unit Cost Quick Calculation Quick Link does not display for Custom Roles that do not have UCQC
   Unit Cost Quick Calculation does not display as a Quick Link in Costing Bubble on Home Page
   when Costing Department Manager is not one of the user roles
   */
  public UcqcLinkInCostingBubbleNotDisplayedStaticAds1466(Users user) {
    super(user);
    System.out.println("User under test: " + user);
    this.user = String.valueOf(user);
  }

  /** Test users for current test case. */
  @Parameterized.Parameters (name = "{index}: Users={0}")
  public static Collection<Object[]> users() {
    Object[][] users = new Object[][] {
      {Users.valueOf("CostAnalyst1")},
      {Users.valueOf("CustomRoleAllStandardRolesExceptUcqc")},
    };
    return Arrays.asList(users);
  }

  @Test
  public void testUcqcLinkInCostingBubbleNotDisplayed() {
    try {
      waitForAjaxExtJs();
      //Shilpa updated xpath 24.08.2022 
//      assertElementIsNotDisplayed(By.xpath("//*[@class='bubbleContent' and @id='costing']"
//              + "/following-sibling::div/descendant::ul/li/a[text()='Unit Cost Quick Calculation']"
//      ));
      assertElementIsNotDisplayed(By.xpath("//*[@class='bubbleContent' and @id='costing-bubble-content1']"
              + "/following-sibling::div/descendant::ul/li/a[text()='Unit Cost Quick Calculation']"
      ));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
