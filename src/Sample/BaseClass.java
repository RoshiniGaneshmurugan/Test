package Sample;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {

	public static void VerifyPlacingOrder() throws Exception {

		System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.automationteststore.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[text()='Login or register']")).click();
		driver.findElement(By.xpath("//*[@id='accountFrm']/fieldset/button")).click();

		driver.findElement(By.id("AccountFrm_firstname")).sendKeys("Roshini");
		driver.findElement(By.id("AccountFrm_lastname")).sendKeys("Raj");
		driver.findElement(By.id("AccountFrm_email")).sendKeys("arjundas@gmail.com");
		driver.findElement(By.id("AccountFrm_address_1")).sendKeys("04, Rose Apartments");
		driver.findElement(By.id("AccountFrm_city")).sendKeys("Chennai");
		driver.findElement(By.id("AccountFrm_postcode")).sendKeys("600300");

		WebElement country = driver.findElement(By.id("AccountFrm_country_id"));
		Select dd1 = new Select(country);
		dd1.selectByVisibleText("India");

		WebElement zone = driver.findElement(By.id("AccountFrm_zone_id"));
		Select dd = new Select(zone);
		dd.selectByVisibleText("Tamil Nadu");

		driver.findElement(By.id("AccountFrm_loginname")).sendKeys("ArjunDas");
		driver.findElement(By.id("AccountFrm_password")).sendKeys("Raj@12345");
		driver.findElement(By.id("AccountFrm_confirm")).sendKeys("Raj@12345");		
		driver.findElement(By.id("AccountFrm_agree")).click();
		driver.findElement(By.xpath("//*[@id='AccountFrm']/div[5]/div/div/button")).click();

		String str = driver.findElement(By.xpath("//*[@id='customer_menu_top']/li/a/div")).getText();
		System.out.println(str);
		if(str.equals("Welcome back Roshini")) {
			System.out.println("Name is displayed correctly");
		}else {
			System.out.println("Name is not displayed correctly");
		}
		
		driver.findElement(By.xpath("//*[@id='categorymenu']/nav/ul/li[5]/a")).click();
		driver.findElement(By.xpath("//a[@title='Add to Cart']")).click();
		driver.findElement(By.xpath("//span[text()='Cart']")).click();		
		driver.findElement(By.xpath("//*[@id='cart_checkout1']")).click();
		
		String str1 = driver.findElement(By.xpath("(//a[text()='ck one shock for him Deodorant'])[2]")).getText();
		System.out.println(str1);
		if(str1.equals("ck one shock for him Deodorant")) {
			System.out.println("Product Name is displayed correctly");
		}else {
			System.out.println("Product Name is not displayed correctly");
		}
		
		driver.findElement(By.id("checkout_btn")).click();
		driver.close();
	}
}