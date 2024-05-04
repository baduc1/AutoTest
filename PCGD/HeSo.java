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

public class HeSo {
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

// Sửa hệ số tiếng việt ( tằng )
	@Test(priority = 1)
	public void TC01_Heso() throws InterruptedException {
		driver.findElement(By.linkText("Thù lao")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Đơn giá & hệ số")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//a[@id='coefficient-tab']"))
				.click();
		// Sửa hệ số 
		driver.findElement(By.xpath(
				"//tbody/tr[1]/td[5]/a[1]/i[1]"))
				.click();
		// click vào nút tăng hệ số tiếng việt
		driver.findElement(By.xpath(
				"//body[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/span[2]/button[1]"))
				.click();
		Thread.sleep(1000);
		// nhấn lưu 
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
				.click();
		Thread.sleep(2000);
		// In ra màn hình và kiểm tra thông báo lưu thành công
	    WebElement successToast = driver.findElement(By.className("toast-message"));
	    String toastMessage = successToast.getText().trim();
	    if (toastMessage.equals("Lưu thành công!")) {
	        System.out.println("TC01_Heso - Thông báo lưu thành công: Đúng");
	    } else {
	        System.out.println("TC01_Heso - Thông báo lưu thành công: Sai");
	        System.out.println("Thông báo thực tế: " + toastMessage);
	    }
	}
	// Sửa hệ số tiếng việt ( giảm )
		@Test(priority = 2)
		public void TC02_Heso() throws InterruptedException {
			
			// Sửa hệ số 
			driver.findElement(By.xpath(
					"//tbody/tr[1]/td[5]/a[1]/i[1]"))
					.click();
			// click vào nút giảm hệ số tiếng việt
			driver.findElement(By.xpath(
					"//body[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/span[1]/button[1]"))
					.click();
			Thread.sleep(1000);
			// nhấn lưu 
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
					.click();
			Thread.sleep(2000);
			// In ra màn hình và kiểm tra thông báo lưu thành công
		    WebElement successToast = driver.findElement(By.className("toast-message"));
		    String toastMessage = successToast.getText().trim();
		    if (toastMessage.equals("Lưu thành công!")) {
		        System.out.println("TC02_Heso - Thông báo lưu thành công: Đúng");
		    } else {
		        System.out.println("TC02_Heso - Thông báo lưu thành công: Sai");
		        System.out.println("Thông báo thực tế: " + toastMessage);
		    }
		}
		// Sửa hệ số tiếng anh ( tăng )
				@Test(priority = 3)
				public void TC03_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút tăng hệ số tiếng anh
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[2]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC03_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC03_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số tiếng anh ( giảm )
				@Test(priority = 4)
				public void TC04_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút giảm hệ số tiếng anh
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[2]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC04_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC04_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số lý thuyết ( tăng )
				@Test(priority = 5)
				public void TC05_Heso() throws InterruptedException {
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút tăng hệ số lý thuyết
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC05_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC05_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số lý thuyết ( Giảm )
				@Test(priority = 6)
				public void TC06_Heso() throws InterruptedException {
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút giảm hệ số lý thuyết
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC06_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC06_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số thực hành ( tăng )
				@Test(priority = 7)
				public void TC07_Heso() throws InterruptedException {
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút tăng hệ số thực hành
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[2]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC07_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC07_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số thực hành ( giảm )
				@Test(priority = 8)
				public void TC08_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút giảm hệ số thực hành
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[2]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(3000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC08_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC08_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số cả 4 (tăng )
				@Test(priority = 9)
				public void TC09_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút tăng hệ số TV
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút tăng hệ số tiếng anh
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[2]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút tăng hệ số lý thuyết
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút tăng hệ số thực hành
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[2]/div[1]/span[2]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC09_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC09_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
	}
				// Sửa hệ số cả 4 (Giảm )
				@Test(priority = 10)
				public void TC10_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
					// click vào nút giảm hệ số tiếng việt
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút giảm hệ số tiếng anh
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[1]/div[2]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút giảm hệ số lý thuyết
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// click vào nút giảm hệ số thực hành
					driver.findElement(By.xpath(
							"//body[1]/div[3]/div[2]/form[1]/div[2]/div[2]/div[1]/span[1]/button[1]"))
							.click();
					Thread.sleep(1000);
					// nhấn lưu 
					driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]/form[1]/div[3]/button[2]"))
							.click();
					Thread.sleep(2000);
					// In ra màn hình và kiểm tra thông báo lưu thành công
				    WebElement successToast = driver.findElement(By.className("toast-message"));
				    String toastMessage = successToast.getText().trim();
				    if (toastMessage.equals("Lưu thành công!")) {
				        System.out.println("TC10_Heso - Thông báo lưu thành công: Đúng");
				    } else {
				        System.out.println("TC10_Heso - Thông báo lưu thành công: Sai");
				        System.out.println("Thông báo thực tế: " + toastMessage);
				    }
					
	}
				// Xóa hệ số tiếng Việt và nhập kí tự "a"
				@Test(priority = 11)
				public void TC11_Heso() throws InterruptedException {
					
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
				    // Nhấn vào ô nhập hệ số tiếng Việt
				    WebElement heSoVietInput = driver.findElement(By.id("vietnamese_coefficient"));
				    heSoVietInput.clear(); // Xóa giá trị hiện tại
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc xóa

				    // Nhập kí tự "a"
				    heSoVietInput.sendKeys("a");
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc nhập kí tự "a"
				    driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]"))
							.click();

				    // Kiểm tra và in ra thông báo lỗi
				    WebElement errorMessage = driver.findElement(By.id("vietnamese_coefficient-error"));
				    String actualErrorMessage = errorMessage.getText();
				    String expectedErrorMessage = "Vui lòng nhập lớn hơn hoặc bằng 1";

				    System.out.println("Thông báo lỗi: " + actualErrorMessage);

				    // Kiểm tra xem thông báo lỗi có đúng hay không
				    if (actualErrorMessage.equals(expectedErrorMessage)) {
				        System.out.println("Thông báo lỗi chính xác.");
				    } else {
				        System.out.println("Thông báo lỗi không chính xác.");
				    }
				}
				// Xóa hệ số tiếng Việt và nhập kí tự lớn hơn cận biên trên là 10
				@Test(priority = 12)
				public void TC12_Heso() throws InterruptedException {
					driver.findElement(By.linkText("Thù lao")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Đơn giá & hệ số")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath(
							"//a[@id='coefficient-tab']"))
							.click();
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
				    // Nhấn vào ô nhập hệ số tiếng Việt
				    WebElement heSoVietInput = driver.findElement(By.id("vietnamese_coefficient"));
				    heSoVietInput.clear(); // Xóa giá trị hiện tại
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc xóa

				    // Nhập kí tự "a"
				    heSoVietInput.sendKeys("10");
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc nhập kí tự "a"
				    driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]"))
							.click();

				    // Kiểm tra và in ra thông báo lỗi
				    WebElement errorMessage = driver.findElement(By.id("vietnamese_coefficient-error"));
				    String actualErrorMessage = errorMessage.getText();
				    String expectedErrorMessage = "Vui lòng nhập nhỏ hơn hoặc bằng 9.99";

				    System.out.println("Thông báo lỗi: " + actualErrorMessage);

				    // Kiểm tra xem thông báo lỗi có đúng hay không
				    if (actualErrorMessage.equals(expectedErrorMessage)) {
				        System.out.println("Thông báo lỗi chính xác.");
				    } else {
				        System.out.println("Thông báo lỗi không chính xác.");
				    }
				}
				// Xóa hệ số tiếng Việt và nhập kí tự là số ở cận biên dưới là 0.9
				@Test(priority = 13)
				public void TC13_Heso() throws InterruptedException {
					driver.findElement(By.linkText("Thù lao")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Đơn giá & hệ số")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath(
							"//a[@id='coefficient-tab']"))
							.click();
					// Sửa hệ số 
					driver.findElement(By.xpath(
							"//tbody/tr[1]/td[5]/a[1]/i[1]"))
							.click();
				    // Nhấn vào ô nhập hệ số tiếng Việt
				    WebElement heSoVietInput = driver.findElement(By.id("vietnamese_coefficient"));
				    heSoVietInput.clear(); // Xóa giá trị hiện tại
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc xóa

				    // Nhập kí tự "a"
				    heSoVietInput.sendKeys("0.9");
				    Thread.sleep(1000); // Đợi 1 giây để xác nhận việc nhập kí tự "a"
				    driver.findElement(By.xpath(
							"/html[1]/body[1]/div[3]/div[2]"))
							.click();

				    // Kiểm tra và in ra thông báo lỗi
				    WebElement errorMessage = driver.findElement(By.id("vietnamese_coefficient-error"));
				    String actualErrorMessage = errorMessage.getText();
				    String expectedErrorMessage = "Vui lòng nhập lớn hơn hoặc bằng 1";

				    System.out.println("Thông báo lỗi: " + actualErrorMessage);

				    // Kiểm tra xem thông báo lỗi có đúng hay không
				    if (actualErrorMessage.equals(expectedErrorMessage)) {
				        System.out.println("Thông báo lỗi chính xác.");
				    } else {
				        System.out.println("Thông báo lỗi không chính xác.");
				    }
				}
				@AfterClass
				public void afterClass() {
					driver.quit();
					}
}
