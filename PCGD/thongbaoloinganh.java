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
	
	public class thongbaoloinganh {
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
			driver.findElement(By.linkText("Học kỳ và ngành")).click();
		}
	
		@Test
		public void nganh() throws InterruptedException, AWTException, IOException {
			FileInputStream fis = new FileInputStream("C:\\them_sua_nganh.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = sheet.getRow(i);
	
				String ma_nganh = row.getCell(0).getStringCellValue();
				String ten_nganh = row.getCell(1).getStringCellValue();
				String ten_tat = row.getCell(2).getStringCellValue();
				String ctdt = row.getCell(3).getStringCellValue();
				
				// Them ngành (Thông báo lỗi)
				driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Major");
				driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a"))
						.click();
	
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"tblMajor_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys(ma_nganh);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(ten_nganh);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"abbreviation\"]")).sendKeys(ten_tat);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"major-form\"]/div[5]/button[2]")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id=\"major-form\"]/div[5]/button[2]")).click();
				Thread.sleep(2000);
	
				String KetquaMongDoi17 = ma_nganh;
				String KetquaThucTe17 = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe17, KetquaMongDoi17,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
	
				String KetquaMongDoi18 = ten_nganh;
				String KetquaThucTe18 = driver.findElement(By.xpath("//*[@id=\"name-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe18, KetquaMongDoi18,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
	
				String KetquaMongDoi19 = ten_tat;
				String KetquaThucTe19 = driver.findElement(By.xpath("//*[@id=\"abbreviation-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe19, KetquaMongDoi19,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
	
				String KetquaMongDoi20 = ctdt;
				String KetquaThucTe20 = driver.findElement(By.xpath("//*[@id=\"program_type-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe20, KetquaMongDoi20,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
	
				
						
	
				driver.findElement(By.xpath("//*[@id=\"btnClose\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"tblMajor\"]/tbody/tr[1]/td[6]/a[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"name\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("value");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"abbreviation\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"abbreviation\"]")).sendKeys("value");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"major-form\"]/div[5]/button[2]")).click();
				Thread.sleep(2000);
	
				String KetquaMongDoi22 = ten_nganh;
				String KetquaThucTe22 = driver.findElement(By.xpath("//*[@id=\"name-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe22, KetquaMongDoi22,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
	
				String KetquaMongDoi23 = ten_tat;
				String KetquaThucTe23 = driver.findElement(By.xpath("//*[@id=\"abbreviation-error\"]")).getText();
				Assert.assertEquals(KetquaThucTe23, KetquaMongDoi23,
						"Fail: Kết quả mong đợi và kết quả thực tế không khớp");
			}
		}
	}
