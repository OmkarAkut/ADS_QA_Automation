package webdriver.scripts.regression.ipps2019calculations;

import java.util.Arrays;
import java.util.List;

public class Ipps2019CalculationsData {

  public String getIpps2019SqlQuery(String finRecNum, String name) {
    String sql = "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
            + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
            + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
            + " AND t1.FINANCIALRECNUM like ('" + finRecNum + "')"
            + " AND t3.name = '" + name + "'"
            + " ORDER by t1.FINANCIALRECNUM"
            ;
    return sql;
  }

  public final String sqlQueryIpps2019TransfersA = getIpps2019SqlQuery(
          "IPPSFY19TFA%",
          "v10 REGRESSION 2019 IPPS Tran A MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019TransfersA = Arrays.asList(
        20598.82,
        15419.63,
        12721.78,
        15501.98,
        20598.82,
        15419.63,
        12721.78,
        15501.98,
        20598.82,
        15419.63,
        12721.78,
        15501.98
  );

  public final String sqlQueryIpps2019TransfersB = getIpps2019SqlQuery(
          "IPPSFY19TFB%",
          "v10 REGRESSION 2019 IPPS Tran B MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019TransfersB = Arrays.asList(
        9021.77,
        8078.29,
        8017.55,
        5838.16,
        4808.81,
        4581.79,
        6764.51,
        5919.8,
        5591.12,
        7081.26
  );

  public final String sqlQueryIpps2019TransfersC = getIpps2019SqlQuery(
          "IPPSFY19TFC%",
          "v10 REGRESSION 2019 IPPS Tran C MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019TransfersC = Arrays.asList(
        30664.28,
        12721.78,
        147194.3,
        55505.23,
        9929.08,
        51478.56,
        17583.63,
        8824.12,
        8377.67,
        8377.67
  );

  public final String sqlQueryIpps2019TransfersD = getIpps2019SqlQuery(
          "IPPSFY19TFD%",
          "v10 REGRESSION 2019 IPPS Tran D MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019TransfersD = Arrays.asList(
        30899.86,
        7844.23,
        147194.3,
        6000.57,
        7126.85,
        11370.39,
        8824.12,
        11954.48,
        8824.12,
        4579.26
  );

  public final String sqlQueryIpps2019Transfers0282K1 = getIpps2019SqlQuery(
          "IPPSFY19TFK0%",
          "v10 REGRESSION 2019 IPPS Tran K MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019Transfers0282K1 = Arrays.asList(
        10533.36,
        12756.99,
        12721.78,
        9058.8,
        9774.36
  );

  public final String sqlQueryIpps2019Transfers0282K2 = getIpps2019SqlQuery(
          "IPPSFY19TFK0%",
          "v10 REGRESSION 2019 IPPS Tran K MSDRG1-2"
  );

  public final List<Double> expectedValuesIpps2019Transfers0282K2 = Arrays.asList(
        15396.22,
        10539.59,
        7853.21,
        7437.16,
        6881.23,
        6310.75
  );

  public final String sqlQueryIpps2019Transfers0282L = getIpps2019SqlQuery(
          "IPPSFY19TFL0%",
          "v10 REGRESSION 2019 IPPS Tran L MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019Transfers0282L = Arrays.asList(
        4581.79,
        6764.51,
        5919.8,
        5591.12,
        7081.26,
        8936.62,
        9338.31,
        6960.48,
        5843.86,
        6110.22
  );

  public final String sqlQueryIpps2019Transfers0282M = getIpps2019SqlQuery(
          "IPPSFY19TFM0%",
          "v10 REGRESSION 2019 IPPS Tran M MSDRG1-1"
  );

  public final List<Double> expectedValuesIpps2019Transfers0282M = Arrays.asList(
        8567.87,
        7971.42,
        6869.64,
        7210.62,
        11013.26,
        15805.15,
        13071.92,
        5668.8,
        7330.37,
        6359.54
  );

  public final String sqlQueryIpps2019DeviceCreditA = getIpps2019SqlQuery(
          "IPPSFY19DCA%",
          "v10 REGRESSION 2019 IPPS DC A MSDRG1"
  );

