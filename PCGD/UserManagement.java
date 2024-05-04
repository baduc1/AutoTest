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

public class UserManagement {
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
	public void TC01_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement);
		// kéo trang xuống
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select.selectByIndex(1);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void TC02_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement);
		// kéo trang xuống
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select.selectByIndex(1);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(3);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(4);
		js.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void TC03_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(1);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(1);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(3);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(1000);
	}

	@Test(priority = 4)
	public void TC04_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(1);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void TC05_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(1);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(3);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void TC06_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(1);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(4);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 7)
	public void TC07_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(2);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(1);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(3);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(1000);
	}

	@Test(priority = 8)
	public void TC08_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(2);
		Thread.sleep(2000);

		// Xem danh sách ngành theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(2);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 9)
	public void TC09_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem ngành theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(2);
		Thread.sleep(2000);

		// Xem theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(3);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}

	@Test(priority = 10)
	public void TC10_UserManagement() throws InterruptedException {
		driver.findElement(By.linkText("Người dùng")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

		// Xem danh sách theo danh sách hiển thị theo loại giảng viên
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[1]/select[1]"));
		Thread.sleep(1000);
		Select select1 = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select1.selectByIndex(2);
		Thread.sleep(2000);

		// Xem danh sách theo danh sách hiển thị Role
		WebElement listBoxFieldElement2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[1]/div[2]/div[2]/select[1]"));
		Thread.sleep(1000);
		Select select2 = new Select(listBoxFieldElement2);
		// kéo trang xuống
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		select2.selectByIndex(4);
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 2000)", "");
		// Xem theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement3 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement3);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(0);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(1);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(2);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
		select.selectByIndex(3);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(2000);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		}
}
