package webdriver.scripts.regression.opps2020calculations;

import java.util.Arrays;
import java.util.List;

public class Opps2020Data {

  public String getOpps2020SqlQuery(String financialRecNum, String name) {
    String sql = "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
          + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
          + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
          + " AND t1.FINANCIALRECNUM like ('" + financialRecNum + "')"
          + " AND t3.name = '" + name + "'"
          + " ORDER by t1.FINANCIALRECNUM";
    return sql;
  }

  public final String sqlQueryJPackagingC = getOpps2020SqlQuery(
          "OPPS2020JPACKC%",
          "v102 REGRESSION OPPS 2020 J Packaging C")
  ;

  public final List<Double> expectedValuesJPackagingC = Arrays.asList(
//        1474.23,
//        2490.58,
//        4254.86,
//        7248.66,
//        5228.21,
//        8572.0,
//        9869.26,
//        3358.59,
//        11291.32,
//        2697.26,
//        5382.94,
//        5576.81,
//        27339.96,
//        5791.9,
//        7421.61,
//        26246.44

        1474.23,
        2490.58,
        4254.86,
        7248.66,
        5228.21,
        9064.0,
        9869.26,
        3358.59,
        11291.32,
        2697.26,
        5382.94,
        5576.81,
        27339.96,
        5791.9,
        7421.61,
        26246.44
  );

  public final String sqlQueryJPackagingAndSiEqualsRechelon = getOpps2020SqlQuery(
          "OPPS2020JPKSIRA%",
          "v102 REGRESSION OPPS 2020 J Pack SI=R A")
          ;


  public final String sqlQueryJPackagingAndSiEqualsR = getOpps2020SqlQuery(
          "OPPS2020JPKSIRA%",
          "v102 REGRESSION OPPS 2020 J Pack SI=R")
  ;

  public final List<Double> expectedValuesJPackagingAndSiEqualsR = Arrays.asList(
        3771.9,
        4386.75,
        5732.12,
        4717.26,
        26987.3,
        431354.38,
        127.2,
        3115.78,
        10550.47,
        2766.75,
        0.0,
        0.0
  );



  public final String sqlQueryDeviceCredit = getOpps2020SqlQuery(
          "OPPS2020DC%",
          "v102 REGRESSION OPPS 2020 Device Credit")
          ;

  public final List<Double> expectedValuesDeviceCredit = Arrays.asList(
        135866.6,
        4667.8,
        936.43,
        4293.09,
        3813.15,
        7202.57,
        5483.98,
        3394.88,
        2954.4,
        7634.62,
        163630.53,
        8576.81,
        2779.02,
        6263.75,
        6263.75,
        12620.73,
        16965.62,
        5682.06,
        5048.56,
        35398.55,
        3253.86,
        3204.95,
        163791.64,
        127.2,
        58.96,
        322.02
  );

  public final String sqlQueryDeviceOffsets = getOpps2020SqlQuery(
          "OPPS2020DEVICEC%",
          "v102 REGRESSION OPPS 2020 Device Offset")
  ;

  public final List<Double> expectedValuesDeviceOffsets = Arrays.asList(
        12781.84,
        20542.71,
        52506.72,
        35559.66,
        10642.1,
        14879.03,
        2976.47,
        3060.96,
        5320.7,
        21284.21,
        17120.17,
        61266.53,
        8207.65,
        28920.96,
        17243.67,
        20706.87,
        15636.0,
        54667.8,
        11010.99,
        3523.09,
        772.03,
        30633.26,
        8207.65,
        3221.13,
        11833.79,
        671.33,
        1349.42,
        8207.65,
        28920.96,
        17243.67
  );

  public final String sqlQueryRadioPharm = getOpps2020SqlQuery(
          "OPPS2020DRUGRA%",
          "v102 REGRESSION OPPS 2020 Radiopharm A")
  ;

