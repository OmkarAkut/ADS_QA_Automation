package webdriver.scripts.datamaintenance.maintaindata;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	DataMaintenanceAtoZTabPageLoads.class,
	DataMaintenanceTreePageLoads.class,
	RunEncounterServiceClassificationScheme.class,
	CreateEditDeleteNewStructurePopulations.class,
	CreateDeleteCostMethodDeptMasterDischargeStatus.class,
	VerifyIsRebillUnderBillTypes.class,
	NewEditASCSchemes.class,
	EditConsumerHistoryRecord.class,
})




public class MaintaindataTestSuite {

}
