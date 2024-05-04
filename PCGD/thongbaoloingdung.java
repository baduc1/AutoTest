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

public class thongbaoloingdung {
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
		driver.findElement(By.linkText("Người dùng")).click();
	}

	@Test
	public void nguoidung() throws InterruptedException, AWTException, IOException {
		FileInputStream fis = new FileInputStream("C:\\them_sua_ngdung.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			String magv = row.getCell(0).getStringCellValue();
			String tengv = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();
			String loaigv = row.getCell(3).getStringCellValue();
			String role = row.getCell(4).getStringCellValue();
			String email1 = row.getCell(5).getStringCellValue();

			// Them, Xoa, Sua nguoi dung (Thông Báo lỗi)
			// Them nguoi dung
			driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblUser_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi = magv;
			String KetquaThucTe = driver.findElement(By.xpath("//*[@id=\"staff_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe, KetquaMongDoi, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi1 = tengv;
			String KetquaThucTe1 = driver.findElement(By.xpath("//*[@id=\"full_name-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe1, KetquaMongDoi1, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi3 = loaigv;
			String KetquaThucTe3 = driver.findElement(By.xpath("//*[@id=\"type-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe3, KetquaMongDoi3, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi4 = role;
			String KetquaThucTe4 = driver.findElement(By.xpath("//*[@id=\"role_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe4, KetquaMongDoi4, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblUser_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"staff_id\"]")).sendKeys(".");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(".");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi5 = magv;
			String KetquaThucTe5 = driver.findElement(By.xpath("//*[@id=\"staff_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe5, KetquaMongDoi5, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi2 = email;
			String KetquaThucTe2 = driver.findElement(By.xpath("//*[@id=\"email-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe2, KetquaMongDoi2, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			// chinh sua nguoi dung (thong bao loi)
			driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblUser\"]/tbody/tr[2]/td[7]/a[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"staff_id\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"full_name\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi7 = magv;
			String KetquaThucTe7 = driver.findElement(By.xpath("//*[@id=\"staff_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe7, KetquaMongDoi7, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi8 = tengv;
			String KetquaThucTe8 = driver.findElement(By.xpath("//*[@id=\"full_name-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe8, KetquaMongDoi8, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi6 = email;
			String KetquaThucTe6 = driver.findElement(By.xpath("//*[@id=\"email-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe6, KetquaMongDoi6, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			driver.findElement(By.xpath("//*[@id=\"staff_id\"]")).sendKeys(".");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(".");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi10 = magv;
			String KetquaThucTe10 = driver.findElement(By.xpath("//*[@id=\"staff_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe10, KetquaMongDoi10,
					"Fail: Kết quả mong đợi và kết quả thực tế không khớp");

			String KetquaMongDoi9 = email1;
			String KetquaThucTe9 = driver.findElement(By.xpath("//*[@id=\"email-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe9, KetquaMongDoi9, "Fail: Kết quả mong đợi và kết quả thực tế không khớp");
		}
	}
}
