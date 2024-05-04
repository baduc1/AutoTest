package thongbaoloi;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PCGD.ReadInput;

public class thongbaoloiHK {
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
			// Lam moi trang de ap dung cookie
			driver.navigate().refresh();
			WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofHours(10));
			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
		}
		driver.findElement(By.linkText("Học kỳ và ngành")).click();
	}

	@Test
	public void HK() throws InterruptedException, AWTException, IOException {

		// Them, Xoa, Sua HK (Thông Báo lỗi)
		// Them HK
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Term");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='number']")).sendKeys("E");
		Thread.sleep(2000);
		WebElement termContainer = driver.findElement(By.id("select2-start_year-container"));
		termContainer.click();
		// Định vị và chọn năm trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='2026']"));
		termOption.click();
		//
		WebElement namkt = driver.findElement(By.id("select2-end_year-container"));
		namkt.click();
		// Định vị và chọn năm trong danh sách
		WebElement namktOption = driver.findElement(By.xpath("//li[text()='2024']"));
		namktOption.click();
		driver.findElement(By.xpath("//*[@id=\"start_week\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"start_week\"]")).sendKeys("-1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"max_lesson\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"max_lesson\"]")).sendKeys("-1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"max_class\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"max_class\"]")).sendKeys("-1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnClose']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"tblTerm_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("22");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"start_week\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"start_week\"]")).sendKeys("-1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"max_lesson\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"max_lesson\"]")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"max_class\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"max_class\"]")).sendKeys("0.1");
		Thread.sleep(2000);
		WebElement termContainer1 = driver.findElement(By.id("select2-start_year-container"));
		termContainer1.click();
		// Định vị và chọn năm trong danh sách
		WebElement termOption1 = driver.findElement(By.xpath("//li[text()='2027']"));
		termOption1.click();
		//
		WebElement namkt1 = driver.findElement(By.id("select2-end_year-container"));
		namkt1.click();
		// Định vị và chọn năm trong danh sách
		WebElement namktOption1 = driver.findElement(By.xpath("//li[text()='2024']"));
		namktOption1.click();
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnClose']")).click();
		Thread.sleep(2000);
		// Kiểm tra xem có thông báo lỗi xuất hiện không
		boolean isErrorDisplayed = isErrorMessageDisplayed();
		if (isErrorDisplayed) {
			System.out.println("Không có thông báo lỗi.");
		} else {
			System.out.println("Đã xuất hiện thông báo lỗi.");
		}
	}

	// Phương thức kiểm tra xem thông báo lỗi có xuất hiện không
	private boolean isErrorMessageDisplayed() {
		// Thực hiện kiểm tra xem phần tử chứa thông báo lỗi có tồn tại không
		try {
			WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message']"));
			return errorMessage.isDisplayed();
		} catch (NoSuchElementException e) {
			return false; // Không tìm thấy phần tử chứa thông báo lỗi
		}
	}

}
