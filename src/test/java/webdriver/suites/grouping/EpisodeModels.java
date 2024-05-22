package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.episodes.EpisodesAssignmentAndRemovalFourPhasesAds1347;
import webdriver.scripts.episodes.EpisodesCreateAssignRemoval;
import webdriver.scripts.episodes.EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EpisodesAssignmentAndRemovalFourPhasesAds1347.class,
	EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579.class,
	EpisodesCreateAssignRemoval.class,

})
public class EpisodeModels {

}
