package PCGD;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AuthenticationTest {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	String osName = System.getProperty("os.name");

	ReadInput read = new ReadInput();

	String[] credentials = ReadInput.readCredentials();

	String username = credentials[0];

	String password = credentials[1];

	@BeforeClass
	public void beforeClass() throws InterruptedException {

		if (osName.contains("Windows")) {

			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		} else {

			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome");

		}

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

		WebElement acceptButton = driver.findElement(By.id("details-button"));
		acceptButton.click();
		Thread.sleep(3000);
		WebElement acceptLink = driver.findElement(By.id("proceed-link"));
		acceptLink.click();
		WebElement submitButton = driver.findElement(By.id("OpenIdConnect"));
		submitButton.click();
		Thread.sleep(3000);

		// input email
		driver.findElement(By.id("i0116")).sendKeys(username);
		// click next
		WebElement nextButton = driver.findElement(By.id("idSIButton9"));
		nextButton.click();
		Thread.sleep(5000);
		// input password
		driver.findElement(By.id("i0118")).sendKeys(password);
		// click submit
		WebElement submitButton1 = driver.findElement(By.id("idSIButton9"));
		submitButton1.click();
		Thread.sleep(8000);
		// enter
		WebElement enterButton = driver.findElement(By.xpath(
				"/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input"));
		enterButton.click();
		Thread.sleep(8000);
	}

	@Test
	public void TC_01_geturl() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
	}

	@Test
	public void TC_02_getcookies() {
		// get cookies
		String cookies = driver.manage().getCookies().toString();
		System.out.println(cookies);
		// save cookies to file config.properties
		ReadInput.writeCookies(cookies);
	}

	// logout
	@Test
  public void TC_03_logout() {
	  driver.findElement(By.id("dropdown-user")).click();
	  driver.findElement(By.xpath("/html/body/div[2]/nav/div/ul/li[2]/div/a[2]")).click();/-strong/-heart:>:o:-((:-h//WebDriverWait wait = new WebDriverWait(driver, 10);
	  driver.quit();
  }

	@Test
	public void TC_04_usecookies() throws InterruptedException {
		WebDriver driver = new ChromeDriver(); // khoi tao trinh duyet moi
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
			// WebDriverWait wait = new WebDriverWait(driver, 10);
			driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
			Thread.sleep(3000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
			driver.quit();
		}

	}

	@AfterClass
	public void afterClass() {
	}
}
