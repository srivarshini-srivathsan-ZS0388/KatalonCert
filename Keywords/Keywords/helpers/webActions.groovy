package Keywords.helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.swing.factory.FormattedTextFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import internal.GlobalVariable
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Dimension;

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import java.awt.Robot
import java.awt.event.KeyEvent;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate
import java.time.format.DateTimeFormatter

public class WebActions {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
	}


	/****
	 * check if element not visible
	 */

	@Keyword
	def loadernotvisible(String copyObjectID) {

		TestObject myElement = findTestObject('copyObjectID')

		def isVisible = WebUI.getAttribute(myElement, 'displayed')

		while (isVisible != null || isVisible.equalsIgnoreCase('true')) {
			println 'Element is not visible!'
			WebUI.waitForPageLoad(5)
		}
	}

	/****
	 * check if element not visible method2
	 */

	@Keyword
	def elenotvisible(TestObject path) {

		TestObject myElement = findTestObject('path')

		// Loop while the element is visible
		while (WebUI.verifyElementPresent(myElement, 1, FailureHandling.CONTINUE_ON_FAILURE)
				&& WebUI.getAttribute(myElement, 'displayed').equalsIgnoreCase('true')) {
			println 'Element is still visible!'
			//wait still it disappear
			WebUI.waitForElementNotVisible(myElement, 5) // Or a more appropriate waiting mechanism
		}

		println 'Element is now invisible!'
	}





	/**
	 * Zoom out browser
	 */
	@Keyword
	def zoomBrowser() {

		Robot robot = new Robot()

		for (int i = 0; i < 5; i++) {
			// how many times you want CRTL+'-' pressed - this gets zoom to 50%
			robot.keyPress(KeyEvent.VK_CONTROL)
			robot.keyPress(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_CONTROL)
			robot.keyRelease(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_CONTROL)
			robot.keyRelease(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_CONTROL)
			robot.keyRelease(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_CONTROL)
		}
	}


	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(String locator) {
		try {
			WebElement element = DriverFactory.getWebDriver().findElement(By.xpath(locator))
			element.click()
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	/**
	 * Method Description: This method used to hover a mouse on a web element
	 * @param locator
	 * @return
	 */
	@Keyword
	def mouseOverElement(String locator) {
		try {
			WebElement element = DriverFactory.getWebDriver().findElement(By.xpath(locator))
			Actions actions = new Actions(DriverFactory.getWebDriver())
			actions.moveToElement(element).perform()
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	/**
	 * Method Description: This selenium method used to verify an element present or not
	 * @param locator
	 * @return
	 */
	@Keyword
	def verifyElePresent(String locator) {
		try {
			WebElement element = DriverFactory.getWebDriver().findElement(By.xpath(locator))
			element.isDisplayed()
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	/**
	 * Method Description: This method used to refresh a page until an element exist
	 * @param xpath
	 * @param timeoutSeconds
	 * @return
	 */
	@Keyword
	public static boolean refreshUntilElementExistsByXPath(String xpath, int timeoutSeconds) {

		WebDriver driver = DriverFactory.getWebDriver()
		int attempts = 0;
		while (attempts < timeoutSeconds) {
			try {
				// Attempt to find the element
				new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
				return true;
			} catch (Exception e) {
				// Element not found, refresh the page and retry
				driver.navigate().refresh();
				attempts++;
			}
		}

		// Timeout reached, element not found
		return false;
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 * Method Description: This method used to Click on an element using parameterization
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def clickEleWithText(String objRepoPath, String eleText) {
		TestObject obj = findTestObject(objRepoPath)
		List<String> xpaths = obj.getActiveXpaths()
		if (xpaths.isEmpty()) {
			println("Test object does not have any xpath locators associated with it")
		}
		String originalXpath = xpaths.get(0)
		String updatedXpath = originalXpath.replace('${eleText}', eleText)
		println(updatedXpath)
		obj.setXpaths([updatedXpath])
		WebUI.click(obj)
	}

	/**
	 * Method Description: This method used to verify an element visibility using parameterization
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def verifyEleVisibilityWithText(String objRepoPath, String eleText) {
		TestObject obj = findTestObject(objRepoPath)
		obj.addProperty('eleText', ConditionType.EQUALS, eleText)
		WebUI.verifyElementVisible(obj)
	}

	/**
	 * Method Description: This method used to generate random staring to concatenate
	 * @return
	 */
	@Keyword
	def String generateRandomString() {
		String randomString = UUID.randomUUID().toString().replaceAll('-', '').substring(0, 10)
		println randomString
		return randomString
	}

	/**
	 * Method Description: This method used to verify an element non visibility using param
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def verifyEleNonVisibilityWithText(String objRepoPath, String eleText) {
		// Retrieve the Test Object from the Object Repository
		TestObject obj = findTestObject(objRepoPath)

		// Set the parameter value for the Test Object
		obj.addProperty('eleText', ConditionType.EQUALS, eleText)
		// Click on the button
		WebUI.verifyElementNotPresent(obj,0)
	}

	/**
	 * Method Description: This method used to click an element using JS
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def clickByJS(String objRepoPath, String eleText) {
		// Get the JavaScript executor instance
		JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getWebDriver())

		// Find the web element you want to click
		TestObject elementToClick = findTestObject(objRepoPath)
		elementToClick.addProperty('eleText', ConditionType.EQUALS, eleText)
		// Execute JavaScript code to click on the element
		js.executeScript("arguments[0].click();", Arrays.asList(elementToClick))
	}

	/**
	 * Method Description: This method used to click an element using JS
	 * @param objRepoPath
	 * @return
	 */
	@Keyword
	def clickByJS1(String objRepoPath) {
		// Get the JavaScript executor instance
		JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getWebDriver())

		// Find the web element you want to click
		TestObject elementToClick = findTestObject(objRepoPath)

		// Execute JavaScript code to click on the element
		js.executeScript("arguments[0].click();", Arrays.asList(elementToClick))
	}

	/**
	 * Method Description: This method used to scroll up the page as needed
	 * @param objRepoPath
	 * @param dim
	 * @return
	 */
	@Keyword
	def scrollUpJS(String objRepoPath, int dim) {
		JavascriptExecutor js = ((JavascriptExecutor) DriverFactory.getWebDriver())
		TestObject sidebarElement = findTestObject(objRepoPath)

		// Execute JavaScript code to scroll the sidebar element up
		js.executeScript("arguments[0].scrollTop -= "+dim+";", Arrays.asList(sidebarElement))
	}

	/**Click Dynamic xpath
	 * 
	 */

	@Keyword
	def clickDynamicXpath(String xpath) {
		TestObject object = new TestObject("object")
		object.addProperty("xpath", ConditionType.EQUALS, xpath)
		WebUI.click(object)
	}

	/**
	 * Method Description: This method used to move an element to do action
	 * @param objRepoPath
	 * @return
	 */
	@Keyword
	def moveToElementAction(String objRepoPath) {
		TestObject elementToClick = findTestObject(objRepoPath)
		Actions actions = new Actions(DriverFactory.getWebDriver())
		actions.moveToElement(WebUI.findWebElement(elementToClick, 0)).perform()
		WebUI.click(elementToClick)
	}

	/**
	 * Method Description: This method used to scroll the page in both up and down
	 * @param driver
	 * @param pixels
	 * @param direction
	 * @return
	 */
	@Keyword
	def scrollPage(WebDriver driver, int pixels, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver
		if (direction == "up") {
			js.executeScript("window.scrollBy(0, -" + pixels + ");")
		} else if (direction == "down") {
			js.executeScript("window.scrollBy(0, " + pixels + ");")
		} else {
			println("Invalid scroll direction provided. Please provide 'up' or 'down'.")
		}
	}

	/**
	 * Method Description: This method used to Scroll a page using JS
	 * @param to
	 * @return
	 */
	@Keyword
	def scrollPageToElement(TestObject to) {
		// Get the WebElement of the target element
		WebElement element = WebUiBuiltInKeywords.findWebElement(to, 2)
		// Execute JavaScript to scroll to the target element
		((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element)
	}

	/**
	 * Method Description: This method used to scroll up the page to the top
	 * @param driver
	 * @return
	 */
	@Keyword
	def scrollToHeader(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, 0);");
	}

	/**
	 * Method Description: This method used to zoomout the page
	 * @param driver
	 * @param zoomLevel
	 * @return
	 */
	@Keyword
	def temporaryZoomOut(WebDriver driver, double zoomLevel) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("document.body.style.transform='scale(" + zoomLevel + ")';");
	}

	/**
	 * Method Description: This method used to switch into iframe
	 * @return
	 */
	@Keyword
	def switchtoiframe(String iframeSelector) {
		WebDriver driver = DriverFactory.getWebDriver()
		def iframeElement = driver.findElement(By.cssSelector(iframeSelector))
		driver.switchTo().frame(iframeElement)
	}
	/**
	 * Method Description: Switch back to normal window 
	 * @return
	 */
	@Keyword
	def switchbacktodefaultContent() {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.switchTo().defaultContent()
	}

	/**
	 * Method Description: This method used to get a text from an element
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def getTextWithEle(String objRepoPath, String eleText) {
		TestObject obj = findTestObject(objRepoPath)
		obj.addProperty('text', ConditionType.CONTAINS, eleText)
		return WebUI.getText(obj)
	}

	/**
	 * Method Description: This method used to wait for an element to visible
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def waitForElementVisibleWithText(String objRepoPath, String eleText) {
		TestObject obj = findTestObject(objRepoPath)
		obj.addProperty('text', ConditionType.EQUALS, eleText)
		WebElement element = WebUiCommonHelper.findWebElement(obj, 10)
		return element
	}

	/**
	 * Method Description: This method used to mouse over element
	 * @param objRepoPath
	 * @param eleText
	 * @return
	 */
	@Keyword
	def mouseOverEleWithText(String objRepoPath, String eleText) {
		String replacedLocator = objRepoPath.replace('${eleText}', eleText)
		println(replacedLocator)
		WebElement element = WebUI.waitForElementVisible(findTestObject(replacedLocator), 10)

		Actions actions = new Actions(DriverFactory.getWebDriver())
		actions.moveToElement(element).perform()
	}

	/**
	 * Method Description: This method used to get current or today date
	 * @return
	 */
	@Keyword
	def getCurrentDate() {
		LocalDate currentDate = LocalDate.now()
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
		String formattedDate = currentDate.format(dateFormatter)
		return formattedDate
	}



	/**
	 * Method Description: This method used to get current date in the format dd/MM/yyyy
	 * @return
	 */
	@Keyword
	def getCurrentDateddMMyyyy() {
		LocalDate currentDate = LocalDate.now()
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
		String formattedDate = currentDate.format(dateFormatter)
		return formattedDate
	}

	/**
	 * Method Description: This method used to upload a file from local system
	 * @param fileName
	 * @param locator
	 * @return
	 */
	@Keyword
	def uploadFilesFromLocalSys(String fileName, String locator) {
		try {
			String absoluteFilePath = System.getProperty("user.dir") + File.separator + "Data Files"+ File.separator + "InputData"+ File.separator +fileName
			WebUI.verifyTextPresent('Upload', false)
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement fileInputElement = driver.findElement(By.xpath(locator))
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('hidden');", fileInputElement)
			fileInputElement.sendKeys(absoluteFilePath)
		}catch(Exception e) {
			e.printStackTrace()
		}
	}

	@Keyword
	def getElementColor(WebElement element) {
		try {
			String color = ((JavascriptExecutor)DriverFactory.getWebDriver()).executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');", element).toString()
			return color
		}catch(Exception e) {
			e.printStackTrace()
		}
	}

	@Keyword
	def mapRGBToColorName(String rgbValue) {
		// Extracting RGB values from the string
		def rgbArray = rgbValue.replaceAll("[^\\d,]", "").split(',')
		def red = Integer.parseInt(rgbArray[0].trim())
		def green = Integer.parseInt(rgbArray[1].trim())
		def blue = Integer.parseInt(rgbArray[2].trim())
		println('Red: ' + red + ', Green: ' + green + ', Blue: ' + blue)

		// Determine the color based on RGB values
		if (red > 200 && green > 100 && blue < 50 && green > red && green > blue) {
			return 'yellow'
		} else if (red > 200 && green > 50 && blue < 50 && red > green && red > blue) {
			return 'orange'
		} else if (red > green && red > blue) {
			return 'red'
		} else if (green > red && green > blue) {
			return 'green'
		} else {
			return 'unknown' // Or handle other colors accordingly
		}
	}
}
