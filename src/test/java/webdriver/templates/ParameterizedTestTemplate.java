package webdriver.templates;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;




@RunWith(Parameterized.class)
public class ParameterizedTestTemplate {
  private String string;

  public ParameterizedTestTemplate(String string) {
    this.string = string;
  }

  @Test
  public void testRolesLogin() {
    try {
      System.out.println("START TEST");
      System.out.println("My String: " + string);
      System.out.println("TEST COMPLETE");
    } catch (Throwable e) {
      System.out.println("<< TEST FAILED >>");
      fail();
    }
  }

  @Parameterized.Parameters //(name = "{index}: Test with role={0}")
  public static Collection<Object[]> roles() {
    Object[][] roles = new Object[][] {
      {"String1"},
      {"String2"},
    };
    return Arrays.asList(roles);
  }
}
