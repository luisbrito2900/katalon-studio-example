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

//Verificar que el usuario pueda iniciar sesion con credenciales validas y haciendo click en el boton "Iniciar sesion".

TestData logindata1 = findTestData('logindata1');
int rows = logindata1.getRowNumbers();

String usuario;
String contraseña;

for (int i = 1; i<= rows; i++) {
	String estado = logindata1.getObjectValue('ejecutar', i).toString();

	if(estado.equalsIgnoreCase('true')) {

		WebUI.openBrowser("https://demo.applitools.com/index.html");

		//Verificando que la pagina abrio correctamente si el cuadro de texto de inicio de sesion esta presente

		if(accion.present("//input[@id='username']")) {

			usuario = logindata1.getObjectValue('username', i).toString();

			contraseña = logindata1.getObjectValue('password', i).toString();

			accion.agregarPuntoVerificacion("Se muestra la pantalla de login", true, true);

			//Introducir nombre de usuario
			WebUI.setText(accion.makeTestObjectFromXpath("//input[@id='username']"), usuario);

			//Verificando que se introdujo correctamente el nombre de usuario
			accion.agregarPuntoVerificacion("Se escribio el usuario de manera exitosa", true, true);

			//Introducir la contraseña
			WebUI.setText(accion.makeTestObjectFromXpath("//input[@id='password']"), contraseña);

			//Verificando que se introdujo correctamente la contraseña
			accion.agregarPuntoVerificacion("Se escribio la clave de manera exitosa", true, true);

			//Click en el boton Sign in
			WebUI.click(accion.makeTestObjectFromXpath("//*[@id='log-in']"));

			//Verificando que se inicio sesion correctamente
			if(accion.present("(//input [@placeholder= 'Start typing to search...'])[1]")) {
				accion.agregarPuntoVerificacion("El login fue exitoso", true, true);
			}else {
				accion.agregarPuntoVerificacion("El login no fue exitoso", false, true);
			}
		} else {
			accion.agregarPuntoVerificacion("No se muestra la pantalla de login", false, true);
		}
	}
}

WebUI.closeBrowser();


