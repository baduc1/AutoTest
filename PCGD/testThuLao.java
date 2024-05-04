package PCGD;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class testThuLao {
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
		driver.findElement(By.linkText("Thù lao GV")).click();

	}

	@Test(priority = 0)
	public void ThongkethulaoGV() throws InterruptedException {

		// Định vị và nhấp vào ô chứa học kỳ
		WebElement termContainer = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
		termContainer.click();

		// Định vị và chọn học kỳ 221 trong danh sách
		WebElement termOption = driver.findElement(By.xpath("//li[text()='221']"));
		termOption.click();
		// Định vị ô tìm kiếm thù lao giảng viên
		WebElement searchInput = driver.findElement(By.xpath("//label[contains(text(),'Tìm kiếm')]/input"));

		// Nhập dữ liệu tìm kiếm vào ô nhập liệu
		String searchKeyword = "Nguyễn Hồng Diên";
		searchInput.sendKeys(searchKeyword);

		// Nhấn phím Enter để thực hiện tìm kiếm
		searchInput.sendKeys(Keys.ENTER);

	}

	@Test(priority = 1)
	public void ExportPDFthulaoGV() throws InterruptedException {

		// Định vị và nhấp vào ô chứa học kỳ
		WebElement export = driver.findElement(By.xpath(
				"//button[@class='dt-button buttons-collection btn btn-outline-secondary dropdown-toggle me-2']"));
		export.click();

		// PDF
		WebElement exportOptionPDF = driver.findElement(By.xpath("//span[contains(text(), 'PDF')]"));
		exportOptionPDF.click();
		// EXcel
		WebElement exportOptionexcel = driver.findElement(By.xpath("//span[contains(text(),'Excel')]"));
		exportOptionexcel.click();
		// sao chép
		WebElement exportOptionsaochep = driver.findElement(By.xpath("//span[contains(text(),'Sao chép')]"));
		exportOptionsaochep.click();
		// in ấn
		WebElement exportOptioninan = driver.findElement(By.xpath("//span[contains(text(),'In ấn')]"));
		exportOptioninan.click();

	}
	@AfterClass
	  public void afterClass() {
	      driver.quit();
	  }
	}


