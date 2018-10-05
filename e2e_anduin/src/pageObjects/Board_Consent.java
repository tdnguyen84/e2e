package pageObjects;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;

import utilities.commonFunctions;
import utilities.constant;

public class Board_Consent {
	public static WebElement helloModalTitle() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/h3[text()='Hello QA, welcome to Anduin!']");
	}
	public static WebElement btInviteMember() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Invite team members']");
	}
	public static WebElement btSkip() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Skip for now']");
	}
	public static WebElement modalInivteMember() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[@title='Invite team members']/div[text()='Invite team members']");
	}
	public static WebElement btCancelInviteModal() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Cancel']");
	}
	public static WebElement linkCreateNewRequest() {
		return commonFunctions.getElement(constant.How.LINKTEXT, "Create new request");
	}
	
	public static WebElement stepperCreate() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='stepper-title'][text()='Create']");
	}
	
	public static WebElement stepperAssign() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='stepper-title'][text()='Assign']");
	}
	
	public static WebElement stepperDone() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='stepper-title'][text()='Done!']");
	}
	
	public static WebElement inputRequestName() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/input[@placeholder='Enter the request name']");
	}
	
	public static WebElement dropdownRequestType() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Select request type']");
	}
	
	public static WebElement datePickerInput() {
		return commonFunctions.getElement(constant.How.XPATH, "//input[@class='datepicker-input txt-grey']");
	}
	
	public static WebElement datePickerInputHasValue() {
		return commonFunctions.getElement(constant.How.XPATH, "//input[@class='datepicker-input']");
	}
	
	public static WebElement datePickerCalendar() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class=''datepicker-calendar]");
	}
	
	public static WebElement btDateInPicker(String day) {
		return commonFunctions.getElement(constant.How.XPATH, "//td/button[text()='"+day+"']");
	}
	
	public static WebElement btNextMonth() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[@class='datepicker-next'][text()='Next month']");
	}
	
	public static WebElement btRequestName(String templateName) {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='"+templateName+"']");
	}
	
	public static WebElement labelMonth() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='datepicker-title']/div[@class='datepicker-label']");
	}
	
	public static WebElement btBrowserFile() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='mr1'][text()='Drag files here to upload or ']/following-sibling::div");
	}
	
	public static WebElement diaglogDatePicker() {
		return commonFunctions.getElement(constant.How.XPATH, "//div[@class='datepicker-calendar']");
	}
	
	public static WebElement checkboxSignature() {
		return commonFunctions.getElement(constant.How.XPATH, "//div/label/input");
	}
	
	public static WebElement btNext() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Next']");
	}
	
	public static WebElement headerAssignBM() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Assign Board Members']");
	}
	
	public static WebElement inputName() {
		return commonFunctions.getElement(constant.How.XPATH, "//input[@placeholder='Name']");
	}
	
	public static WebElement inputEmail() {
		return commonFunctions.getElement(constant.How.XPATH, "//input[@placeholder='Email']");
	}
	
	public static WebElement headerGeneralMsg() {
		return commonFunctions.getElement(constant.How.XPATH, "//h3[text()='General message']");
	}
	
	public static WebElement txtareaGeneralMsg() {
		return commonFunctions.getElement(constant.How.XPATH, "//textarea[@placeholder='General message (optional)']");
	}
	
	public static WebElement txtareaPrivateMsg() {
		return commonFunctions.getElement(constant.How.XPATH, "//textarea[@placeholder='Private message (optional)']");
	}
	
	public static WebElement btSendRequest() {
		return commonFunctions.getElement(constant.How.XPATH, "//button[text()='Send request']");
	}
	
	public static WebElement btTrackApprovalStatus() {
		return commonFunctions.getElement(constant.How.LINKTEXT, "Track Approval Status");
	}
	
	public static WebElement btEntityName() {
		return commonFunctions.getElement(constant.How.XPATH, "//a/div/p[text()='Test Anduin']");
	}
	
	public static void createRquest(String requestName, String requestType, Integer selectedDay) throws AWTException {
		linkCreateNewRequest().click();
		commonFunctions.waitUntilElementVisible(stepperCreate());
		inputRequestName().sendKeys(requestName);
		dropdownRequestType().click();
		btRequestName(requestType).click();
		
		Board_Consent.datePickerInput().click();
		
		if(selectedDay < commonFunctions.getCurrentDay()) {
			Board_Consent.btNextMonth().click();
		}
		
		WebElement currentMonthLabel = labelMonth();
		String selectedMonth = currentMonthLabel.getText();
		
		String selectedDayStr = String.valueOf(selectedDay);
		Board_Consent.btDateInPicker(selectedDayStr).click();
		String sortNameSelectedMonth = selectedMonth.substring(0, 3);
		
		
		String selectedDate = sortNameSelectedMonth + " " + selectedDayStr + ", " + "2018";
		assertEquals(datePickerInputHasValue().getAttribute("value"), selectedDate);
		
		btBrowserFile().click();
		String fileName = "upload_example_doc.docx";
		commonFunctions.uploadFile(fileName);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[@class='ml2'][text()='"+fileName+"']").isDisplayed(), true);
		
		checkboxSignature().click();
		btNext().click();
		commonFunctions.waitUntilElementVisible(headerAssignBM());
		inputName().sendKeys("Test Board Member");
		inputEmail().sendKeys("qaanduin+boardmember@gmail.com");
		btNext().click();
		commonFunctions.waitUntilElementVisible(headerGeneralMsg());
		txtareaGeneralMsg().sendKeys("Genreal message here");
		txtareaPrivateMsg().sendKeys("Private message here");
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[@class='ml2'][text()='"+fileName+"']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='Signature required']").isDisplayed(), true);
		btSendRequest().click();
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='All set! Your request has been sent.']").isDisplayed(), true);
		
		commonFunctions.waitUntilElementClickAble(btTrackApprovalStatus());
		btTrackApprovalStatus().click();
		
		commonFunctions.waitUntilElementVisible(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Employee Option Pool']"));
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='Test Board Member']").isDisplayed(),true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Request sent']").isDisplayed(),true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='Pending']").isDisplayed(),true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div/span[text()='"+fileName+"']").isDisplayed(),true);
		
		btEntityName().click();
		commonFunctions.waitUntilElementVisible(commonFunctions.getElement(constant.How.XPATH, "//h3[text()='Requests for board approval']"));
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='"+requestName+"']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//td[text()='"+selectedDate+"']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='< 1 day']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//span[text()='Request sent']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.XPATH, "//div[text()='QA Company']").isDisplayed(), true);
		assertEquals(commonFunctions.getElement(constant.How.LINKTEXT, "View detail").isEnabled(), true);
		
		
	}
	
	
	
	
}
