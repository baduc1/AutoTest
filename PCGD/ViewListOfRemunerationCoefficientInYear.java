package PCGD;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class ViewListOfRemunerationCoefficientInYear {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	ReadInput read = new ReadInput();
	String[] credentials = ReadInput.readCredentials();
	String username = credentials[0];
	String password = credentials[1];

	@BeforeTest
	public void beforeClass() throws InterruptedException {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\Google\\\\Chrome");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
		WebElement acceptButton = driver.findElement(By.id("details-button"));
		acceptButton.click();
		WebElement acceptLink = driver.findElement(By.id("proceed-link"));
		acceptLink.click();
		// Doc cookies tu file config.properties
		String cookies = ReadInput.readCookies();
		// Kiem tra xem cookies co ton tai khong
		if (cookies != null && !cookies.isEmpty()) {
			// Tach cookies thanh cac cap key-value
			String[] cookiePairs = cookies.split(";");
			// xoa [] trong chuoi
			cookiePairs[0] = cookiePairs[0].substring(1);
			cookiePairs[cookiePairs.length - 1] = cookiePairs[cookiePairs.length - 1].substring(0,
					cookiePairs[cookiePairs.length - 1].length() - 1);
			// Thiet lap cookies cho trinh duyet
			for (String cookiePair : cookiePairs) {
				String[] cookieParts = cookiePair.split("=", 2);
				if (cookieParts.length == 2) {
					String cookieName = cookieParts[0].trim();
					String cookieValue = cookieParts[1].trim();
					driver.manage().addCookie(new Cookie(cookieName, cookieValue));
				}
			}
//Lam moi trang de ap dung cookie
			driver.navigate().refresh();
			WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofHours(10));
			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
			Thread.sleep(3000);
			// Assert.assertEquals(driver.getCurrentUrl(),
			// "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
			// driver.quit();
		}
	}

	@Test(priority = 1)
	public void TC01_ViewOfList() throws InterruptedException {
	    driver.findElement(By.linkText("Thù lao")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Đơn giá & hệ số")).click();
	    Thread.sleep(3000);
	    // Định vị và nhấp vào ô chứa ngành
	    WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-year-container']"));
	    termContainer.click();
	    // Định vị và chọn ngành tất cả trong danh sách
	    WebElement termOption = driver.findElement(By.xpath("//li[text()='2023 - 2027']"));
	    termOption.click();
	 // Chọn trương trình ĐT Đặc biệt
	    driver.findElement(By.linkText("CTĐT tiêu chuẩn")).click();
	    Thread.sleep(3000);
	    // kéo trang xuống
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0, 50000)", "");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -50000)", "");
	    Thread.sleep(3000);
	}
	@Test(priority = 2)
	public void TC02_ViewOfList() throws InterruptedException {
	    // Chọn trương trình ĐT Đặc biệt
	    driver.findElement(By.linkText("CTĐT đặc biệt")).click();
	    Thread.sleep(3000);
	    // kéo trang xuống
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0, 50000)", "");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -50000)", "");
	    Thread.sleep(3000);
	}
	@Test(priority = 3)
	public void TC03_ViewOfList() throws InterruptedException {
	    // Chọn trương trình ĐT Đặc biệt
	    driver.findElement(By.linkText("Người nước ngoài")).click();
	    Thread.sleep(3000);
	    // kéo trang xuống
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0, 50000)", "");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -50000)", "");
	    Thread.sleep(3000);
	}
	@AfterClass
    public void afterClass() {
    	driver.quit();
    	}
}