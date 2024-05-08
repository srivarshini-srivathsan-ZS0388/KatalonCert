import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import Keywords.helpers.*

WebActions act = new WebActions()

WebUI.openBrowser('')

WebUI.navigateToUrl('https://demoblaze.com/index.html')

WebUI.maximizeWindow()

act.zoomBrowser()

WebUI.waitForPageLoad(6)

WebUI.click(findTestObject('Object Repository/Page_STORE/a_About us'))

WebUI.click(findTestObject('Object Repository/Page_STORE/button_Close1'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_STORE/a_Contact'), 3)

WebUI.click(findTestObject('Object Repository/Page_STORE/a_Contact'))

WebUI.click(findTestObject('Object Repository/Page_STORE/button_Close'))

WebUI.takeScreenshotAsCheckpoint('VS_Checkpoint')

WebUI.closeBrowser()

