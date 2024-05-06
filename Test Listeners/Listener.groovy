//import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
//import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
//import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
//import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
//
//import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
//import com.kms.katalon.core.model.FailureHandling as FailureHandling
//import com.kms.katalon.core.testcase.TestCase as TestCase
//import com.kms.katalon.core.testdata.TestData as TestData
//import com.kms.katalon.core.testobject.TestObject as TestObject
//
//import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
//
//import internal.GlobalVariable as GlobalVariable
//
//import com.kms.katalon.core.annotation.BeforeTestCase
//import com.kms.katalon.core.annotation.BeforeTestSuite
//import com.kms.katalon.core.annotation.AfterTestCase
//import com.kms.katalon.core.annotation.AfterTestSuite
//import com.kms.katalon.core.context.TestCaseContext
//import com.kms.katalon.core.context.TestSuiteContext
//
//import Keywords.helpers.*
//
//
//class Listener {
//	WebActions act = new WebActions()
//	/**
//	 * Executes before every test case starts.
//	 * @param testCaseContext related information of the executed test case.
//	 */
//	@BeforeTestCase
//	def beforeTestCase(TestCaseContext testCaseContext) {
//
//		//Launch browser and navigate to URL
//		PropertyReaders reader = new PropertyReaders()
//		
//		WebUI.openBrowser('')	
//		println 'The URL is :          ' +GlobalVariable.appURL
//		WebUI.navigateToUrl(GlobalVariable.appURL)
////		WebUI.navigateToUrl(reader.readPropertyFileData("appURL"))
//		WebUI.maximizeWindow()
//		WebUI.click(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/btnUsernameAndPassword'))
//		//Login with user credentials
////		WebUI.setText(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/txtbxEmailOrUsername'), reader.readPropertyFileData("userName"))
////		WebUI.setEncryptedText(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/txtbxPassword'), reader.readPropertyFileData("password"))
//		WebUI.setText(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/txtbxEmailOrUsername'), GlobalVariable.userName)
//		WebUI.setEncryptedText(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/txtbxPassword'), GlobalVariable.password)
//		WebUI.click(findTestObject('Object Repository/Page_EcoOnline EHS LOGIN/btnLogin'))
//		
//		//Verify if Home page is displayed
//		WebUI.waitForElementPresent(findTestObject('Object Repository/Page_EcoOnline EHS EVENTS/companyLogo'), 2)	
//		act.zoomBrowser()
//		String currentUrl = WebUI.getUrl()
//		String expectedSubstring = 'Home'
//		assert currentUrl.contains(expectedSubstring)
//		
//	}
//
//	/**
//	 * Executes after every test case ends.
//	 * @param testCaseContext related information of the executed test case.
//	 */
//	@AfterTestCase
//	def afterTestCase(TestCaseContext testCaseContext) {
//		//WebUI.closeBrowser()
//	}
//
//	/**
//	 * Executes before every test suite starts.
//	 * @param testSuiteContext: related information of the executed test suite.
//	 */
//	@BeforeTestSuite
//	def beforeTestSuite(TestSuiteContext testSuiteContext) {
//		println testSuiteContext.getTestSuiteId()
//	}
//
//	/**
//	 * Executes after every test suite ends.
//	 * @param testSuiteContext: related information of the executed test suite.
//	 */
//	@AfterTestSuite
//	def afterTestSuite(TestSuiteContext testSuiteContext) {
//		println testSuiteContext.getTestSuiteId()
//	}
//}