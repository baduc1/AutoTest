package PCGD;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class PhanCongGV {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	ReadInput read = new ReadInput();
	String[] credentials = ReadInput.readCredentials();
	String username = credentials[0];
	String password = credentials[1];

	@BeforeTest
	public void beforeClass() throws InterruptedException {
		// Thiết lập WebDriver
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

		// Xác nhận trang không an toàn
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();

		// Đọc cookies từ file config.properties
		String cookies = ReadInput.readCookies();

		// Kiểm tra và thiết lập cookies cho trình duyệt
		if (cookies != null && !cookies.isEmpty()) {
			String[] cookiePairs = cookies.split(";");
			cookiePairs[0] = cookiePairs[0].substring(1);
			cookiePairs[cookiePairs.length - 1] = cookiePairs[cookiePairs.length - 1].substring(0,
					cookiePairs[cookiePairs.length - 1].length() - 1);

			for (String cookiePair : cookiePairs) {
				String[] cookieParts = cookiePair.split("=", 2);
				if (cookieParts.length == 2) {
					String cookieName = cookieParts[0].trim();
					String cookieValue = cookieParts[1].trim();
					driver.manage().addCookie(new Cookie(cookieName, cookieValue));
				}
			}
			driver.navigate().refresh();
			WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofHours(10));
			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
			Thread.sleep(3000);
		}
	}

	@Test(priority = 0)
	public void TC01_phancong() throws InterruptedException {
		driver.findElement(By.linkText("Thời khoá biểu")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Phân công")).click();
		Thread.sleep(2000);
		WebElement termContainer = driver.findElement(By.xpath("//span[@class='select2-selection__rendered' and @id='select2-term-container']"));
		termContainer.click();

		// Định vị và chọn học kỳ 998 trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='998']"));
		termOption.click();

		// Định vị và nhấp vào ô chứa ngành
		WebElement nganhContainer = driver.findElement(By.xpath("//span[@class='select2-selection__rendered' and @id='select2-major-container']"));
		nganhContainer.click();

		// Định vị và chọn ngành
		WebElement nganhOption = driver.findElement(By.xpath("//li[text()='CÔNG NGHỆ THÔNG TIN']"));
		nganhOption.click();

		// chọn vào ô trống để phân công giảng viên
		driver.findElement(By.xpath("//button[@class='btn assign-card text-nowrap my-25 p-25 btn-secondary unassigned-theory' and @type='button' and @id='312894']"))
		.click();
		
		WebElement chonGV = driver.findElement(By.xpath("//span[@id='select2-lecturer-b0-container']"));
		chonGV.click();
		

		//nhấn nút buton
		WebElement button = driver.findElement(By.xpath("//body/div[@id='popover815489']/div[2]/div[1]/button[1]/i[1]"));
		button.click();
		
	}
}