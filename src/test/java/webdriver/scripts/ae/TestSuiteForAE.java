package webdriver.scripts.ae;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	EXECUTEJobPopUp.class,
	ExecuteNowFunctionality.class,
	ScheduleExecutionFunctionality.class,
	UpdateAutomationEngineSequenceScreen.class,
	VIEWCANCELScheduledJobs.class,
	VIEWJobExecutionDetails.class,
})
public class TestSuiteForAE {

}
