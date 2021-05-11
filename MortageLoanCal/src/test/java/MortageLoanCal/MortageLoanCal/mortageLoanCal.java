package MortageLoanCal.MortageLoanCal;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class mortageLoanCal {
	
	private static WebDriver driver;
	
	@BeforeClass	
	public static void Browser()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
	}
	
	@Test
	public void monthlyPaymentTest()
	{
		driver.get("https://www.mortgageloan.com/calculator");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ModalCTA_ML_ElementSectionCTA3866")));
        driver.findElement(By.xpath("/html/body/div[3]/div/div/span/span")).click();
        driver.findElement(By.id("KJE-LOAN_AMOUNT")).clear();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("KJE-LOAN_AMOUNT")).sendKeys("$200000");
        Select val = new Select(driver.findElement(By.id("KJE-TERM")));
        val.selectByValue("30");
        driver.findElement(By.id("KJE-INTEREST_RATE")).clear();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("KJE-INTEREST_RATE")).sendKeys("5%");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("KJE-BY_YEAR1")).click();
        driver.findElement(By.name("KJECalculate")).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("KJEGraphTitle")));
        String monPay = driver.findElement(By.id("KJETitle")).getText();
        System.out.println(monPay);
        Assert.assertEquals(monPay, driver.findElement(By.id("KJETitle")).getText());
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        String totPayment = driver.findElement(By.xpath("//*[@id=\"KJE-C-GRAPH1\"]/div/table/tbody/tr/td[1]/h2")).getText();
		System.out.println(totPayment);
		Assert.assertEquals(totPayment,driver.findElement(By.xpath("//*[@id=\"KJE-C-GRAPH1\"]/div/table/tbody/tr/td[1]/h2")).getText());
	}

	@AfterClass
	public static void close()
	{
		System.out.println("done");
		driver.quit();
	}

}
