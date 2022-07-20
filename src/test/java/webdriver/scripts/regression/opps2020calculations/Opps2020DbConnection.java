package webdriver.scripts.regression.opps2020calculations;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2020DbConnection {

  Opps2020Data opps2020 = new Opps2020Data();
  CalculationHelper helper = new CalculationHelper();

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrect() throws ClassNotFoundException {
    helper.getDataFromDatabaseAndAssertExpectedValues(
            opps2020.sqlQuerySkinSubstitutes,
            opps2020.expectedValuesSkinSubstitutes
    );
  }

}
