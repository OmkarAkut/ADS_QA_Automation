package webdriver.scripts.datamaintenance.utilities;

import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.globalstatic.SetupStatic;

//Last update 03-11-2020.
@RunWith(Suite.class)
@Suite.SuiteClasses({

        UtilitiesEncountersWithNegativeChargeBalanceReportAds2427.class,
        UtilitiesEncountersWithNoChargesReportAds1480.class,
        UtilitiesEncountersWithNoEfrsReportAds2429.class,
        UtilitiesEncountersWithZeroChargeBalanceReportAds2428.class,

})



public class UtilitiesPageReportsTestSuite extends SetupStatic {

  @BeforeClass
  public static void browserChecker() throws IOException {
	  
    if ((setBrowser().toLowerCase().contains("headless"))) {
      System.out.println("NOTICE: Headless browser does not currently support file downloading "
              + "- run test in regular mode");
      System.out.println("UtilitiesPageReportsTestSuite Ignored");
    }
    assumeTrue(!(setBrowser().toLowerCase().contains("headless")));
  }

}
