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
import com.project.POM.POM_productos;

import com.project.Base.Base;

import org.openqa.selenium.By;


public class POM_carrito_compras extends Base {
	
	private WebDriver driver;
	POM_productos pom_productos;
	

	// Se inicializa el logger
	final static Logger logger = LogManager.getLogger(POM_carrito_compras.class.getName());
	Properties prop = new Properties();
		
	By tituloCarrito	 	 = By.xpath("//span[@class='title']");			 
	By primerProducto        = By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']");		
	By botonCheckout		 = By.xpath("//button[@id='checkout']");	
	By textoPrecio			 = By.xpath("//div[@class='inventory_item_price']");
	/*
	 * Constructor que invoca el driver del navegador a utilizar
	 */
	public POM_carrito_compras(WebDriver driver) {
		super(driver);
	}

	
	public void assertTitle()  {		
		
		logger.info("Se valida el títlo de la pantalla del carrito de compras");
		
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tituloCarrito));
		String expectedTittle = "YOUR CART";
		boolean valor= expectedTittle.equals(getText(tituloCarrito));
		//Assert.assertTrue(valor, "TÍTULO NO ESPERADO");
		Assert.assertEquals(getText(tituloCarrito), expectedTittle);

	}
	
	public void clickCheckout() throws Exception   {		

		Assert.assertTrue(isDisplayed(botonCheckout), "BOTON CHEKOUT NO DISPONIBLE");
		click(botonCheckout);

	}
	
	public void validarPrecio() throws Exception  {		

		String precioProducto=POM_productos.precioProducto;
		String precioCompra= getText(textoPrecio);
		Assert.assertEquals(precioProducto, precioCompra);
		/*if(!precioProducto.equals(precioCompra)){
		System.out.println("LOS PRECIOS NO COINCIDEN" +"se tiene:"+precioProducto+"y se espera"+precioCompra);
		}*/
	}
	

	

}
