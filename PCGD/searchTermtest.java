package Hockivanguoidung;

import org.testng.annotations.Test;

import PCGD.ReadInput;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class searchTermtest {
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
	 driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Term");
	 }	 
}
	 @Test
	    public void searchTerm() {
	        // Mở trang web
	        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Term");

	        // Nhập từ khóa tìm kiếm
	        WebElement searchBox = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
	        searchBox.sendKeys("809");

	        // Chờ kết quả xuất hiện
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Kiểm tra kết quả tìm kiếm
	        WebElement resultMessage = driver.findElement(By.xpath("//div[@id='tblTerm_wrapper']"));
	        System.out.println("Kết quả tìm kiếm: " + resultMessage.getText());
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}
