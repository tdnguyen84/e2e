package automationFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.rmi.UnknownHostException;

import org.testng.annotations.Test;

import pageObjects.Login;
import pageObjects.Toolkit;
import pageObjects.Toolkit_DilutionCalculator;
import utilities.Log;
import utilities.commonFunctions;
import utilities.constant;


public class dilutionCalculator extends testBase {

	public static void mainDilutionCalculator() throws UnknownHostException {
		String testCaseName = "Dilution Calculator Test";
		Log.startTestCase(testCaseName);
		
		Login.signIn(constant.globalVariables.testCompanyuser, constant.globalVariables.testPass);
		Toolkit.dilutionCalculator().click();
//		commonFunctions.waitUntilElementVisible(Toolkit_DilutionCalculator.textWelcomeDilution());
//		Toolkit_DilutionCalculator.btStartCalculator().click();
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.dilutionInstructionText().isDisplayed(), true);
		Toolkit_DilutionCalculator.inputFounder().sendKeys("100");
		Toolkit_DilutionCalculator.inputTotalShares().sendKeys("1000000");
		Toolkit_DilutionCalculator.inputPreMoney().sendKeys("8000000");
		Toolkit_DilutionCalculator.inputProposed().sendKeys("3000000");
		Toolkit_DilutionCalculator.inputStockOptionPool().click();
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.ownershipPercent("27.27%").isDisplayed(), true);
		Toolkit_DilutionCalculator.inputStockOptionPool().sendKeys("10");
		Toolkit_DilutionCalculator.btCalculate().click();
		commonFunctions.waitUntilElementVisible(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Results']"));
		
		
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtFounder().getText(), "1,000,000");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtFounderDiluted().getText(), "Diluted: 37.3%");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtFounderPercentage().getText(), "62.7%\nDiluted: 37.3%");
		
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtInvestor().getText(), "434,783");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtInvestorPercentage().getText(), "27.3%");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtEOP().getText(), "159,420");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtEOPPercentage().getText(), "10%");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtTotal().getText(), "1,594,203");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtTotalPercentage().getText(), "100%");
		
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtEffectivePreMoney().getText(), "$6,900,000");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtPreMoney().getText(), "$8,000,000");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtPostMoney().getText(), "$11,000,000");
		AssertJUnit.assertEquals(Toolkit_DilutionCalculator.txtSeries().getText(), "$6.9 / share");
		AssertJUnit.assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Founders will be diluted by: '][span='37.3%']").isDisplayed(), true);
		
		
		Log.endTestCase(testCaseName);
	}
	
	@Test
	public void testDilutionCalculator() throws UnknownHostException{
		mainDilutionCalculator();
	}

}
