package PCGD;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ZoomTest {
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
		 System.setProperty("webdriver.chrome.driver","C:\\\\Program Files\\\\Google\\\\Chrome"); 
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
		 
		 //Kiem tra xem cookies co ton tai khong
		 if (cookies != null && !cookies.isEmpty()) {
		 //Tach cookies thanh cac cap key-value
		 String[] cookiePairs = cookies.split(";");
		 //xoa [] trong chuoi
		 cookiePairs[0] = cookiePairs[0].substring(1);
		 cookiePairs[cookiePairs.length-1] = cookiePairs[cookiePairs.length-1].substring(0, cookiePairs[cookiePairs.length-1].length()-1);
		 
		 //Thiet lap cookies cho trinh duyet
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
}

    @Test(priority = 0)
    public void zoomIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/")); // Thay đổi URL tương ứng

        System.out.println("About to zoom in");
        for (int i = 0; i < 5; i++) {
            Dimension windowSize = driver.manage().window().getSize();
            int newWidth = (int) (windowSize.getWidth() * 1.1);
            int newHeight = (int) (windowSize.getHeight() * 1.1);
            driver.manage().window().setSize(new Dimension(newWidth, newHeight));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("About to zoom out");
        for (int i = 0; i < 3; i++) {
            Dimension windowSize = driver.manage().window().getSize();
            int newWidth = (int) (windowSize.getWidth() * 0.9);
            int newHeight = (int) (windowSize.getHeight() * 0.9);
            driver.manage().window().setSize(new Dimension(newWidth, newHeight));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.quit();
    }
}
