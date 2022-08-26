/**
 * Clase que contiene la llamada al script para la prueba
 * 
 * @author 
 * @since 
 * @version 1.0
 * 
 */

package com.project.POM;

import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.Base.Base;

import org.openqa.selenium.By;


public class POM_checkout_one extends Base {

	// Se inicializa el logger
	final static Logger logger = LogManager.getLogger(POM_checkout_one.class.getName());
	Properties prop = new Properties();
		
	By tituloCheckoutOne	= By.xpath("//span[@class='title']");
	By inputNombre	 	 	= By.xpath("//input[@id='first-name']");			 
	By inputApellido        = By.xpath("//input[@id='last-name']");	
	By inputCodigozip	    = By.xpath("//input[@id='postal-code']");
	By botonContinuar       = By.xpath("//input[@id='continue']");
	/*
	 * Constructor que invoca el driver del navegador a utilizar
	 */
	public POM_checkout_one(WebDriver driver) {
		super(driver);
	}

	
	public void assertTitle()  {		
		
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tituloCheckoutOne));
		String expectedTittle = "CHECKOUT: YOUR INFORMATION";
		boolean valor= expectedTittle.equals(getText(tituloCheckoutOne));
		Assert.assertTrue(valor, "TÍTULO NO ESPERADO");
		//Assert.assertEquals(getText(tituloCheckoutOne), expectedTittle);

	}
	
	public void completarDatos(String nombre, String apellido, String codigozip)  {		

		Assert.assertTrue(isDisplayed(inputNombre), "CAMPO NOMBRE NO DISPONIBLE");
		Assert.assertTrue(isDisplayed(inputApellido), "CAMPO APELLIDO NO DISPONIBLE");
		Assert.assertTrue(isDisplayed(inputCodigozip), "CAMPO CÓDIGO ZIP NO DISPONIBLE");
		
		type(nombre, inputNombre);
		type(apellido, inputApellido);
		type(codigozip, inputCodigozip);
		
		Assert.assertTrue(isDisplayed(botonContinuar), "BOTÓN CONTINUAR NO DISPONIBLE");
		click(botonContinuar);

	}
	

	

}
