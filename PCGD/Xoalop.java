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

public class Xoalop {
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
	// Xóa lớp không thành công do nhập sai tên xác nhận
	@Test(priority = 0)
	public void TC01() throws InterruptedException {
		driver.findElement(By.linkText("Thời khoá biểu")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Phân công")).click();
		Thread.sleep(3000);
		// Click vào ô muốn xóa
		// Tìm phần tử button theo id
		WebElement buttonElement = driver.findElement(By.id("357257"));

		// Click vào button
		buttonElement.click();
		Thread.sleep(2000);
		// Nhấn vào nút xóa
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[3]/div[2]/div[1]/button[2]/i[1]"))
				.click();
		Thread.sleep(2000);
		// Xác nhận xóa
				driver.findElement(By.xpath(
						"/html[1]/body[1]/div[4]/div[1]/input[1]"))
						.sendKeys("221_71ITBS10103_08");
		// Nhấn nút xác nhận
				// Nhấn nút xác nhận
				driver.findElement(By.xpath(
						"/html[1]/body[1]/div[4]/div[1]/div[6]/button[1]"))
						.click();
				Thread.sleep(2000);
		
		// In ra màn hình và kiểm tra thông báo lưu thành công
		WebElement validationMessageElement = driver.findElement(By.id("swal2-validation-message"));
		String validationMessage = validationMessageElement.getText().trim();

		if (validationMessage.equals("Xác nhận xoá thất bại!")) {
		    System.out.println("Thông báo xác nhận xoá thất bại: Đúng");
		} else {
		    System.out.println("Thông báo xác nhận xoá thất bại: Sai");
		    System.out.println("Nội dung thực tế: " + validationMessage);
		}
		// Nhấn nút hủy
				driver.findElement(By.xpath(
						"/html[1]/body[1]/div[4]/div[1]/div[6]/button[3]"))
						.click();
				Thread.sleep(2000);
	}
	// Xóa lớp thành công
		@Test(priority = 2)
		public void TC02() throws InterruptedException {
			driver.findElement(By.linkText("Thời khoá biểu")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Phân công")).click();
			Thread.sleep(3000);
			// Click vào ô muốn xóa
			// Tìm phần tử button theo id
			WebElement buttonElement = driver.findElement(By.id("357257"));

			// Click vào button
			buttonElement.click();
			Thread.sleep(2000);
			// Nhấn vào nút xóa
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[3]/div[2]/div[1]/button[2]/i[1]"))
					.click();
			Thread.sleep(2000);
			// Xác nhận xóa
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[4]/div[1]/input[1]"))
							.sendKeys("221_71ITBS10203_0501");
			// Nhấn nút xác nhận
					// Nhấn nút xác nhận
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[4]/div[1]/div[6]/button[1]"))
							.click();
					Thread.sleep(2000);
			Thread.sleep(2000);
			// In ra màn hình và kiểm tra thông báo lưu thành công
		    WebElement successToast = driver.findElement(By.className("toast-message"));
		    String toastMessage = successToast.getText().trim();
		    if (toastMessage.equals("Xóa lớp thành công!")) {
		        System.out.println("TC01 - Thông báo xóa lớp thành công: Đúng");
		    } else {
		        System.out.println("TC01 - Thông báo xóa lớp thành công: Sai");
		        System.out.println("Thông báo thực tế: " + toastMessage);
		    }
		}
		@AfterClass
	    public void afterClass() {
	    	driver.quit();
	    	}
}
