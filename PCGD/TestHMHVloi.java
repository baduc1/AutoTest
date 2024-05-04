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
import org.openqa.selenium.NoSuchElementException;
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

public class TestHMHVloi {
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
	}

	@Test(priority = 1) // thông báo lỗi học hàm học vị
	public void hocham() throws InterruptedException, AWTException, IOException {
		FileInputStream fis = new FileInputStream("C:\\them_sua_hk.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            String ma_ham = "";
            String ten_ham = "";
            String thutu = "";
            String ma_ham1 = "";
            String thutu1 = "";
            // Kiểm tra và gán giá trị cho các biến
            if (row.getCell(0) != null) {
                ma_ham = row.getCell(0).getStringCellValue();
            }

            if (row.getCell(1) != null) {
                ten_ham = row.getCell(1).getStringCellValue();
            }

            if (row.getCell(2) != null) {
                thutu = row.getCell(2).getStringCellValue();
            }

            if (row.getCell(3) != null) {
                ma_ham1 = row.getCell(3).getStringCellValue();
            }

            if (row.getCell(4) != null) {
                thutu1 = row.getCell(4).getStringCellValue();
            }

			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegree");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblAcademicDegree_wrapper\"]/div[1]/div[2]/div/div[2]/button"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"level\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"academicdegree-form\"]/div[4]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi = ma_ham;
			String KetquaThucTe = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe, KetquaMongDoi);

			String KetquaMongDoi2 = ten_ham;
			String KetquaThucTe2 = driver.findElement(By.xpath("//*[@id=\"name-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe2, KetquaMongDoi2);

			String KetquaMongDoi3 = thutu;
			String KetquaThucTe3 = driver.findElement(By.xpath("//*[@id=\"level-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe3, KetquaMongDoi3);

			driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys(".");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"level\"]")).sendKeys("-1");
			Thread.sleep(2000);

			String KetquaMongDoi4 = ma_ham1;
			String KetquaThucTe4 = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe4, KetquaMongDoi4);

			String KetquaMongDoi5 = thutu1;
			String KetquaThucTe5 = driver.findElement(By.xpath("//*[@id=\"level-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe5, KetquaMongDoi5);

			driver.findElement(By.xpath("//*[@id=\"btnClose\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblAcademicDegree\"]/tbody/tr[1]/td[5]/a[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"name\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"level\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"academicdegree-form\"]/div[4]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi6 = ten_ham;
			String KetquaThucTe6 = driver.findElement(By.xpath("//*[@id=\"name-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe6, KetquaMongDoi6);
			String KetquaMongDoi7 = thutu;
			String KetquaThucTe7 = driver.findElement(By.xpath("//*[@id=\"level-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe7, KetquaMongDoi7);

			driver.findElement(By.xpath("//*[@id=\"level\"]")).sendKeys("-1");
			Thread.sleep(2000);

			String KetquaMongDoi8 = thutu1;
			String KetquaThucTe8 = driver.findElement(By.xpath("//*[@id=\"level-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe8, KetquaMongDoi8);
			workbook.close();
		}
	}

	@Test(priority = 2) // thông báo lỗi cấp bậc
	public void capbac() throws InterruptedException, AWTException, IOException {
		FileInputStream fis = new FileInputStream("C:\\them_sua_hk.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			String hocham = row.getCell(0).getStringCellValue();
			String capbac = row.getCell(1).getStringCellValue();
			String capbac1 = row.getCell(2).getStringCellValue();

			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/AcademicDegreeRank");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"tblAcademicDegreeRank_wrapper\"]/div[1]/div[2]/div/div[2]/button"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"academicdegreerank-form\"]/div[3]/button[2]")).click();
			Thread.sleep(2000);

			String KetquaMongDoi = hocham;
			String KetquaThucTe = driver.findElement(By.xpath("//*[@id=\"academic_degree_id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe, KetquaMongDoi);

			String KetquaMongDoi1 = capbac;
			String KetquaThucTe1 = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe1, KetquaMongDoi1);

			driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys(".");
			Thread.sleep(2000);
			String KetquaMongDoi2 = capbac1;
			String KetquaThucTe2 = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
			Assert.assertEquals(KetquaThucTe2, KetquaMongDoi2);
			workbook.close();
			// Kiểm tra xem có thông báo lỗi xuất hiện không
			boolean isErrorDisplayed = isErrorMessageDisplayed();
			if (isErrorDisplayed) {
				System.out.println("Không có thông báo lỗi.");
			} else {
				System.out.println("Đã xuất hiện thông báo lỗi.");
			}
		}
	}

		// Phương thức kiểm tra xem thông báo lỗi có xuất hiện không
		private boolean isErrorMessageDisplayed() {
			// Thực hiện kiểm tra xem phần tử chứa thông báo lỗi có tồn tại không
			try {
				WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message']"));
				return errorMessage.isDisplayed();
			} catch (NoSuchElementException e) {
				return false; // Không tìm thấy phần tử chứa thông báo lỗi
			}
		}

	}
