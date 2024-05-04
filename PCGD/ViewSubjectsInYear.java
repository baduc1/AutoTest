package PCGD;

import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class ViewSubjectsInYear {
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

// Xem theo biểu đồ
	@Test(priority = 1)
	public void TC01_TKSGGV() throws InterruptedException {
		driver.findElement(By.linkText("Thống kê")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Số giờ giảng viên")).click();
		Thread.sleep(3000);
		// Định vị và nhấp vào ô chứa học kỳ
		WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-unit-container']"));
		termContainer.click();
		// Định vị và chọn học kỳ 221 trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='Năm học']"));
		termOption.click();
		WebElement termContainer1 = driver.findElement(By.xpath("//span[@id='select2-year-container']"));
		termContainer1.click();
		// Định vị và chọn học kỳ 221 trong danh sách
		WebElement termOption1 = driver.findElement(By.xpath("//li[text()='2023 - 2024']"));
		termOption1.click();
		Thread.sleep(3000);
		WebElement termContainer2 = driver.findElement(By.xpath("//span[@id='select2-major-container']"));
		termContainer2.click();
		// Định vị và chọn học kỳ 221 trong danh sách
		WebElement termOption2 = driver.findElement(By.xpath("//li[text()='Tất cả']"));
		termOption2.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		// Định vị phần tử bằng XPath
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]"));

		// Lấy nội dung của phần tử và in ra màn hình
		System.out.println(element.getText());

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		}
}