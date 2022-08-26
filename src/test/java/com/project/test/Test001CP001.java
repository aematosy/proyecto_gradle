/**
 * Clase que contiene la llamada al script para la prueba
 * 
 * @author 
 * @since 
 * @version 1.0
 * 
 */

package com.project.test;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.project.Base.Excel;
import com.project.Base.RutasGenerales;
import com.project.POM.POM_login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Test001CP001 {

	private WebDriver driver;
	POM_login objCP;
	RutasGenerales ruta;
	Properties prop = new Properties();
	Excel reader;
		
	/*
	 * Método que invoca al driver del navegador y
	 * la ruta del asplicativo a probar
	 */
	@BeforeMethod
	public void setUp() throws Exception {	
		objCP = new POM_login(driver);
		prop.load(new FileReader(objCP.rutaProperties()));
		
		if(prop.getProperty("CP001_NAV").equals("0")) {
			driver = objCP.chromeDriverConnection();
		} else if(prop.getProperty("CP001_NAV").equals("1")) {
			driver = objCP.firefoxDriverConnection();
		} else if(prop.getProperty("CP001_NAV").equals("2")) {
			driver = objCP.edgeDriverConnection();
		} else if(prop.getProperty("CP001_NAV").equals("3")) {
			driver = objCP.iExplorerDriverConnection();
		} else if(prop.getProperty("CP001_NAV").equals("4")) {
			driver = objCP.operaDriverConnection();
		}
		ruta = new RutasGenerales();	
		
	}
	
	/*
	 * Método que contiene el test de prueba
	 */
	@Test
	public void test() throws Exception {
		
		//Leer Excel	
		try {reader = new Excel(ruta.rutaInputs());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		for(int rowNum = 2; rowNum <=reader.getRowCount("CP001"); rowNum++){
			
			String usuario 	= reader.getCellData("CP001", "USUARIO", rowNum);
			String password = reader.getCellData("CP001", "PASSWORD", rowNum);
			objCP.visit(prop.getProperty("URL").trim());
			
			try {
				objCP.login(usuario,password);
				
				reader.setCellData("CP002", "ESTADO", rowNum, "PASSED");
				
			}catch(Exception e){
				reader.setCellData("CP001", "ESTADO", rowNum, "FAILED");
				
			}
			
		}
	}

	/*
	 * Método que culmina el proceso de prueba
	 */
	@AfterMethod
	public void tearDown() throws Exception {
	driver.quit();
	}
	
}
