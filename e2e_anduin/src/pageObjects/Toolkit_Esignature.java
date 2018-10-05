package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Toolkit_Esignature {
	public static WebElement btSign() {
		return commonFunctions.getElement(constant.How.XPATH, "//button/span[text()='Sign']");
	}
	public static WebElement btRequest() {
		return commonFunctions.getElement(constant.How.XPATH, "//button/div[text()='Request']");
	}

}
