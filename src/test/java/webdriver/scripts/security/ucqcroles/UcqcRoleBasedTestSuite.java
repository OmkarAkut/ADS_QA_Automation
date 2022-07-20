package webdriver.scripts.security.ucqcroles;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** Verifies specific role-based access to Ucqc page related sections
 * on main page (e.g., navigation "bubbles").
 * Updated: 04-10-2020
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        UcqcLinkInCostingBubbleDisplayedAds1466.class,
        CostMgrXCostAnalystBubbleNavigationStaticAds1466.class,
        UcqcLinkInCostingBubbleNotDisplayedStaticAds1466.class

})

public class UcqcRoleBasedTestSuite {
}
