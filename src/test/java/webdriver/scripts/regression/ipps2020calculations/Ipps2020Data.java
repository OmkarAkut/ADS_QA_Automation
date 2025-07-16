package webdriver.scripts.regression.ipps2020calculations;

import java.util.Arrays;
import java.util.List;

public class Ipps2020Data {

  public String getIpps2020SqlQuery(String finRecNum, String name) {
    String sql = "SELECT t3.name, t1.FINANCIALRECNUM, t1.CALCULATEDPAYMENT"
            + " FROM encounterpricinglog2 t1, lineofbusinesscalcresults2 t2, contract2 t3"
            + " WHERE t1.lobcalcresultsid = t2.objectid and t2.contractid = t3.objectid"
            + " AND t1.FINANCIALRECNUM like ('" + finRecNum + "')"
            + " AND t3.name = '" + name + "'"
            + " ORDER by t1.FINANCIALRECNUM"
            ;
    return sql;
  }

  public final String sqlQueryIpps2020TransfersA = getIpps2020SqlQuery(
          "IPPSFY20TFA%",
          "v10.1 REGRESSION 2020 IPPS Trans A 1-1"
  );

  public final List<Double> expectedValuesIpps2020TransfersA = Arrays.asList(
        21430.83,
        15594.11,
        13212.01,
        15938.97,
        21430.83,
        15594.11,
        13212.01,
        15938.97,
        21430.83,
        15594.11,
        13212.01,
        15938.97,
        22877.15,
        22877.15,
        27314.62
  );

  public final String sqlQueryIpps2020TransfersB = getIpps2020SqlQuery(
          "IPPSFY20TFB%",
          "v10.1 REGRESSION 2020 IPPS Trans B 2-1"
  );

  public final List<Double> expectedValuesIpps2020TransfersB = Arrays.asList(
        5901.29,
        4606.74,
        9222.33,
        14395.18,
        10378.41,
        22877.15,
        19777.93,
        11671.79,
        13280.72,
        13910.88
  );

  public final String sqlQueryIpps2020TransfersC = getIpps2020SqlQuery(
          "IPPSFY20TFC%",
          "v10.1 REGRESSION 2020 IPPS Trans C 1-1"
  );

  public final List<Double> expectedValuesIpps2020TransfersC = Arrays.asList(
        31824.21,
        13212.01,
        155465.27,
        56795.29,
        9500.0,
        51403.96,
        17380.02,
        8576.2,
        8572.47,
        8572.47
  );

  public final String sqlQueryIpps2020TransfersD = getIpps2020SqlQuery(
          "IPPSFY20TFD%",
          "v10.1 REGRESSION 2020 IPPS Trans D 1-1"
  );

  public final List<Double> expectedValuesIpps2020TransfersD = Arrays.asList(
        31973.97,
        7984.46,
        155465.27,
        6047.12,
        7236.5,
        12167.1,
        8576.2,
        11872.59,
        8576.2,
        4584.87
  );

  public final String sqlQueryIpps2020Transfers0282K1 = getIpps2020SqlQuery(
          "IPPSFY20TFK%",
          "v10.1 REGRESSION 2020 IPPS Trans K 1-1"
  );

  public final List<Double> expectedValuesIpps2020Transfers0282K1 = Arrays.asList(
        11037.44,
        12732.39,
        13212.01,
        9308.51,
        10200.8,
        13780.32,
        16559.59
  );

  public final String sqlQueryIpps2020Transfers0282K2 = getIpps2020SqlQuery(
          "IPPSFY20TFK%",
          "v10.1 REGRESSION 2020 IPPS Trans K 1-2"
  );

  public final List<Double> expectedValuesIpps2020Transfers0282K2 = Arrays.asList(
        14806.91,
        10828.36,
        7972.68,
        7659.66,
        7039.17,
        6931.66,
        13780.32,
        16559.59
  );

  public final String sqlQueryIpps2020Transfers0282L = getIpps2020SqlQuery(
          "IPPSFY20TFL%",
          "v10.1 REGRESSION 2020 IPPS Trans L 1-1"
  );

  public final List<Double> expectedValuesIpps2020Transfers0282L = Arrays.asList(
        4628.62,
        6692.28,
        6110.97,
        6084.14,
        7001.93,
        9499.07,
        9706.29,
        7372.63,
        6048.5,
        5937.21
  );

  public final String sqlQueryIpps2020Transfers0282M = getIpps2020SqlQuery(
          "IPPSFY20TFM%",
          "v10.1 REGRESSION 2020 IPPS Trans M 1-1"
  );

  public final List<Double> expectedValuesIpps2020Transfers0282M = Arrays.asList(
        8972.3,
        8032.02,
        7290.56,
        7403.15,
        11214.55,
        16783.41,
        13906.84,
        6105.6,
        7146.24,
        6941.35,
        14341.84,
        18191.08
  );

  public final String sqlQueryIpps2020DeviceCreditA = getIpps2020SqlQuery(
          "IPPSFY20DC%",
          "v10.1 REGRESSION 2020 IPPS DC A"
  );

