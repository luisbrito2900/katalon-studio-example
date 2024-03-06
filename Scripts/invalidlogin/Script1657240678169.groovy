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
import utileria.accion as accion

//Verificar que el usuario no pueda iniciar sesion con credenciales no validas.

TestData invalidlogin = findTestData('invalidlog');
int rows = invalidlogin.getRowNumbers();

String usuario;
String contraseña;

for (int i = 1; i<= rows; i++) {
	String estado = invalidlogin.getObjectValue('ejecutar', i).toString();

	if(estado.equalsIgnoreCase('true')) {

		WebUI.openBrowser("https://demo.applitools.com/index.html");

		//Verificando que la pagina abrio correctamente
		if(accion.present("//input[@id='username']")) {

			usuario = invalidlogin.getObjectValue('username', i).toString();

			contraseña = invalidlogin.getObjectValue('password', i).toString();

			accion.agregarPuntoVerificacion("Se muestra la pantalla de inicio de sesion", true, true);

			//Introducir nombre de usuario
			WebUI.setText(accion.makeTestObjectFromXpath("//input[@id='username']"), usuario);

			//Verificando que se introdujo correctamente el nombre de usuario
			accion.agregarPuntoVerificacion("Se escribio el usuario de manera exitosa", true, true);

			//Introducir la contraseña
			WebUI.setText(accion.makeTestObjectFromXpath("//input[@id='password']"), contraseña);

			//Verificando que se introdujo correctamente la contraseña
			accion.agregarPuntoVerificacion("Se escribio la contraseña de manera exitosa", true, true);

			//Click en el boton Sign in
			WebUI.click(accion.makeTestObjectFromXpath("//*[@id='log-in']"));

			//Verificando que no se pueda iniciar sesion 
			if(accion.present("(//input [@placeholder= 'Start typing to search...'])[1]")) {
				accion.agregarPuntoVerificacion("Se inicio sesion", true, true);
			}else {
				accion.agregarPuntoVerificacion("No se inicio sesion", false, true);
			}
		} else {
			accion.agregarPuntoVerificacion("No se muestra la pantalla de inicio de sesion", false, true);
		}
	}
}

WebUI.closeBrowser();
