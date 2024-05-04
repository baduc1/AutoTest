package Hockivanguoidung;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PCGD.ReadInput;

public class AddTerm {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	ReadInput read = new ReadInput();
	String[] credentials = ReadInput.readCredentials();
	String username = credentials[0];
	String password = credentials[1];
	String[] addTermValue = { "756", "2022", "2025", "2", "4", "4" };

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
		}

		driver.findElement(By.linkText("Học kỳ và ngành")).click();

	}

	@Test(priority = 0)
	public void sendValue() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"tblTerm_wrapper\"]/div[1]/div[2]/div/div[2]/button")).click();

		for (int i = 0; i < addTermValue.length; i += 6) {
			WebElement term = driver.findElement(By.id("id"));
			term.sendKeys(addTermValue[i]);
			term.sendKeys(Keys.TAB);

			WebElement startYearElement = driver.findElement(By.xpath("//*[@id=\"start_year\"]"));
			Select startYear = new Select(startYearElement);
			startYear.selectByValue(addTermValue[i + 1]);

			WebElement endYearElement = driver.findElement(By.xpath("//*[@id=\"end_year\"]"));
			Select endYear = new Select(endYearElement);
			endYear.selectByValue(addTermValue[i + 2]);

			WebElement startWeek = driver.findElement(By.xpath("//*[@id=\"start_week\"]"));
			startWeek.clear();
			startWeek.sendKeys(addTermValue[i + 3]);

			// Handling date selection if needed
			WebElement selectStartDay = driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[5]/input[2]"));
			selectStartDay.click();
			WebElement monthElement = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/select"));
			monthElement.click();
			Select daypickMonth = new Select(monthElement);
			daypickMonth.selectByVisibleText("Tháng một");

			WebElement daypickYear = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div/input"));
			daypickYear.clear();
			daypickYear.click();
			daypickYear.sendKeys(addTermValue[1]);
			Thread.sleep(2000);

			driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[14]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement maxLesson = driver.findElement(By.xpath("//*[@id=\"max_lesson\"]"));
			maxLesson.clear();
			maxLesson.sendKeys(addTermValue[i + 4]);

			WebElement maxClass = driver.findElement(By.xpath("//*[@id=\"max_class\"]"));
			maxClass.clear();
			maxClass.sendKeys(addTermValue[i + 5]);

			driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[7]/button[2]")).click();
		}
	}

	@Test(priority = 1)
	public void EditTerm() throws InterruptedException {
		WebElement findTermElement = driver.findElement(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input"));
		findTermElement.sendKeys("756");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"tblTerm\"]/tbody/tr/td[9]/a[1]")).click();
		String[] editValue = { "2023", "2026", "3", "6", "6" };
		for (int i = 0; i < editValue.length; i += 6) {

			WebElement startYearElement = driver.findElement(By.xpath("//*[@id=\"start_year\"]"));
			Select startYear = new Select(startYearElement);
			startYear.selectByValue(editValue[i]);

			WebElement endYearElement = driver.findElement(By.xpath("//*[@id=\"end_year\"]"));
			Select endYear = new Select(endYearElement);
			endYear.selectByValue(editValue[i + 1]);

			WebElement startWeek = driver.findElement(By.xpath("//*[@id=\"start_week\"]"));
			startWeek.clear();
			startWeek.sendKeys(editValue[i + 2]);

			WebElement selectStartDay = driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[5]/input[2]"));
			selectStartDay.click();
			WebElement monthElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/select"));
			monthElement.click();
			Select daypickMonth = new Select(monthElement);
			daypickMonth.selectByVisibleText("Tháng một");
			WebElement daypickYear = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div/div/input"));
			daypickYear.clear();
			daypickYear.click();
			daypickYear.sendKeys(editValue[0]);
			Thread.sleep(1000);

			driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/span[11]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			WebElement maxLesson = driver.findElement(By.xpath("//*[@id=\"max_lesson\"]"));
			maxLesson.clear();
			maxLesson.sendKeys(editValue[i + 3]);

			WebElement maxClass = driver.findElement(By.xpath("//*[@id=\"max_class\"]"));
			maxClass.clear();
			maxClass.sendKeys(editValue[i + 4]);

			driver.findElement(By.xpath("//*[@id=\"term-form\"]/div[7]/button[2]")).click();
		}
	}

	@Test(priority = 2)
	public void lockTerm() throws InterruptedException {
		WebElement findTermElement = driver.findElement(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input"));
		findTermElement.clear();
		findTermElement.sendKeys(addTermValue[0]);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"tblTerm\"]/tbody/tr/td[8]/div/input")).click();
		Thread.sleep(1000);
	}

	@Test(priority = 3)
	public void deleteTerm() throws InterruptedException {
		WebElement findTermElement = driver.findElement(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input"));
		findTermElement.clear();
		findTermElement.sendKeys(addTermValue[0]);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"tblTerm\"]/tbody/tr/td[9]/a[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("swal2-confirm")).click();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
	}

}