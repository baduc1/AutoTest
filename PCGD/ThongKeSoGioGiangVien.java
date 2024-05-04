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
import org.testng.annotations.BeforeTest;

public class ThongKeSoGioGiangVien {
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
		//
		WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
		termContainer.click();
		// Định vị và chọn học kỳ 998 trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='998']"));
		termOption.click();
		////
		WebElement nganh = driver.findElement(By.xpath("//span[@id='select2-major-container']"));
		nganh.click();
		// Định vị và chọn tất cả  trong danh sách
		WebElement nganhOption = driver.findElement(By.xpath("//li[text()='Tất cả']"));
		nganhOption.click();
		///////////////
		WebElement LGV = driver.findElement(By.xpath("//span[@id='select2-lecturerType-container']"));
		LGV .click();
		// Định vị và chọn tất cả  trong danh sách
		WebElement LGVOption = driver.findElement(By.xpath("//li[text()='Cơ hữu']"));
		LGVOption.click();
		Thread.sleep(1500);
		

	}

// Xem theo biểu đồ ca giảng
	@Test(priority = 2)
	public void TC02_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='isLesson']")).click();
		Thread.sleep(3000);
	}

// Xem theo bảng biểu, xuất ra file excel
	@Test(priority = 3)
	public void TC03_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[2]/span[1]"))
				.click();
	}

//Xem theo bảng biểu, xuất ra file PDF
	@Test(priority = 4)
	public void TC04_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[3]/span[1]"))
				.click();
	}

//Xem theo bảng biểu, In ấn
	@Test(priority = 5)
	public void TC05_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]"))
				.click();
	}

//Xem theo bảng biểu, sao chép
	@Test(priority = 6)
	public void TC06_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[2]/a[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[4]/span[1]"))
				.click();
	}
//Xem theo Chi tiết, hiển thị 10,25,50, tất cả
	@Test(priority = 7)
	public void TC07_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath(
				"/d/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]iv[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]"))
				.click();
		Thread.sleep(2000);
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement1);
//kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0, 400)", "");
		Thread.sleep(3000);
		select.selectByIndex(0);
		Thread.sleep(2000);
		select.selectByIndex(1);
		Thread.sleep(2000);
		select.selectByIndex(2);
		Thread.sleep(2000);
		select.selectByIndex(3);
		Thread.sleep(1000);
		js3.executeScript("window.scrollBy(0, 2000)", "");
		Thread.sleep(3000);
	}

//Xem theo chi tiết, ca giảng
	@Test(priority = 8)
	public void TC08_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='isLesson']")).click();
	}

//Xem theo chi tiết, tìm tên và xuất file excel
	@Test(priority = 9)
	public void TC09_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("Luu Ba Duc");
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[2]"))
				.click();
	}

//Xem theo chi tiết, tìm tên và xuất PDF
	@Test(priority = 10)
public void TC010_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("Luu Ba Duc");
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[3]/span[1]"))
				.click();
	}

//Xem theo chi tiết, tìm tên và sao chép
	@Test(priority = 11)
	public void TC011_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("Luu Ba Duc");
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[4]/span[1]"))
				.click();
		Thread.sleep(5000);
	}

//Xem theo chi tiết, tìm tên và In ấn
	@Test(priority = 12)
	public void TC012_TKSGGV() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"))
				.sendKeys("Luu Ba Duc");
		Thread.sleep(2000);
driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]"))
				.click();
		Thread.sleep(1000);
	}
	// Xem theo biểu đồ thống kê theo năm học
		@Test(priority = 13)
		public void TC13_TKSGGV() throws InterruptedException {
			
			driver.findElement(By.xpath("//input[@id='isLesson']")).click();
			Thread.sleep(3000);
			// Tìm phần tử chứa năm học
			WebElement namHocElement = driver.findElement(By.xpath("//li[@id='select2-unit-result-eh8y-year']"));

			// Click vào năm học
			namHocElement.click();
			Thread.sleep(2000);

			// Tìm phần tử chứa học kỳ "2023 - 2024"
			WebElement hocKyElement = driver.findElement(By.xpath("//li[@id='select2-year-result-r5vq-2023 - 2024']"));

			// Click vào học kỳ "2023 - 2024"
			hocKyElement.click();
			Thread.sleep(2000);

			// Tìm phần tử chứa ngành "Tất cả"
			WebElement nganhElement = driver.findElement(By.xpath("//li[@id='select2-major-result-n3mv--1']"));

			// Click vào ngành "Tất cả"
			nganhElement.click();
			Thread.sleep(2000);

		}
}