  public final List<Double> expectedValuesIpps2020DeviceCreditA = Arrays.asList(
        154475.27,
        12290.72,
        33808.83,
        11118.72,
        12884.44,
        22478.55,
        12646.04
  );

  public final String sqlQueryIpps2020DeviceCreditB = getIpps2020SqlQuery(
          "IPPSFY20DCB%",
          "v10.1 REGRESSION 2020 IPPS DC B"
  );

  public final List<Double> expectedValuesIpps2020DeviceCreditB = Arrays.asList(
        8854.93,
        10270.2,
        28657.36,
        9729.32,
        15995.13,
        21488.55,
        11656.04
  );

  public final String sqlQueryIpps2020DeviceCreditE = getIpps2020SqlQuery(
          "IPPSFY20DCE%",
          "v10.1 REGRESSION 2020 IPPS DC E"
  );

  public final List<Double> expectedValuesIpps2020DeviceCreditE = Arrays.asList(
        155465.27,
        13910.88,
        22788.32,
        29678.22,
        18059.83,
        23468.55,
        13636.04
  );

  public final String sqlQueryIpps2020DeviceCreditF = getIpps2020SqlQuery(
          "IPPSFY20DCF%",
          "v10.1 REGRESSION 2020 IPPS DC F"
  );

  public final List<Double> expectedValuesIpps2020DeviceCreditF = Arrays.asList(
        22769.67,
        21636.1,
        10008.17,
        21522.24,
        4895.61,
        7147.04,
        6619.24,
        6045.73,
        17267.28,
        28481.27
  );


  public final String sqlQueryIpps2020AddOnTechA1 = getIpps2020SqlQuery(
          "IPPSFY20AOTA%",
          "v10.1 REGRESSION 2020 IPPS AOT A1"
  );

  public final List<Double> expectedValuesIpps2020AddOnTechA1 = Arrays.asList(
        10857.71,
        8780.24,
        11649.89,
        12286.44,
        28681.77,
        46651.2,
        33052.81,
        18524.03,
        41424.29,
        79761.33
  );

  public final String sqlQueryIpps2020AddOnTechA2 = getIpps2020SqlQuery(
          "IPPSFY20AOTA%",
          "v10.1 REGRESSION 2020 IPPS AOT A2"
  );

  public final List<Double> expectedValuesIpps2020AddOnTechA2 = Arrays.asList(
        10857.71,
        8992.33,
        11649.89,
        12498.53,
        30649.0,
        47044.65,
        33446.25,
        18524.03,
        41817.74,
        79761.33
  );

  public final String sqlQueryIpps2020AddOnTechD = getIpps2020SqlQuery(
          "IPPSFY20AOTD%",
          "v10.1 REGRESSION 2020 IPPS AOT D"
  );

  public final List<Double> expectedValuesIpps2020AddOnTechD = Arrays.asList(
        36777.6,
        12801.64,
        31893.95,
        17106.05,
        28706.35,
        13694.92,
        27908.0,
        7771.8,
        31758.44,
        18240.09
  );

  public final String sqlQueryIpps2020AddOnTechE = getIpps2020SqlQuery(
          "IPPSFY20AOTE%",
          "v10.1 REGRESSION 2020 IPPS AOT E"
  );

  public final List<Double> expectedValuesIpps2020AddOnTechE = Arrays.asList(
        32786.79,
        17793.23,
        28825.56,
        31634.57,
        31943.12,
        31707.8,
        29892.74,
        13404.1,
        29682.71,
        11801.65
  );

  public final String sqlQueryIpps2020AddOnTechF = getIpps2020SqlQuery(
          "IPPSFY20AOTF%",
          "v10.1 REGRESSION 2020 IPPS AOT F"
  );

  public final List<Double> expectedValuesIpps2020AddOnTechF = Arrays.asList(
        43101.27,
        12850.4,
        41952.33,
        15557.76,
        38804.6,
        13694.85,
        38006.24,
        7771.73,
        41816.84,
        18240.09
  );

  public final String sqlQueryIpps2020ExcludedServices = getIpps2020SqlQuery(
          "IPPSFY20AOTD%",
          "v10.1 REGRESSION 2020 IPPS Excluded Serv"
  );

  public final List<Double> expectedValuesIpps2020ExcludedServices = Arrays.asList(
          35278.41,
          11302.46,
          31223.58,
          14402.45,
          28035.98,
          10343.06,
          27237.63,
          4419.94,
          30724.85,
          17232.73
  );

  public final String sqlQueryIpps2020PsychComorbidity =
        "SELECT ENCOUNTERID, psychcombinedcomorbidityfactor"
        + " FROM ENCOUNTER2"
        + " WHERE ENCOUNTERID LIKE '2020PSYCHRB%'"
  ;

  public final String sqlQueryIpps2021PsychComorbidity =
        "SELECT ENCOUNTERID, psychcombinedcomorbidityfactor"
        + " FROM ENCOUNTER2"
        + " WHERE ENCOUNTERID LIKE '2021PSYCHRB%'"
;

  public final List<Double> expectedValuesIpps2020PsychComorbidity = Arrays.asList(
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
