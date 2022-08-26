/**
 * Clase que contiene métodos bases de uso para los scripts
 * 
 * @author Sergio Urbano
 * @since 02/12/2020
 * @version 1.0
 * 
 */

package com.project.Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base {
	
	static WebDriver driver;
	RutasGenerales ruta = new RutasGenerales();
	
	/*
	 * Método constructor que inicializa el driver
	 */
	public Base(WebDriver driver) {
		this.driver = driver;
		
	}
	
	/*
	 * Método que obtiene el driver del navegador Chrome
	 */
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", ruta.rutaDriverChrome());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("incognito");
		return driver;
		/*System.setProperty("webdriver.chrome.driver", ruta.rutaDriverChrome());
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("ignore-certificate-errors");
		capabilities.setCapability(ChromeOptions.CAPABILITY,options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		return driver;*/

		}
	
	/*
	 * Método que obtiene el driver del navegador Firefox
	 */
	public WebDriver firefoxDriverConnection() {
		System.setProperty("webdriver.gecko.driver", ruta.rutaDriverFirefox());
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	
	}
	
	/*
	 * Método que obtiene el driver del navegador Internet Explorer
	 */
	public WebDriver iExplorerDriverConnection() {
		System.setProperty("webdriver.ie.driver", ruta.rutaDriverIE());
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		return driver;
	
	}
	
	/*
	 * Método que obtiene el driver del navegador Microsoft Edge
	 */
	public WebDriver edgeDriverConnection() {
		System.setProperty("webdriver.edge.driver", ruta.rutaDriverEdge());
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		return driver;
	
	}
	
	/*
	 * Método que obtiene el driver del navegador Opera
	 */
	public WebDriver operaDriverConnection() {
		System.setProperty("webdriver.opera.driver", ruta.rutaDriverOpera());
		driver = new OperaDriver();
		driver.manage().window().maximize();
		return driver;
	
	}
	
	/*
	 * Método para encontrar un elemento
	 */
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	/*
	 * Método para encontrar una lista de elementos
	 */
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
    public  List<WebElement> getListWebElements(By locator) {
        List<WebElement> weList = null;
        try {
            weList = driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("Don't found element [" + locator + "]");
        }
        return weList;
    }
	
	/*
	 * Método para obtener el texto de un elemento
	 */
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	/*
	 * Método para obtener si el texto de un elemento es vacío
	 */
	public Boolean getTextEmpty(By locator) {
		return driver.findElement(locator).getText().isEmpty();
	}
	
	/*
	 * Método para comparar el texto de un elemento con un valor
	 */
	public Boolean getTextCompare(By locator, String inputText) {
		return driver.findElement(locator).getText().trim().equalsIgnoreCase(inputText);
	}
	
    public  void waitSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	/*
	 * Método para ingresar un valor dentro de un elemento
	 */
	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}
	
	/*
	 * Método para dar clic a un elemento
	 */
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	/*
	 * Método para dar clic a un elemento
	 */
	public void selectList(By locator, String value) {
		
		Select objSelect =new Select(driver.findElement(locator));
		objSelect.selectByVisibleText(value);
	}
	
	/*
	 * Método para validar si se muestra un elemento
	 */
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch(org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	/*
	 * Método para setear la url de una pagina al driver
	 */
	public void visit(String url) {
		driver.get(url);
	}
	
	/*
	 * Método para imrimir un texto en consola
	 */
	public void print(String inputText) {
		System.out.println(inputText);
	}
	 public  void BajarScroll(By by) {
		 
		 	JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		    js.executeScript("arguments[0].click();", findElement(by)); 

	 }
	/*
	 * Método que prepara un WebDriverWait
	 * Se utiliza para esperar que se muestr un elemento
	 * o si se puede hacer clic, etc.
	 */
	public WebDriverWait esperarElemento() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		return wait;
	}
	
	/*
	 * Método que devuelve la ruta del properties
	 * 
	 */
	public String rutaProperties() {
		return ruta.rutaProperties();
	}
	
	/*
	 * Método para refrescar la página
	 */
	public void recargar() {
		driver.navigate().refresh();
	}

}