  public final List<Double> expectedValuesRadioPharm = Arrays.asList(
//        600.27,
//        666.84,
//        1366.38,
//        1549.94,
//        3369.94,
//        3436.51,
//        4039.01,
//        4294.83,
//        661.22,
//        727.79,
//        1366.38,
//        1586.11,
//        395.39,
//        506.93,
//        1366.38,
//        1549.94,
//        654.56,
//        766.1,
//        1625.55,
//        1809.11,
//        3424.23,
//        3535.77,
//        4395.22,
//        4578.78,
//        715.51,
//        827.05,
//        1686.5,
//        1870.06,
//        395.39,
//        506.93,
//        1366.38,
//        1549.94,
//        1254.83,
//        10508.04,
//        3016.79,
//        4649.81,
//        675.94,
//        3445.61,
//        736.89,
//        416.77,
//        417.12,
//        528.66,
//        1388.11,
//        1571.67,
//        654.56,
//        3535.77,
//        1686.5,
//        1549.94,
//        902.31,
//        6398.78,
//        1315.78
          600.27,
          666.84,
        1366.38,
        1549.94,
        3424.23,
        3535.77,
        4395.22,
        4578.78,
        661.22,
        727.79,
        1366.38,
        1586.11,
        395.39,
        506.93,
        1366.38,
        1549.94,
        654.56,
        766.1,
        1625.55,
        1809.11,
        3424.23,
        3535.77,
        4395.22,
        4578.78,
        715.51,
        827.05,
        1686.5,
        1870.06,
        395.39,
        506.93,
        1366.38,
        1549.94,
        1254.83,
        10607.3,
        3016.79,
        4649.81,
        675.94,
        3445.61,
        736.89,
        416.77,
        417.12,
        528.66,
        1388.11,
        1571.67,
        654.56,
        3535.77,
        1686.5,
        1549.94,
        1062.23,
        6453.07,
        1315.78
  );

  public final String sqlQuerySkinSubstitutes = getOpps2020SqlQuery(
          "OPPS2020SKINSUBA%",
          "v102 REGRESSION OPPS 2020 Skin Sub A")
  ;

  public final List<Double> expectedValuesSkinSubstitutes = Arrays.asList(
//        1742.89,
//        3197.73,
//        1742.89,
//        3197.73,
//        1862.88,
//        3317.72,
//        1856.75,
//        3311.59,
//        2614.33,
//        6562.5,
//        2842.05,
//        6737.04,
//        536.76,
//        530.63,
//        1764.62,
//        3219.46,
//        1764.62,
//        3219.46,
//        1862.88,
//        3317.72,
//        1856.75,
//        3311.59,
//        4069.17,
//        1856.75,
//        4189.16,
//        0.0

        1862.88,
        3317.72,
        1856.75,
        3311.59,
        1862.88,
        3317.72,
        1856.75,
        3311.59,
        2854.31,
        6755.43,
        2842.05,
        6737.04,
        536.76,
        530.63,
        1764.62,
        3219.46,
        1764.62,
        3219.46,
        1862.88,
        3317.72,
        1856.75,
        3311.59,
        4189.16,
        1970.61,
        4303.02,
        0.0

  );

  public final String sqlQueryContrastAgents = getOpps2020SqlQuery(
          "OPPS2020DRUGCA%",
          "v102 REGRESSION OPPS 2020 Contrast A")
  ;

  public final List<Double> expectedValuesContrastAgents = Arrays.asList(
//        195.71,
//        410.12,
//        731.23,
//        215.3,
//        429.71,
//        750.82,
//        391.42,
//        1230.37,
//        1462.46,
//        436.36,
//        217.44,
//        431.85,
//        752.96,
//        215.3,
//        429.71,
//        750.82,
//        391.42,
//        1141.35,
//        1376.24,
//        215.3

        215.3,
        429.71,
        750.82,
        215.3,
        429.71,
        750.82,
        430.6,
        1289.14,
        1501.64,
        436.36,
        217.44,
        431.85,
        752.96,
        215.3,
        429.71,
        750.82,
        411.01,
        1160.94,
        1395.83,
        234.89
  );

  public final String sqlQueryUpdateModifierPoLogic = getOpps2020SqlQuery(
          "ADS2101PO%",
          "v102 REGRESSION OPPS 2020 Mod PO")
  ;

  public final List<Double> expectedValuesUpdateModifierPoLogic = Arrays.asList(
        49.81,
        498.05,
        12.6,
        124.51,
        124.51,
        49.81,
        199.22,
        124.51,
        21.73
  );

  public final String sqlQueryJPackagingUpdates = getOpps2020SqlQuery(
          "%JPACK%",
          "v1023 REGRESSION OPPS 2020 J Pack Update")
          ;

  public final List<Double> expectedValuesJPackagingUpdates = Arrays.asList(
          3635.83,
          4495.78,
          4434.58,
          4479.74,
          3771.9,
          5401.61,
          24226.44,
          5270.13
  );

}
