package PCGD;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
public class updateuser {
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
	            cookiePairs[cookiePairs.length - 1] = cookiePairs[cookiePairs.length - 1]
	                    .substring(0, cookiePairs[cookiePairs.length - 1].length() - 1);
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
	            
	            driver.findElement(By.linkText("Người dùng")).click();
	            
}
  
  
  @Test(priority = 0)
  public void updateUser() throws InterruptedException {
      WebElement searchInput = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
      searchInput.sendKeys("123456");
      searchInput.sendKeys(Keys.ENTER);

      WebElement editButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[7]/a[1]/i[1]"));
      editButton.click();

      WebElement nameInput = driver.findElement(By.xpath("//input[@id='full_name']"));
      nameInput.clear();
      nameInput.sendKeys("Nguyễn Văn A");

      WebElement emailInput = driver.findElement(By.xpath("//input[@id='email']"));
      emailInput.clear();
      emailInput.sendKeys("minh.2273201080873@vanlanguni.vn");

      // Tìm và kích hoạt hộp chọn
      WebElement selectBox = driver.findElement(By.xpath("//span[@id='select2-type-container']"));
      selectBox.click();

      // Chọn giá trị từ danh sách xuống
      WebElement option = driver.findElement(By.xpath("//li[text()='Cơ hữu']")); // Thay 'Giá trị muốn chọn' bằng giá trị thực sự bạn muốn chọn
      option.click();


 	 	
 	 	
      
   // Tìm và nhấp vào hộp chọn vai trò (role)
      WebElement roleSelect = driver.findElement(By.xpath("//span[@id='select2-role_id-container']"));
      roleSelect.click();

      // Đợi cho danh sách các giá trị được hiển thị
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofHours(10));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-results__option")));

      // Chọn một giá trị cụ thể từ danh sách
      WebElement roleOption = driver.findElement(By.xpath("//li[text()='BCN khoa']"));
      roleOption.click();
      WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
      saveButton.click();
  }

  @AfterClass
  public void afterClass() {
      driver.quit();
  }
}
