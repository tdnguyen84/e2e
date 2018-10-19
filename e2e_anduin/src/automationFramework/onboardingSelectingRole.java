package automationFramework;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Deals_Dashboard;
import pageObjects.Login;
import pageObjects.Onboarding;
import pageObjects.Settings_Page;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;

public class onboardingSelectingRole extends testBase {
	public static void mainOnbaordingSelectingRole(String selectedRole) {
		String testCaseName = "Selecting " + selectedRole + " Role During Onboarding Test";
		Log.startTestCase(testCaseName);
		
		if(selectedRole == "Deal Observer") {
			Login.signIn(constant.globalVariables.onboardingObserverUser, constant.globalVariables.testPass);
			commonFunctions.waitUntilElementVisible(Onboarding.welcomeObserverModalTitle());
			assertEquals(Onboarding.getWelcomeMsg(), "As CFO, you're recommended to participate as a Deal Observer to easily keep track of the deal progress.");
			assertEquals(Onboarding.selectedRole("Deal Observer").isDisplayed(), true);
			assertEquals(Onboarding.unselectedRole("Deal Team Member").isDisplayed(), true);
			assertEquals(Onboarding.btConfrim().isEnabled(), true);
			
			Onboarding.unselectedRole("Deal Team Member").click();
			assertEquals(Onboarding.selectedRole("Deal Team Member").isDisplayed(), true);
			assertEquals(Onboarding.unselectedRole("Deal Observer").isDisplayed(), true);
			assertEquals(Onboarding.btConfrim().isEnabled(), true);
			
			Onboarding.btConfrim().click();
			commonFunctions.waitUntilElementVisible(Onboarding.observerStep1ModalTitle());
			assertEquals(Onboarding.dotSlickActive("0").isDisplayed(), true);
			assertEquals(Onboarding.observerStep1Sub1().isDisplayed(), true);
			assertEquals(Onboarding.observerStep1Sub2().isDisplayed(), true);
			assertEquals(Onboarding.observerStep1Sub3().isDisplayed(), true);
			assertEquals(Onboarding.btBetterInsight().isEnabled(), true);
			
			Onboarding.btBetterInsight().click();
			assertEquals(Onboarding.dotSlickActive("1").isDisplayed(), true);
			assertEquals(Onboarding.observerStep2Sub1().isDisplayed(), true);
			assertEquals(Onboarding.observerStep2Sub2().isDisplayed(), true);
			assertEquals(Onboarding.observerStep2Sub3().isDisplayed(), true);
			assertEquals(Onboarding.btMoreTransparency().isEnabled(), true);
			
			Onboarding.btMoreTransparency().click();
			assertEquals(Onboarding.dotSlickActive("2").isDisplayed(), true);
			assertEquals(Onboarding.observerStep3Sub1().isDisplayed(), true);
			assertEquals(Onboarding.observerStep3Sub2().isDisplayed(), true);
			assertEquals(Onboarding.observerStep3Sub3().isDisplayed(), true);
			assertEquals(Onboarding.btGetStarted().isEnabled(), true);
			
			
		} else {
			Login.signIn(constant.globalVariables.onboardingDealTeamUser, constant.globalVariables.testPass);
			commonFunctions.waitUntilElementVisible(Onboarding.welcomeObserverModalTitle());
			assertEquals(Onboarding.getWelcomeMsg(), "As CEO, you're recommended to participate as a Deal Team Member to closely monitor deal activities. ");
			assertEquals(Onboarding.selectedRole("Deal Team Member").isDisplayed(), true);
			assertEquals(Onboarding.unselectedRole("Deal Observer").isDisplayed(), true);
			assertEquals(Onboarding.btConfrim().isEnabled(), true);
			
			Onboarding.unselectedRole("Deal Observer").click();
			assertEquals(Onboarding.selectedRole("Deal Observer").isDisplayed(), true);
			assertEquals(Onboarding.unselectedRole("Deal Team Member").isDisplayed(), true);
			assertEquals(Onboarding.btConfrim().isEnabled(), true);
			
			Onboarding.btConfrim().click();
		}
		
		commonFunctions.waitUntilElementVisible(commonFunctions.getElement(constant.How.XPATH, "//h2[text()='No separate deals yet']"));
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//a/span[text()='Set up my organization']").isDisplayed(), true);
		
		Login.profileAvatar().click();
		Deals_Dashboard.linkSettings().click();
		assertEquals(Settings_Page.defaultRole(selectedRole).isDisplayed(), true);
		
		Log.endTestCase(testCaseName);
	}
	
	@Test(dataProvider = "onboarding_test")
	public void onboardingSelectRoleTest(String selectedRole) {
		mainOnbaordingSelectingRole(selectedRole);
	}
	
	@DataProvider(name = "onboarding_test")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "Deal Observer"},
	   { "Deal Team Member"},
	 };
	}


}
