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

public class SearchSubject {
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

	@Test
	public void TC01() throws InterruptedException {
		driver.findElement(By.linkText("Thù lao")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Môn học")).click();
		Thread.sleep(3000);
		// Định vị và nhấp vào ô chứa học kỳ
				WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
				termContainer.click();

				// Định vị và chọn học kỳ 221 trong danh sách
				WebElement termOption = driver.findElement(By.xpath("//li[text()='998']"));
				termOption.click();
				// Định vị và nhấp vào ô chứa học kỳ
				WebElement termContainer1 = driver.findElement(By.xpath("//span[@id='select2-major-container']"));
				termContainer1.click();

				// Định vị và chọn học kỳ 221 trong danh sách
				WebElement termOption2 = driver.findElement(By.xpath("//li[text()='Team10']"));
				termOption2.click();
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("Cơ sở lập trình");
		Thread.sleep(2000);
	}
	@Test
	public void TC02() throws InterruptedException {
		driver.findElement(By.linkText("Thù lao")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Môn học")).click();
		Thread.sleep(3000);
		// Định vị và nhấp vào ô chứa học kỳ
				WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
				termContainer.click();
				// Định vị và chọn học kỳ 221 trong danh sách
				WebElement termOption = driver.findElement(By.xpath("//li[text()='998']"));
				termOption.click();
				// Định vị và nhấp vào ô chứa học kỳ
				WebElement termContainer1 = driver.findElement(By.xpath("//span[@id='select2-major-container']"));
				termContainer1.click();

				// Định vị và chọn học kỳ 221 trong danh sách
				WebElement termOption2 = driver.findElement(By.xpath("//li[text()='Team10']"));
				termOption2.click();
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("AV2");
		Thread.sleep(2000);
		// Định vị phần tử bằng XPath
		WebElement titleElement = driver.findElement(By.xpath("//td[@class='dataTables_empty']"));

		// Lấy nội dung của phần tử và in ra màn hình
		String title = titleElement.getText();
		System.out.println("Thông báo: " + title);

		// Kiểm tra xem tiêu đề có khớp với mong đợi hay không
		String expectedTitle = "Không tìm thấy kết quả";
		if (title.equals(expectedTitle)) {
		    System.out.println("Tiêu đề khớp với mong đợi.");
		} else {
		    System.out.println("Tiêu đề không khớp với mong đợi.");
		}

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		}
}
