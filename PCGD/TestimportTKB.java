package PCGD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestimportTKB {
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

		driver.findElement(By.linkText("Thời khoá biểu")).click();
		driver.findElement(By.linkText("Import TKB")).click();

	}

	@Test
    public void Import_TKB() throws AWTException, InterruptedException {

        WebElement termDropdown = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
        termDropdown.click();
        Thread.sleep(3000);
        WebElement termInput = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
        termInput.sendKeys("731"+ Keys.ENTER);
        Thread.sleep(3000);
        WebElement majorDropdown = driver.findElement(By.xpath("//span[@id='select2-major-container']"));
        majorDropdown.click();
        Thread.sleep(3000);
        WebElement inputField = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
        inputField.sendKeys("Công nghệ điện tử"+ Keys.ENTER);
        Thread.sleep(3000);


        // uploadfile
        Thread.sleep(3000); // Dừng 3 giây để chờ cho trang tải hoàn tất
        driver.findElement(By.id("dpz-single-file")).click(); // Nhấn vào phần tử cho phép chọn tệp
        driver.switchTo().activeElement(); // Chuyển trỏ chuột đến phần tử đang hoạt động

        String filePath = "C:\\CNTT UIS-ThoiKhoaBieu_TieuChuan_Mau.xlsx"; // Đường dẫn tới tệp cần tải lên
        File file = new File(filePath); // Tạo đối tượng File từ đường dẫn tệp
        String absolutePath = file.getAbsolutePath(); // Lấy đường dẫn tuyệt đối của tệp

        StringSelection ss = new StringSelection(absolutePath); // Tạo StringSelection chứa đường dẫn tuyệt đối của tệp
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null); // Sao chép đường dẫn tệp vào clipboard hệ thống
        Thread.sleep(3000); // Dừng 3 giây để đợi cho clipboard được đặt

        Robot robot = new Robot(); // Tạo đối tượng Robot để tự động nhập phím
        robot.keyPress(KeyEvent.VK_CONTROL); // Nhấn phím CONTROL
        robot.keyPress(KeyEvent.VK_V); // Nhấn phím V (phím dán)
        robot.keyRelease(KeyEvent.VK_V); // Thả phím V
        robot.keyRelease(KeyEvent.VK_CONTROL); // Thả phím CONTROL
        robot.keyPress(KeyEvent.VK_ENTER); // Nhấn phím ENTER
        robot.keyRelease(KeyEvent.VK_ENTER); // Thả phím ENTER
        Thread.sleep(3000); // Dừng 3 giây để đợi cho việc tải lên hoàn tất

        driver.findElement(By.id("submit-all")).click(); // Nhấn vào nút "Tải lên"
        Thread.sleep(30000); // Dừng 30 giây để đợi cho việc tải lên hoàn tất

        // Tìm và lấy nội dung của thông báo thành công
        WebElement successMessage = driver.findElement(By.id("swal2-html-container"));
        String messageText = successMessage.getText(); // Lấy văn bản của thông báo thành công
        Thread.sleep(3000); // Dừng 3 giây để đợi cho việc thông báo được hiển thị

        System.out.println(messageText); // In ra thông báo thành công

        // Nhấn vào nút "OK"
        WebElement okButton = driver.findElement(By.className("swal2-confirm"));
        okButton.click(); // Nhấn vào nút OK trên thông báo

    }
}
