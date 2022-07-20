package webdriver.scripts.regression.ipps2021calculations;

import java.util.Arrays;
import java.util.List;
import webdriver.core.Driver;

public class Ipps2021Data {

  public String getIpps2021SqlQuery(String finRecNum, String name) {
    String sql = "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
            + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
            + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
            + " AND t1.FINANCIALRECNUM like ('" + finRecNum + "')"
            + " AND t3.name = '" + name + "'"
            + " ORDER by t1.FINANCIALRECNUM"
            ;
    return sql;
  }

  public String getIpps2021SqlQuery(String finRecNum, String name, String version) {
    String sql = "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
            + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
            + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
            + " AND t1.FINANCIALRECNUM like ('" + finRecNum + "')"
            + " AND t3.name = '" + version + name + "'"
            + " ORDER by t1.FINANCIALRECNUM"
            ;
    return sql;
  }

  public final String sqlQueryIpps2021TransfersA = getIpps2021SqlQuery(
          "IPPSFY21TFA%",
          "v10.3 REGRESSION 2021 IPPS Trans A 1-1"
  );

  public final List<Double> expectedValuesIpps2021TransfersA = Arrays.asList(
        22144.44,
        15932.87,
        13743.46,
        16322.73,
        22144.44,
        15932.87,
        13743.46,
        16322.73,
        22144.44,
        15932.87,
        13743.46,
        16322.73, //the new values should start here, but do not 13268.92, 9639.74
        23150.91,
        23150.91,
        27642.6
  );

  public final String sqlQueryIpps2021TransfersB = getIpps2021SqlQuery(
          "IPPSFY21TFB%",
          "v10.3 REGRESSION 2021 IPPS Trans B 2-1"
  );

  public final List<Double> expectedValuesIpps2021TransfersB = Arrays.asList(
        5717.63,
        4554.03,
        9170.64,
        14793.09,
        10539.55,
        23150.91,
        19645.23,
        11966.97,
        14065.11,
        14614.75
  );

  public final String sqlQueryIpps2021TransfersC = getIpps2021SqlQuery(
          "IPPSFY21TFC%",
          "v10.3 REGRESSION 2021 IPPS Trans C 1-1"
  );

  public final List<Double> expectedValuesIpps2021TransfersC = Arrays.asList(
        33247.07,
        13743.46,
        163604.54,
        59211.89,
        9586.92,
        48893.77,
        18231.16,
        17721.45,
        12797.26,
        467.89,
        8502.96,
        8502.96
  );

  public final String sqlQueryIpps2021TransfersD = getIpps2021SqlQuery(
          "IPPSFY21TFD%",
          "v10.3 REGRESSION 2021 IPPS Trans D 1-1"
  );

  public final List<Double> expectedValuesIpps2021TransfersD = Arrays.asList(
        32358.87,
        8160.3,
        163604.54,
        6160.88,
        7375.87,
        13193.65,
        467.89,
        7079.29,
        467.89,
        4824.93
  );

  public final String sqlQueryIpps2021Transfers0282K1 = getIpps2021SqlQuery(
          "IPPSFY21TFK%",
          "v10.3 REGRESSION 2021 IPPS Trans K 1-1"
  );

  public final List<Double> expectedValuesIpps2021Transfers0282K1 = Arrays.asList(
        11041.82,
        12839.87,
        13743.46,
        9886.61,
        10652.57,
        13942.96,
        8816.39,
        9489.38
  );

  public final String sqlQueryIpps2021Transfers0282K2 = getIpps2021SqlQuery(
          "IPPSFY21TFK%",
          "v10.3 REGRESSION 2021 IPPS Trans K 1-2"
  );

  public final List<Double> expectedValuesIpps2021Transfers0282K2 = Arrays.asList(
        14887.43,
        10804.84,
        8450.92,
        7626.08,
        7072.06,
        6752.82,
        13942.96,
        16674.09,
        8816.39,
        9489.38
  );

  public final String sqlQueryIpps2021Transfers0282L = getIpps2021SqlQuery(
          "IPPSFY21TFL%",
          "v10.3 REGRESSION 2021 IPPS Trans L 1-1"
  );

  public final List<Double> expectedValuesIpps2021Transfers0282L = Arrays.asList(
        4655.4,
        6820.66,
        6123.05,
        5509.8,
        7169.61,
        9999.5,
        10130.85,
        7474.42,
        5953.91,
        6027.12
  );

  public final String sqlQueryIpps2021Transfers0282M = getIpps2021SqlQuery(
          "IPPSFY21TFM%",
          "v10.3 REGRESSION 2021 IPPS Trans M 1-1"
  );

  public final List<Double> expectedValuesIpps2021Transfers0282M = Arrays.asList(
        8594.16,
        8125.17,
        6999.63,
        7259.32,
        11906.81,
        16673.52,
        14756.56,
        6313.36,
        7317.07,
        6954.45,
        15545.84,
        18971.79
  );

  public final String sqlQueryIpps2021DeviceCreditA = getIpps2021SqlQuery(
          "IPPSFY21DCA%",
          "v10.3 REGRESSION 2021 IPPS DC A"
  );

