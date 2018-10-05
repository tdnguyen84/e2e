package automationFramework;

import static org.testng.Assert.assertEquals;

import pageObjects.Login;
import pageObjects.Toolkit;
import pageObjects.Toolkit_DilutionCalculator;
import utilities.commonFunctions;
import utilities.constant;
import utilities.setup;

public class dilutionCalculator {

	public static void Execute() {
		setup.setUp();
		
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		Toolkit.dilutionCalculator().click();
//		commonFunctions.waitUntilElementVisible(Toolkit_DilutionCalculator.textWelcomeDilution());
//		Toolkit_DilutionCalculator.btStartCalculator().click();
		assertEquals(Toolkit_DilutionCalculator.dilutionInstructionText().isDisplayed(), true);
		Toolkit_DilutionCalculator.inputFounder().sendKeys("100");
		Toolkit_DilutionCalculator.inputTotalShares().sendKeys("1000000");
		Toolkit_DilutionCalculator.inputPreMoney().sendKeys("8000000");
		Toolkit_DilutionCalculator.inputProposed().sendKeys("3000000");
		Toolkit_DilutionCalculator.inputStockOptionPool().click();
		assertEquals(Toolkit_DilutionCalculator.ownershipPercent("27.27%").isDisplayed(), true);
		Toolkit_DilutionCalculator.inputStockOptionPool().sendKeys("10");
		Toolkit_DilutionCalculator.btCalculate().click();
		commonFunctions.waitUntilElementVisible(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Results']"));
		
		
		assertEquals(Toolkit_DilutionCalculator.txtFounder().getText(), "1,000,000");
		assertEquals(Toolkit_DilutionCalculator.txtFounderDiluted().getText(), "Diluted: 37.3%");
		assertEquals(Toolkit_DilutionCalculator.txtFounderPercentage().getText(), "62.7%\nDiluted: 37.3%");
		
		assertEquals(Toolkit_DilutionCalculator.txtInvestor().getText(), "434,783");
		assertEquals(Toolkit_DilutionCalculator.txtInvestorPercentage().getText(), "27.3%");
		assertEquals(Toolkit_DilutionCalculator.txtEOP().getText(), "159,420");
		assertEquals(Toolkit_DilutionCalculator.txtEOPPercentage().getText(), "10%");
		assertEquals(Toolkit_DilutionCalculator.txtTotal().getText(), "1,594,203");
		assertEquals(Toolkit_DilutionCalculator.txtTotalPercentage().getText(), "100%");
		
		assertEquals(Toolkit_DilutionCalculator.txtEffectivePreMoney().getText(), "$6,900,000");
		assertEquals(Toolkit_DilutionCalculator.txtPreMoney().getText(), "$8,000,000");
		assertEquals(Toolkit_DilutionCalculator.txtPostMoney().getText(), "$11,000,000");
		assertEquals(Toolkit_DilutionCalculator.txtSeries().getText(), "$6.9 / share");
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Founders will be diluted by: '][span='37.3%']").isDisplayed(), true);
		
		setup.tearDown();
		
		
	}

}
