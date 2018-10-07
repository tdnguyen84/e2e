package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jmx.snmp.Timestamp;

import pageObjects.Board_Consent;
import pageObjects.Login;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.time.LocalDateTime;




public class commonFunctions {
	
	public static void gotoUrl(String url) {
		try {			
			setup._webdriver.navigate().to(url);
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static WebElement getElement(String how, String query) {		
		try {
			
			WebElement element = null;		
			
			setup._webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			switch(how) {
				case "id" :
					element = setup._webdriver.findElement(By.id(query));
					break;				
				case "linkText":
					element = setup._webdriver.findElement(By.linkText(query));
					break;
				case "name":
					element = setup._webdriver.findElement(By.name(query));
					break;
				case "className":
					element = setup._webdriver.findElement(By.className(query));
					break;
				case "xpath":
					element = setup._webdriver.findElement(By.xpath(query));
					break;
				case "cssSelector":
					element = setup._webdriver.findElement(By.cssSelector(query));
					break;
				//You can define more query at here...			
			}
			return element;
		} catch(Exception e) {
			return null;
		}		
	}
	
	public static void iWait(int timeout) {
		setup._webdriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void waitUntilElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(setup._webdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilElementClickAble(WebElement element) {		
		WebDriverWait wait = new WebDriverWait(setup._webdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));;
	}
	
//	public static void selectDropdowListValue(WebElement DDList, WebElement DDListPicker, String value, boolean isPicker) throws InterruptedException {
//		CommonFunctions.waitUntilElementVisible(DDList);
//		if(DDList.getAttribute("value") != value) {
//			WebDriverWait wait = new WebDriverWait(Setup._webdriver, 10);				
//			
//			if(isPicker) {			
//				DDListPicker.click();
//				
//				String customQuery = "a[class^='aui-list-item-link'][title='" + value + "']";
//				WebElement listItem = CommonFunctions.getElement(Constant.How.CSSSELECTOR, customQuery);
//				Assert.assertNotNull(listItem);
//				listItem.click();
//				wait.until(ExpectedConditions.elementToBeClickable(DDListPicker));
//			} else {
//				DDList.click();	
//				Select oSelection = new Select(DDList);
//				List<WebElement> options = oSelection.getOptions();
//
//			    for (WebElement option : options) {
//			        System.out.println(option.getText());
//			        if (option.getText().equals(value)) {
//			            option.click();
//			            Assert.assertTrue(option.isSelected());
//			            break;
//			        }
//			    }
//			}		
//		}
//		
//	}
//	
//	public static void selectCurrentDate(WebElement widgetDate, boolean isDueDate) {
//		String currentDate = getCurrentDay();
//		if(isDueDate) {
//			CreateIssuePage.pickerDueDate().click();
//		} else {
//			CreateIssuePage.pickerVendorDate().click();
//		}
//		
//		iWait(20);	
//		
//		List<WebElement> rows=CreateIssuePage.widgetDueDate().findElements(By.tagName("tr"));
//		List<WebElement> columns=CreateIssuePage.widgetDueDate().findElements(By.tagName("td"));  
//		for (WebElement cell: columns){
//			if (cell.getText().equals(currentDate)){  
//				cell.click();  
//				break;  
//			}  
//		}   
//	}
//	
	public static Integer getCurrentDay() {
		LocalDateTime now = LocalDateTime.now();
		return now.getDayOfMonth();
	}
	
	public static Integer getNextDay(Integer numberDays) {
		Integer currentDay = getCurrentDay();
		return currentDay + numberDays;
	}
//	
//	public static String summaryGenerator(String textSummary) {
//		Date date = new Date();
//		return (textSummary + date.getSeconds());
//	}
//	
//	
//	public static Object scrollElementIntoView(WebElement element) {
//	    return ((JavascriptExecutor)Setup._webdriver).executeScript("arguments[0].scrollIntoView(true);", element);
//	}
//	
	public static void setClipboardData(String string) {
	   StringSelection stringSelection = new StringSelection(string);
	   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
//	public static void uploadFile(String fileName) throws AWTException {
//		setClipboardData(fileName);
//		Robot robot = new Robot();
//		robot.delay(1000);
//
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
//		robot.delay(1000);
//	}
	
	public static String getPathUploadFile(String uploadFile) throws Exception {
		String fullPath = new File(".").getCanonicalPath() + uploadFile;
		return fullPath.replace("\\\\", "\\");
	}
	
	public static void jsClick(WebElement element) {
		((JavascriptExecutor)setup._webdriver).executeScript("arguments[0].click();", element);
	}
	
	public static void jsInput(WebElement element) {
		((JavascriptExecutor)setup._webdriver).executeScript("arguments[0].value='100';", element);
	}
	
	public static void selectDateInPicker(Integer selectedDay) {
		
		Board_Consent.datePickerInput().click();
		
		if(selectedDay < commonFunctions.getCurrentDay()) {
			Board_Consent.btNextMonth().click();
		}
		String nextCurrentDayStr = String.valueOf(selectedDay);
		
		Board_Consent.btDateInPicker(nextCurrentDayStr).click();
	}
	
	public static void uploadFile(String fileName) throws AWTException {
		String workingDir = System.getProperty("user.dir");
		String fileDir = workingDir + "/src/" + fileName;//"//Users/tridinhnguyen/Documents/work/e2e-anduin/src";
		setClipboardData(fileDir);
		Robot robot = new Robot();
		robot.delay(1000);
		
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_TAB);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_TAB);
		 
		robot.delay(500);
		 
		//Open Goto window
		 
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_SHIFT);
		 
		robot.keyPress(KeyEvent.VK_G);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_SHIFT);
		 
		robot.keyRelease(KeyEvent.VK_G);
		 
		//Paste the clipboard value
		 
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_V);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_V);
		 
		//Press Enter key to close the Goto window and Upload window
		 
		robot.keyPress(KeyEvent.VK_ENTER);
		 
		robot.keyRelease(KeyEvent.VK_ENTER);
		 
		robot.delay(500);
		 
		robot.keyPress(KeyEvent.VK_ENTER);
		 
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static String getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return String.valueOf(timestamp.getDateTime());
	}
	
	public static void logOut(boolean isDirectly) {
		if(isDirectly) {
			String logoutURL = setup._testsite + "/#/logout";
			commonFunctions.gotoUrl(logoutURL);
		} else {
			Login.profileAvatar().click();
			commonFunctions.getElement(constant.How.XPATH, "//span[text()='Log out']").click();
		}
	}
}
