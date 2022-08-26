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


public class POM_productos extends Base {
	
	static String precioProducto= null;

	// Se inicializa el logger
	final static Logger logger = LogManager.getLogger(POM_productos.class.getName());
	Properties prop = new Properties();
		
	By tituloProducto	 	 = By.xpath("//span[contains (text(),'Products')]");			 
	By iconoCarritoLleno     = By.xpath("//span[@class='shopping_cart_badge']");
	By selectOrdenar 		 = By.xpath("//select[@class='product_sort_container']");
	By listaProductos		 = By.xpath("//button[contains(@id,'add-to-cart')]");
	By iconoCarrito			 = By.xpath("//a[@class='shopping_cart_link']");
	
	//public WebDriver driver;
	private String precioProd;
	/*
	 * Constructor que invoca el driver del navegador a utilizar
	 */
	public POM_productos(WebDriver driver) {
		//this.driver=driver;
		super(driver);
	}

	public void assertTitle()  {		
		
		logger.info("Se valida el títlo de la pantalla de productos");
		
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tituloProducto));
		String expectedTittle = "PRODUCTS";
		boolean valor= expectedTittle.equals(getText(tituloProducto));
		Assert.assertTrue(valor, "TÍTULO NO ESPERADO");

	}
	
	public void seleccionarProducto() throws Exception  {	

		prop.load(new FileReader(rutaProperties()));
		logger.info("Se valida que existan productos disponibles");
		Assert.assertEquals(findElements(listaProductos).size()>0, true);
		WebDriverWait wait = esperarElemento();			
		List<WebElement> lista= getListWebElements(listaProductos);
		//waitSleep(5);
		String[] test = lista.get(Integer.parseInt(prop.getProperty("INDEX_PROD"))).toString().split("\\//");
		String xpath= "("+"//"+test[1].replace(")]]", ")]")+")["+prop.getProperty("INDEX_PROD")+"]";
		
		By producto = By.xpath(xpath);

		logger.info("Se selecciona un producto a comprar");
		click(producto); 
		logger.info("Se valida que el producto aparezca en el icono de carrito de compras");
		wait.until(ExpectedConditions.visibilityOfElementLocated(iconoCarritoLleno));
	}
	
	public void obtenerPrecioProducto()  {	
				
		By precio = By.xpath("(//div[@class='inventory_item_price'])["+prop.getProperty("INDEX_PROD")+"]");
		precioProducto= getText(precio);
		this.precioProducto=precioProducto;
	}
	
	public void ordernarProductos()  {	
		logger.info("Se ordena los productos");
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfElementLocated(selectOrdenar));
		selectList(selectOrdenar, "Price (high to low)");
	}
	
	public void irOpcCarritoCompras()  {
		logger.info("Se ingresa a la opción de carrito de compras");
		WebDriverWait wait = esperarElemento();	
		wait.until(ExpectedConditions.presenceOfElementLocated(iconoCarrito));
		click(iconoCarrito); 
	}

	

}
