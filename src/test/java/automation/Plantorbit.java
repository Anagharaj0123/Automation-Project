package automation;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Plantorbit {
	WebDriver driver;
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
	}
	@BeforeMethod
	public void url() {
		driver.get("https://plantorbit.com/");
		driver.manage().window().maximize();
	}
	@Test
	public void test1() {
		WebElement logo = driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/h1/a"));
		if(logo.isDisplayed()) {
			System.out.println("logo is visible");
		}
		else {
			System.out.println("logo is invisible");
		}
	}
	@Test
	public void test2() throws IOException {
		driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/div/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"CustomerEmail\"]")).sendKeys("hello@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"CustomerPassword\"]")).sendKeys("abcgdhlkk");
		driver.findElement(By.xpath("//*[@id=\"customer_login\"]/button")).sendKeys("selenium",Keys.ENTER);
		String expec = "https://plantorbit.com/account";
		if(driver.getCurrentUrl().equals(expec)) {
			System.out.println("login successful");
		}
		else {
			System.out.println("login failed");
		}
		driver.navigate().back();
	}
	@Test
	public void test3() {
		driver.findElement(By.xpath("//*[@id=\"Details-HeaderMenu-1\"]/summary/span")).click();
		driver.findElement(By.xpath("//*[@id=\"HeaderMenu-MenuList-1\"]/li[1]/a")).click();
		driver.navigate().back();
	}
	@Test
	public void test4() {
		driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/div/details-modal")).click();
		driver.findElement(By.id("Search-In-Modal")).sendKeys("cactus");
		driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/div/details-modal/details/div/div[2]/predictive-search/form/div[1]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"CardLink--6939653013643\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"product-form-template--15165865394315__main\"]/div/button")).click();
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/a")).click();
	}
	@Test
	public void test5() throws Exception {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("./screenshot//screenshot1.png"));
	}
	@Test
	public void test6() throws Exception {
		WebElement logoElem = driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/sticky-header/header/h1/a"));
		File src2 = logoElem.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src2, new File("./screenshot//screenshot2.png"));
	}
	@Test
	public void test7() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\"shopify-section-footer\"]/footer/div[1]/div[1]/div[3]/ul/li[1]/a")));
		driver.findElement(By.xpath("//*[@id=\"shopify-section-footer\"]/footer/div[1]/div[1]/div[3]/ul/li[1]/a")).click();
	}
	@Test
	public void test8() {
		String parentWindow = driver.getWindowHandle();
		System.out.println("parent window title " +driver.getTitle());
		driver.findElement(By.xpath("//*[@id=\"CollapsibleAccordion-collapsible_row_fgR4dy-template--15165865427083__collapsible_content_eMNNGW\"]/ul/li[1]/a")).click();
		Set<String> allwindowhandles = driver.getWindowHandles();
		for(String handle:allwindowhandles) {
			System.out.println(handle);
			if(!handle.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(handle);
				driver.findElement(By.xpath("//*[@id=\"CardLink-template--15165865361547__main-collection-product-grid-6939720614027\"]")).click();
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}
	}
	@Test
	public void test9() throws Exception {
		URL ob = new URL("https://plantorbit.com/");
		HttpURLConnection con = (HttpURLConnection)ob.openConnection();
		if(con.getResponseCode()==200) {
			System.out.println("Valid");
		}
		else {
			System.out.println("Invalid");
		}
	}
	@Test
	public void test10() {
		List<WebElement> li = driver.findElements(By.tagName("a"));
		for(WebElement w : li) {
			String link = w.getAttribute("href");
			String name = w.getText();
			System.out.println(link+"---"+name);
		}
	}
	@AfterTest
	public void close() {
		driver.quit();
	}
}
