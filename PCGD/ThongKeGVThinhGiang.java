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

public class ThongKeGVThinhGiang {
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

// Xem theo GVTG
	@Test(priority = 1)
	public void TC01_TKGVTG() throws InterruptedException {
		driver.findElement(By.linkText("Thống kê")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("GV thỉnh giảng")).click();
		Thread.sleep(3000);
		// Click vào ô chọn học kì
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span[1]/span[1]/ul[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[2]/span[1]/span[1]/ul[1]/li[2]"))
				.click();
		Thread.sleep(2000);
		// Tab ra màn hình
		driver.findElement(
				By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]"))
				.click();
		Thread.sleep(2000);
		// Nhấn nút thống kê
		driver.findElement(By.xpath("//button[@id='submit-all']")).click();
		Thread.sleep(2000);
		// Xem danh sách ngành theo danh sách hiển thị (10,25,50,tất cả)
		WebElement listBoxFieldElement1 = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/select[1]"));
		Thread.sleep(1000);
		Select select = new Select(listBoxFieldElement1);
		// kéo trang xuống
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		select.selectByIndex(0);
		Thread.sleep(1000);
		js3.executeScript("window.scrollBy(0, 5000)", "");
		Thread.sleep(3000);
		js3.executeScript("window.scrollBy(0, -5000)", "");
		select.selectByIndex(1);
		Thread.sleep(1000);
		js3.executeScript("window.scrollBy(0, 5000)", "");
		Thread.sleep(3000);
		js3.executeScript("window.scrollBy(0, -5000)", "");
		select.selectByIndex(2);
		Thread.sleep(1000);
		js3.executeScript("window.scrollBy(0, 5000)", "");
		Thread.sleep(3000);
		js3.executeScript("window.scrollBy(0, -5000)", "");
		select.selectByIndex(3);
		Thread.sleep(1000);
		js3.executeScript("window.scrollBy(0, 5000)", "");
		Thread.sleep(3000);
		js3.executeScript("window.scrollBy(0, -5000)", "");
	}

	// Xem theo GVTG và xuất ra file excel
	@Test(priority = 2)
	public void TC02_TKGVTG() throws InterruptedException {
		driver.findElement(By.linkText("Thống kê")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("GV thỉnh giảng")).click();
		Thread.sleep(3000);
		// Click vào ô chọn học kì
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span[1]/span[1]/ul[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[2]/span[1]/span[1]/ul[1]/li[2]"))
				.click();
		Thread.sleep(2000);
		// Tab ra màn hình
		driver.findElement(
				By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]"))
				.click();
		Thread.sleep(2000);
		// Nhấn nút thống kê
		driver.findElement(By.xpath("//button[@id='submit-all']")).click();
		Thread.sleep(2000);
		// chọn nút xuất
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"))
				.click();
		Thread.sleep(2000);
		// nhấn file excel
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[2]/span[1]"))
				.click();
		Thread.sleep(2000);
	}

	// Xem theo GVTG và xuất ra file pdf
	@Test(priority = 3)
	public void TC03_TKGVTG() throws InterruptedException {
		// nhấn file pdf
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[3]/span[1]"))
				.click();
		Thread.sleep(2000);
	}

	// Xem theo GVTG và nhấn copy
	@Test(priority = 4)
	public void TC04_TKGVTG() throws InterruptedException {
		// nhấn file copy
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[4]/span[1]"))
				.click();
		Thread.sleep(5000);
	}

	// Xem theo GVTG và nhấn in
	@Test(priority = 5)
	public void TC05_TKGVTG() throws InterruptedException {
		// nhấn file in ấn 
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]"))
				.click();
		Thread.sleep(1000);
	}

	// Trường hợp chọn tất cả các học kì và báo lỗi
	@Test(priority = 6)
	public void TC06_TKGVTG() throws InterruptedException {
		driver.findElement(By.linkText("Thống kê")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("GV thỉnh giảng")).click();
		Thread.sleep(3000);

		// Click vào ô chọn học kì
		driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span[1]/span[1]/ul[1]"))
				.click();
		Thread.sleep(2000);

		// Chọn tất cả các học kì
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/span[2]/span[1]/div[1]/button[1]/i[1]"))
				.click();
		Thread.sleep(2000);

		// Tab ra màn hình
		driver.findElement(
				By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]"))
				.click();
		Thread.sleep(2000);

		// Nhấn nút thống kê
		driver.findElement(By.xpath("//button[@id='submit-all']")).click();
		Thread.sleep(2000);

		// Kiểm tra thông báo
		WebElement errorAlert = driver.findElement(By.xpath("//div[@class='swal2-html-container']"));
		String alertText = errorAlert.getText().trim();

		// So sánh với thông báo mong đợi
		String expectedAlertText = "Quá nhiều học kỳ đã được chọn, vui lòng chọn tối đa 100 học kỳ!";
		if (alertText.equals(expectedAlertText)) {
			System.out.println("Thông báo chính xác: " + alertText);
		} else {
			System.out.println("Thông báo không chính xác.");
			System.out.println("Thông báo thực tế: " + alertText);
			System.out.println("Thông báo mong đợi: " + expectedAlertText);
			// Nhấn nút ok để thoát thông báo
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[6]/button[1]")).click();
			Thread.sleep(2000);
		}
	}
	@AfterClass
    public void afterClass() {
    	driver.quit();
    	}
}
