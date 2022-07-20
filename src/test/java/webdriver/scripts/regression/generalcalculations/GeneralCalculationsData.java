package webdriver.scripts.regression.generalcalculations;

public class GeneralCalculationsData {

  public final static String getMedicalServiceAssignmentSql =
          "SELECT count (financialrecnum) FROM chargeitem2 " +
          "WHERE serviceTIMESTAMP >= TO_DATE('2019 01 01 00 00 00', 'YYYY MM DD HH24 MI SS') " +
          "AND serviceTIMESTAMP <= TO_DATE('2019 12 31 23 59 59', 'YYYY MM DD HH24 MI SS') " +
          "AND medsvcdefmedsvcanalysisname = 'V10.2 REGRESSION Med Serv Assign'"
  ;
  public final static int getMedicalServiceAssignmentExpectedValue = 2435;
  public final static int getMedicalServiceAssignmentExpectedValueEchelon = 3651;

//  public final static String getPriceListEncounterAssignmentSql =
//          "SELECT * FROM chargeitem2 " +
//          "WHERE financialrecnum like 'ADS2101PO%'" +
//          "AND estimatedcharges15 != null"
//  ;

  public final static String getPriceListEncounterAssignmentSql =
          "SELECT count (financialrecnum) FROM chargeitem2 " +
          "WHERE financialrecnum like 'ADS2101PO%'" +
          "AND estimatedcharges15 != null"
  ;
  public final static int getPriceListEncounterAssignmentExpectedValue = 25;

}