  public final List<Double> expectedValuesIpps2019DeviceCreditA = Arrays.asList(
        144962.61,
        10922.24,
        33006.17,
        10870.08,
        11961.26
  );

  public final String sqlQueryIpps2019DeviceCreditB = getIpps2019SqlQuery(
          "IPPSFY19DCB%",
          "v10 REGRESSION 2019 IPPS DC B MSDRG2"
  );

  public final List<Double> expectedValuesIpps2019DeviceCreditB = Arrays.asList(
        8515.93,
        9611.47,
        22994.97,
        8813.7,
        15520.83
  );

  public final String sqlQueryIpps2019DeviceCreditE = getIpps2019SqlQuery(
          "IPPSFY19DCE%",
          "v10 REGRESSION 2019 IPPS DC E MSDRG3"
  );

  public final List<Double> expectedValuesIpps2019DeviceCreditE = Arrays.asList(
        145952.61,
        13426.77,
        21631.51,
        28200.96,
        17954.44
  );

  public final String sqlQueryIpps2019DeviceCreditF = getIpps2019SqlQuery(
          "IPPSFY19DCF%",
          "v10 REGRESSION 2019 IPPS DC F MSDRG4"
  );

  public final List<Double> expectedValuesIpps2019DeviceCreditF = Arrays.asList(
        25315.31,
        21107.34,
        9451.47,
        20060.09,
        4461.83,
        7018.3,
        5625.69,
        5333.19,
        16647.19,
        27961.05
  );

  public final String sqlQueryIpps2019AddOnTechA1 = getIpps2019SqlQuery(
          "IPPSFY19AOTA%",
          "v10.1 REGRESSION 2019 IPPS AOT A1"
  );

  public final List<Double> expectedValuesIpps2019AddOnTechA1 = Arrays.asList(
        11109.81,
        8629.42,
        11252.65,
        11918.76,
        28290.42,
        46850.61,
        32952.58,
        18429.91,
        41350.3,
        75618.3
  );

  public final String sqlQueryIpps2019AddOnTechA2 = getIpps2019SqlQuery(
          "IPPSFY19AOTA%",
          "v10.1 REGRESSION 2019 IPPS AOT A2"
  );

  public final List<Double> expectedValuesIpps2019AddOnTechA2 = Arrays.asList(
        11109.81,
        8841.51,
        11252.65,
        12130.85,
        30257.64,
        47244.05,
        33346.03,
        18429.91,
        41743.74,
        75618.3
  );

  public final String sqlQueryIpps2019AddOnTechD = getIpps2019SqlQuery(
          "IPPSFY19AOTD%",
          "v10.1 REGRESSION 2019 IPPS AOT D"
  );

  public final List<Double> expectedValuesIpps2019AddOnTechD = Arrays.asList(
        36542.01,
        12972.82,
        31924.75,
        16543.85,
        28703.13,
        13607.95,
        27892.28,
        7661.71,
        31632.93,
        17628.18
  );

  public final String sqlQueryIpps2019AddOnTechE = getIpps2019SqlQuery(
          "IPPSFY19AOTE%",
          "v10.1 REGRESSION 2019 IPPS AOT E"
  );

  public final List<Double> expectedValuesIpps2019AddOnTechE = Arrays.asList(
        31648.51,
        17685.29,
        28734.6,
        30904.4,
        31830.61,
        32884.61,
        29755.67,
        13268.55,
        29720.49,
        11731.73
  );

  public final String sqlQueryIpps2019AddOnTechF = getIpps2019SqlQuery(
          "IPPSFY19AOTF%",
          "v10.1 REGRESSION 2019 IPPS AOT F"
  );

  public final List<Double> expectedValuesIpps2019AddOnTechF = Arrays.asList(
        43056.73,
        13021.58,
        41983.14,
        14202.35,
        38801.37,
        13607.88,
        37990.53,
        7661.64,
        41691.33,
        17236.46
  );

}
