package thongbaoloi;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PCGD.ReadInput;

public class TestdongiaCTTCloi {
	private WebDriverWait wait;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
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
		driver.findElement(By.linkText("Thù lao")).click();
		driver.findElement(By.linkText("Đơn giá & hệ số")).click();
		driver.findElement(By.linkText("Đơn giá")).click();

	}

	@Test(priority = 0)
	public void updateAndDeleteCTĐTtieuchuan() throws InterruptedException {
		WebElement termContainer = driver.findElement(By.xpath("//*[@id=\"select2-year-container\"]"));
		termContainer.click();

		// Định vị và chọn học kỳ 998 trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='2034 - 2046']"));
		termOption.click();
		// Cập nhật đơn giá cho CTĐT tiêu chuẩn
		WebElement standardCTDTInput = driver.findElement(By.xpath("(//i[contains(@class, 'feather-edit')])[7]"));
		standardCTDTInput.click();
		Thread.sleep(2000);

		WebElement dongia = driver.findElement(By.xpath("//input[@id='price']"));
		dongia.clear();
		dongia.sendKeys("-1000");

		// Click vào nút "Lưu"
		WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
		saveButton.click();
		Thread.sleep(1000);

		////////////////////////////
		WebElement errorMessage = null;
		try {
			// Tìm phần tử chứa thông báo lỗi
			errorMessage = driver.findElement(By.xpath("//label[@id='price-error']")); // Thay "error-message" bằng
																						// class hoặc XPath của thông
																						// báo lỗi
		} catch (NoSuchElementException e) {
			// Nếu không tìm thấy phần tử, không có lỗi
		}

		if (errorMessage != null) {
			// Nếu có phần tử chứa thông báo lỗi, in ra thông báo
			System.out.println("Có lỗi: " + errorMessage.getText());
		} else {
			// Nếu không có lỗi, in ra thông báo không có lỗi
			System.out.println("Không có lỗi.");
		}
		WebElement huyButton = driver.findElement(By.xpath("//button[@id='btnClose']"));
        huyButton.click();

	}

	@Test(priority = 1)
	public void updatesuatatcad() throws InterruptedException {
		// Click vào nút "Tạm ứng tất cả"
		WebElement tadsuatatcaTC = driver.findElement(By.xpath(
				"//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		tadsuatatcaTC.click();

		// Sửa đơn giá tất cả
		WebElement dongiatatca = driver.findElement(By.xpath("//input[@id='price']"));
		dongiatatca.clear();
		dongiatatca.sendKeys("-12000");

		WebElement saveButtontatca = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
		saveButtontatca.click();

	}

	@AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau khi kiểm thử hoàn thành
        driver.quit();
    }
}


