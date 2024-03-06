package utileria

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.transform.ConditionalInterrupt
import internal.GlobalVariable

public class accion {

	public static TestObject makeTestObjectFromXpath(String xpath){

		TestObject result = null

		if(!xpath.equals("") || xpath !=null){

			TestObject tempObject = new TestObject(xpath)

			tempObject.addProperty("xpath", ConditionType.EQUALS , xpath)

			if (tempObject != null) {

				result = tempObject
			}

			return result
		} else {

			return null
		}
	}

	public static void agregarPuntoVerificacion(String mensaje, Boolean decision, boolean screenshot) {
		if(mensaje) {
			if(decision) {
				KeywordUtil.markPassed(mensaje);
			}else {
				KeywordUtil.markFailed(mensaje);
			}

			if(screenshot) {
				WebUI.takeScreenshot();
			}
		}
	}

	public static Boolean present(String xpath, int time = 40) {

		return WebUI.waitForElementPresent(makeTestObjectFromXpath(xpath), time);
	}

	public static String text(String xpath) {
		return WebUI.getText(makeTestObjectFromXpath(xpath));
	}

	public static void click(String xpath) {
		WebUI.click(makeTestObjectFromXpath(xpath));
	}
}
