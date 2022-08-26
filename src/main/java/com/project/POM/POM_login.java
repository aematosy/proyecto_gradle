/**
 * Clase que contiene la llamada al script para la prueba
 * 
 * @author 
 * @since 
 * @version 1.0
 * 
 */

package com.project.POM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.Base.Base;

import org.openqa.selenium.By;


public class POM_login extends Base {

	// Se inicializa el logger
	final static Logger logger = LogManager.getLogger(POM_login.class.getName());
	Properties prop = new Properties();
	
	By inputUsername	 	 = By.xpath("//input[@id='user-name']");			 
	By inputPassword         = By.xpath("//input[@id='password']");
	By botonLogin			 = By.xpath("//input[@id='login-button']");
	
	By tituloProducto       = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	
	/*
	 * Constructor que invoca el driver del navegador a utilizar
	 */
	public POM_login(WebDriver driver) {
		
		super(driver);

	}

	/*
	 * Método que contiene el caso de la prueba
	 */

	public void login(String usuario,String password) throws IOException {		
		

	
			prop.load(new FileReader(rutaProperties()));
			
			logger.info("****************************************************************************************");
			logger.info("**********************************  "+"INICIO - TESTCASE"+"  *********************************");
			logger.info("****************************************************************************************");
			
			WebDriverWait wait = esperarElemento();				
			wait.until(ExpectedConditions.elementToBeClickable(inputUsername));
			type(usuario, inputUsername);
			type(password, inputPassword);
			click(botonLogin); 

	}
}
