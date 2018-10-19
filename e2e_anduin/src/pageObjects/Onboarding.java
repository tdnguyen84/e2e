package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;


public class Onboarding {
	public static WebElement welcomeObserverModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/h3[text()='Hello John and welcome to Anduin']");
	}
	
	public static WebElement welcomeDealTeamModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/h3[text()='Hello Ada and welcome to Anduin']");
	}
	
	public static WebElement observerStep1ModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/h3[text()='End-to-End Visibility and Management']");
	}
	
	public static WebElement observerStep1Sub1() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Transparent Progress']");
	}
	
	public static WebElement observerStep1Sub2() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Deal Term Summary']");
	}
	
	public static WebElement observerStep1Sub3() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Web & Mobile E-Signature']");
	}
	
	
	public static WebElement observerStep2ModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/h3[text()='Comprehensive Reports, Right to Your Inbox']");
	}
	
	
	public static WebElement observerStep2Sub1() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Weekly Digests']");
	}
	
	public static WebElement observerStep2Sub2() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Automated Reports']");
	}
	
	public static WebElement observerStep2Sub3() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Instant Updates']");
	}
	
	public static WebElement observerStep3ModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/h3[text()='Visibility into Legal Matters']");
	}
	
	public static WebElement observerStep3Sub1() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Document Generator']");
	}
	
	public static WebElement observerStep3Sub2() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Legal Parsing Engine']");
	}
	
	public static WebElement observerStep3Sub3() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@aria-hidden='false']/div/div/div/div/h5[text()='Automated Comparison']");
	}
	
	
	
	public static WebElement selectedRole(String roleName) {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='at-check-mark h-100 flex items-center -selected'][div/div/h4='"+roleName+"']");
	}
	
	public static WebElement unselectedRole(String roleName) {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='at-check-mark h-100 flex items-center'][div/div/h4='"+roleName+"']");
	}
	
	public static WebElement btConfrim() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Confirm']");
	}
	
	public static WebElement btSkipTour() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Skip tour']");
	}
	
	public static WebElement btBetterInsight() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='→ Better Insight']");
	}
	
	public static WebElement btMoreTransparency() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='→ More Transparency']");
	}
	
	public static WebElement btGetStarted() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Get Started']");
	}
	
	public static WebElement dotSlickActive(String index) {
		return commonFunctions.getElement(constant.How.XPATH, "//li[@class='slick-active']/div[@class='at-slider-dot'][text()='"+index+"']");
	}
	
	
	
	public static String getWelcomeMsg() {
		return commonFunctions.getElement(constant.How.XPATH, "//h4[@class='tc mb4 ph3']/span").getText();
	}

}
