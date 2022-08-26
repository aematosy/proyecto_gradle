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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.project.Base.Excel;
import com.project.Base.RutasGenerales;
import com.project.POM.POM_carrito_compras;
import com.project.POM.POM_checkout_one;
import com.project.POM.POM_checkout_two;
import com.project.POM.POM_login;
import com.project.POM.POM_productos;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test001CP002 {

	final static Logger logger = LogManager.getLogger(Test001CP002.class.getName());
	
	private WebDriver driver;
	POM_login pom_login;
	POM_productos pom_productos;
	POM_carrito_compras pom_carrito;
	POM_checkout_one pom_checkout_one;
	POM_checkout_two pom_checkout_two;
	RutasGenerales ruta;
	Properties prop = new Properties();
	Excel reader;
		
	/*
	 * Método que invoca al driver del navegador y
	 * la ruta del aplicativo a probar
	 */
	@BeforeMethod
	public void setUp() throws Exception {	

		pom_login = new POM_login(driver);
		prop.load(new FileReader(pom_login.rutaProperties()));
		
		if(prop.getProperty("CP002_NAV").equals("0")) {
			driver = pom_login.chromeDriverConnection();
		} else if(prop.getProperty("CP002_NAV").equals("1")) {
			driver = pom_login.firefoxDriverConnection();
		} else if(prop.getProperty("CP002_NAV").equals("2")) {
			driver = pom_login.edgeDriverConnection();
		} else if(prop.getProperty("CP002_NAV").equals("3")) {
			driver = pom_login.iExplorerDriverConnection();
		} else if(prop.getProperty("CP002_NAV").equals("4")) {
			driver = pom_login.operaDriverConnection();
		}
		ruta = new RutasGenerales();
	
	}

	/*
	 * Método que contiene el test de prueba
	 */
	@Test
	public void test() throws Exception {
		pom_productos = new POM_productos(driver);
		pom_carrito = new POM_carrito_compras(driver);
		pom_checkout_one = new POM_checkout_one(driver);
		pom_checkout_two = new POM_checkout_two(driver);
		//Leer Excel	
		try {reader = new Excel(ruta.rutaInputs());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		for(int rowNum = 2; rowNum <=reader.getRowCount("CP002"); rowNum++){
			
			//String device 	= reader.getCellData("CP002", "DEVICE", rowNum);
			String url 	= reader.getCellData("CP002", "URL", rowNum);
			String usuario 	= reader.getCellData("CP002", "USUARIO", rowNum);
			String password = reader.getCellData("CP002", "PASSWORD", rowNum);
			String nombre = reader.getCellData("CP002", "NOMBRE", rowNum);
			String apellido = reader.getCellData("CP002", "APELLIDOS", rowNum);
			String codigozip = reader.getCellData("CP002", "CODIGO_ZIP", rowNum);
			pom_login.recargar();
			driver.manage().deleteAllCookies();
			//pom_login.visit(prop.getProperty("URL").trim());
			pom_login.visit(url.trim());
			//cargarDevice(device);
			try {
				
				//pom_login.visit(url.trim());
				pom_login.login(usuario,password);
		        pom_productos.ordernarProductos();
		        pom_productos.assertTitle();
		        pom_productos.seleccionarProducto();	
		        pom_productos.obtenerPrecioProducto();
		        pom_productos.irOpcCarritoCompras();
		        pom_carrito.assertTitle();
		        pom_carrito.validarPrecio();
		        pom_carrito.clickCheckout();
		        pom_checkout_one.assertTitle();
		        pom_checkout_one.completarDatos(nombre,apellido,codigozip);
		        pom_checkout_two.assertTitle();
		        pom_checkout_two.validarPrecio();
		        pom_checkout_two.finalizarCompra();
		        
				reader.setCellData("CP002", "ESTADO", rowNum, "PASSED");
				
			}catch(Exception e){
				
				reader.setCellData("CP002", "ESTADO", rowNum, "FAILED");
				
			}
			
		}
	}

	/*
	 * Método que culmina el proceso de prueba
	 */
	@AfterMethod
	public void tearDown() throws Exception {
		logger.info("****************************************************************************************");
		logger.info("************************************  "+"FIN - TESTCASE"+"  **********************************");
		logger.info("****************************************************************************************");
	//driver.quit();
	}
	
}
