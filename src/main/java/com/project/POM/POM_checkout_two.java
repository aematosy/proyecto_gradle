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


public class POM_checkout_two extends Base {

	// Se inicializa el logger
	final static Logger logger = LogManager.getLogger(POM_checkout_two.class.getName());
	Properties prop = new Properties();
		
	By tituloCheckoutTwo	= By.xpath("//span[@class='title']");
	By botonFinish          = By.xpath("//button[@id='finish']");
	By textoPrecio			= By.xpath("//div[@class='inventory_item_price']");
	/*
	 * Constructor que invoca el driver del navegador a utilizar
	 */
	public POM_checkout_two(WebDriver driver) {
		super(driver);
	}

	
	public void assertTitle()  {		
		
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tituloCheckoutTwo));
		String expectedTittle = "CHECKOUT: OVERVIEW";
		boolean valor= expectedTittle.equals(getText(tituloCheckoutTwo));
		Assert.assertTrue(valor, "TÍTULO NO ESPERADO");
		//Assert.assertEquals(getText(tituloCheckoutOne), expectedTittle);

	}
	
	public void finalizarCompra() throws InterruptedException  {		
		
		Assert.assertTrue(isDisplayed(botonFinish), "BOTÓN FINISH NO DISPONIBLE");
		click(botonFinish);

	}
	
	public void validarPrecio() throws Exception  {		

		String precioProducto=POM_productos.precioProducto;
		String precioCompra= getText(textoPrecio);
		Assert.assertEquals(precioProducto, precioCompra);

	}
	

	

}
