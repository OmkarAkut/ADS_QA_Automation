package webdriver.scripts.regression.opps2019calculations;

import java.util.Arrays;
import java.util.List;

public class Opps2019Data {

  public final String sqlQueryJPackagingC =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('OPPS2019JPACKC%')"
      + " AND t3.name = 'v10 REGRESSION 2019 OPPS J Packaging C'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesJPackagingC = Arrays.asList(
      1477.34,
      2554.38,
      4025.51,
      6790.8,
      5139.98,
      12781.52,
      10652.36,
      3195.56,
      13164.12,
      2885.32,
      5127.19,
      5102.3
  );

  public final String sqlQueryJPackagingD =
    "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
    + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
    + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
    + " AND t1.FINANCIALRECNUM like ('OPPS2019JPACKC%')"
    + " AND t3.name = 'v10 REGRESSION 2019 OPPS J Packaging D'"
    + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesJPackagingD = Arrays.asList(
        1477.34,
        2554.38,
        4025.51,
        6790.8,
        5139.98,
        12781.52,
        10652.36,
        3195.56,
        13164.12,
        2885.32,
        5127.19,
        5102.3
  );

  public final String sqlQueryJPackagingAndSiEqualsR =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('OPPS2019JPKSIRA%')"
      + " AND t3.name = 'v10 REGRESSION 2019 OPPS J Pack SI=R A'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesJPackagingAndSiEqualsR = Arrays.asList(
        19345.02,
        4651.52,
        4002.98,
        4973.32,
        13132.12,
        494905.33,
        2199.18,
        5276.67,
        11274.22,
        4651.52,
        0.0,
        0.0
  );

  public final String sqlQueryDeviceOffsets =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('OPPS2019DEVICEC%')"
      + " AND t3.name = 'v10 REGRESSION 2019 OPPS Device Offset C'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesDeviceOffsets = Arrays.asList(
      11507.14,
      19567.11,
      53141.41,
      34334.66,
      10384.94,
      4700.56,
      2837.1,
      3018.49,
      5024.93,
      20769.87,
      16491.35,
      63026.24,
      7952.31,
      28638.86,
      16267.13,
      0.0,
      0.0
  );

  public final String sqlQueryRadioPharm =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('OPPS2019DRUGRE%')"
      + " AND t3.name = 'v10 REGRESSION 2019 OPPS Radiopharm E'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesRadioPharm = Arrays.asList(
        646.74,
        710.49,
        1320.4,
        1477.38,
        646.74,
        710.49,
        1320.4,
        1477.38,
        699.78,
        809.37,
        1640.52,
        1797.5,
        699.78,
        809.37,
        1640.52,
        1797.5,
        1346.53,
        2329.22,
        2923.88,
        5041.93,
        731.37,
        731.37,
        403.5,
        513.09,
        699.78,
        809.37,
        1090.15,
        966.86,
        1410.27,
        0.0
  );

  public final String sqlQuerySkinSubstitutes =
    "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
    + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
    + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
    + " AND t1.FINANCIALRECNUM like ('OPPS2019SKINSUBA%')"
    + " AND t3.name = 'v10 REGRESSION 2019 OPPS Skin Sub A'"
    + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesSkinSubstitutes = Arrays.asList(
        1663.64,
        2970.93,
        1663.64,
        2970.93,
        1734.84,
        3042.13,
        1732.6,
        3039.89,
        2495.47,
        6023.76,
        2633.39,
        6148.75,
        482.45,
        480.21,
        1687.48,
        2994.77,
        1687.48,
        2994.77,
        1734.84,
        3042.13,
        1732.6,
        3039.89,
        3802.76,
        1732.6,
        3871.72,  //updated from 3873.96 to 3871.72 on 9-24-20
        0.0
  );

  public final String sqlQueryContrastAgents =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('OPPS2019DRUGCA%')"
      + " AND t3.name = 'v10 REGRESSION 2019 OPPS Contrast A'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesContrastAgents = Arrays.asList(
        216.68,
        414.45,
        742.97,
        237.93,
        435.7,
        764.22,
        433.35,
        1243.35,
        1485.93,
        432.5,
        240.52,
        438.29,
        766.81,
        237.93,
        435.7,
        764.22,
        433.35,
        1157.42,
        1416.59,
        237.93
  );

  public final String sqlQueryUpdateModifierPoLogic =
      "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
      + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
      + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
      + " AND t1.FINANCIALRECNUM like ('ADS1242PO%')"
      + " AND t3.name = 'ADS-1305 OPPS 2019 ADS-1242 Mod PO'"
      + " ORDER by t1.FINANCIALRECNUM"
  ;

  public final List<Double> expectedValuesUpdateModifierPoLogic = Arrays.asList(
      87.1,
      870.99,
      22.03,
      124.43,
      124.43,
      87.1,
      348.4,
      124.43,
      23.84
  );

}