  public final List<Double> expectedValuesIpps2021DeviceCreditA = Arrays.asList(
        162614.54,
        13075.11,
        35055.24,
        11112.25,
        12379.56,
        23785.17,
        13241.2,
        11910.89,
        8538.91,
        16170.49,
        12278.92,
        10153.32
  );

  public final String sqlQueryIpps2021DeviceCreditB = getIpps2021SqlQuery(
          "IPPSFY21DCB%",
          "v10.3 REGRESSION 2021 IPPS DC B"
  );

  public final List<Double> expectedValuesIpps2021DeviceCreditB = Arrays.asList(
        9103.95,
        10583.5,
        32710.02,
        15299.35,
        17427.16,
        22795.17,
        12251.2,
        10920.89,
        7548.91,
        15180.49,
        11288.92,
        9163.32
  );

  public final String sqlQueryIpps2021DeviceCreditE = getIpps2021SqlQuery(
          "IPPSFY21DCE%",
          "v10.3 REGRESSION 2021 IPPS DC E"
  );

  public final List<Double> expectedValuesIpps2021DeviceCreditE = Arrays.asList(
        163604.54,
        14614.75,
        23406.59,
        30960.41,
        18176.53,
        24775.17,
        14231.2,
        12900.89,
        9528.91,
        17160.49,
        13268.92,
        11143.32
  );

  public final String sqlQueryIpps2021DeviceCreditF = getIpps2021SqlQuery(
          "IPPSFY21DCF%",
          "v10.3 REGRESSION 2021 IPPS DC F"
  );

  public final List<Double> expectedValuesIpps2021DeviceCreditF = Arrays.asList(
        20843.36,
        22049.77,
        10159.15,
        22853.48,
        5173.0,
        8609.18,
        6319.14,
        6148.38,
        16243.01,
        28723.31
  );


  public final String sqlQueryIpps2021AddOnTechA1 = getIpps2021SqlQuery(
          "IPPSFY21AOTA%",
          "v103 REGRESSION 2021 IPPS AOT A1"
  );

  public final List<Double> expectedValuesIpps2021AddOnTechA1 = Arrays.asList(
        11073.81,
        9136.26,
        11850.59,
        12741.4,
        28466.59,
        47325.04,
        33213.35,
        19170.61,
        41390.51,
        81185.21
  );

  public final String sqlQueryIpps2021AddOnTechA2 = getIpps2021SqlQuery(
          "IPPSFY21AOTA%",
          "v103 REGRESSION 2021 IPPS AOT A2"
  );

  public final List<Double> expectedValuesIpps2021AddOnTechA2 = Arrays.asList(
        11073.81,
        9348.34,
        11850.59,
        12953.49,
        30433.82,
        47718.49,
        33606.79,
        19170.61,
        41783.96,
        81185.21
  );

  public final String sqlQueryIpps2021AddOnTechD = getIpps2021SqlQuery(
          "IPPSFY21AOTD%",
          "v103 REGRESSION 2021 IPPS AOT D"
  );

  public final List<Double> expectedValuesIpps2021AddOnTechD = Arrays.asList(
        38152.58,
        12667.62,
        32239.34,
        16146.75,
        28817.0,
        13887.8,
        27928.11,
        7815.76,
        31974.51,
        18453.15
  );

  public final String sqlQueryIpps2021AddOnTechE = getIpps2021SqlQuery(
          "IPPSFY21AOTE%",
          "v103 REGRESSION 2021 IPPS AOT E"
  );

  public final List<Double> expectedValuesIpps2021AddOnTechE = Arrays.asList(
        34795.01,
        17994.86,
        28974.33,
        33346.67,
        32428.94,
        32013.06,
        30084.35,
        13825.17,
        29907.75,
        12006.67
  );

  public final String sqlQueryIpps2021AddOnTechF = getIpps2021SqlQuery(
          "IPPSFY21AOTF%",
          "v103 REGRESSION 2021 IPPS AOT F"
  );

  public final List<Double> expectedValuesIpps2021AddOnTechF = Arrays.asList(
        43370.23,
        12716.38,
        42168.61,
        17496.32,
        38915.25,
        13887.73,
        38026.35,
        7815.69,
        42032.91,
        18453.15
  );

  public final String sqlQueryIpps2021ExcludedServices = getIpps2021SqlQuery(
          "IPPSFY21AOTD%",
          "v103 REGRESSION 2021 IPPS Excluded Serv"
  );

  public final List<Double> expectedValuesIpps2021ExcludedServices = Arrays.asList(
        36653.4,
        11168.43,
        31439.86,
        15313.14,
        28146.63,
        10535.94,
        27257.74,
        4463.91,
        30940.92,
        17691.04
  );

  public final String sqlQueryIpps2021PsychComorbidity =
        "SELECT ENCOUNTERID, psychcombinedcomorbidityfactor"
        + " FROM ENCOUNTER2"
        + " WHERE ENCOUNTERID LIKE '2021PSYCHRB%'"
;

  public final List<Double> expectedValuesIpps2021PsychComorbidity = Arrays.asList(
        1.1433,
        1.38603402,
        1.09,
        1.57831078,
        1.0,
        1.0914,
        1.37404538,
        1.1021
  );

}
