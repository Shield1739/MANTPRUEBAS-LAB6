package utp.ts.autos;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Unit test for simple App.
 */
@RunWith(DataProviderRunner.class)
public class AppTest 
{
	private WebDriver driver;
	By edadLocalizador;
	By generoLocalizador;
	By btn_consultarLocalizador;
	By bodySelector;

	@DataProvider
	public static Object[][] proveedorDatos()
	{
		String text = "El valor de la prima anual de seguro de auto según su género y edad es: ";

		return new Object[][]
			{
				{"18", "masculino", text + "2000 dólares"},
				{"24", "masculino", text + "2000 dólares"},
				{"25", "masculino", text + "1000 dólares"},
				{"64", "masculino", text + "1000 dólares"},
				{"65", "masculino", text + "1500 dólares"},
				{"18", "femenino", text + "500 dólares"},
				{"24", "femenino", text + "500 dólares"},
				{"25", "femenino", text + "500 dólares"},
				{"64", "femenino", text + "500 dólares"},
				{"65", "femenino", text + "1500 dólares"},
				{"-1", "masculino", "La edad no puede ser un valor negativo"},
				{"0", "masculino", "La edad del cotizante debe ser mayor o igual a 18 años"},
				{"17", "femenino", "La edad del cotizante debe ser mayor o igual a 18 años"},
				{"", "femenino", "Debe ingresar su edad para poder realizar la cotización"},
				{"diesiocho", "femenino", "Debe ingresar valores numéricos en el campo edad"},
				{"18.5", "femenino", "Debe ingresar valores numéricos enteros en el campo edad"}
			};
	}

  	@Before
	public void setUp()
	{
//		System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
//		this.driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/");
//		driver.get("http://localhost/SegurosAutos/src/main/java/utp/ts/autos/app/");

		this.edadLocalizador = By.name("edad");
		this.generoLocalizador = By.name("genero");
		this.btn_consultarLocalizador = By.name("btn_consultar");
		this.bodySelector = By.cssSelector("body");
	}

    @Test
	@UseDataProvider("proveedorDatos")
    public void testConsultarPrimaAnual(String edad, String genero, String resultado)
    {
    	driver.findElement(edadLocalizador).sendKeys(edad);
    	driver.findElement(generoLocalizador).sendKeys(genero);
		driver.findElement(btn_consultarLocalizador).click();

        assertEquals( resultado, driver.findElement(bodySelector).getText());
    }

    @After
	public void tearDown()
	{
		driver.quit();
	}
}
