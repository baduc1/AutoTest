package PCGD;

import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class XemTKB {
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
            Thread.sleep(3000);
            // Assert.assertEquals(driver.getCurrentUrl(),
            // "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
            // driver.quit();
        }
    }
    @Test(priority = 1)
    public void TC01_XemTKB() throws InterruptedException {
        driver.findElement(By.linkText("Thời khoá biểu")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Xem TKB")).click();
        Thread.sleep(3000);
        // Tìm và nhấn vào nút hoặc phần tử chứa dropdown
        WebElement dropdown = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
        dropdown.click();
        Thread.sleep(3000);
        // Chọn học kì 999 từ dropdown
        WebElement term999 = driver.findElement(By.xpath("//li[text()='999']"));
        term999.click();
        Thread.sleep(3000);
        // Tìm và nhấn vào dropdown để mở danh sách
        WebElement dropdown1 = driver.findElement(By.xpath("//span[@id='select2-week-container']"));
        dropdown1.click();
        Thread.sleep(3000);
        // Tìm và chọn tuần "Tuần 13" từ danh sách
        WebElement week13 = driver.findElement(By.xpath("//li[text()='Tuần 13']"));
        week13.click();
        Thread.sleep(3000);
        // Tìm và nhấn vào dropdown để mở danh sách
        WebElement dropdown2 = driver.findElement(By.xpath("//span[@id='select2-lecturer-container']"));
        dropdown2.click();
        // Tìm và chọn giảng viên "Luu Ba Duc" từ danh sách
        WebElement lecturerLuuBaDuc = driver.findElement(By.xpath("//li[text()='Luu Ba Duc']"));
        lecturerLuuBaDuc.click();
        
        // In kết quả
        System.out.println("Kết quả của TC1" );
        WebElement element1 = driver.findElement(By.xpath(
            "/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/table[1]/tbody[1]/tr[4]/td[2]/div[1]"
        ));
        String text1 = element1.getText();
        System.out.println("Lịch trong tuần của giảng viên này là " );
        System.out.println( text1);
        
        WebElement element2 = driver.findElement(By.xpath(
            "/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/table[1]/tbody[1]/tr[3]/td[3]/div[1]"
        ));
        String text2 = element2.getText();
        System.out.println(text2);
    }
    @Test(priority = 2)
    public void TC02_XemTKB() throws InterruptedException {
        driver.findElement(By.linkText("Thời khoá biểu")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Xem TKB")).click();
        Thread.sleep(3000);
        // Tìm và nhấn vào nút hoặc phần tử chứa dropdown
        WebElement dropdown = driver.findElement(By.xpath("//span[@id='select2-term-container']"));
        dropdown.click();
        Thread.sleep(3000);
        // Chọn học kì 999 từ dropdown
        WebElement term999 = driver.findElement(By.xpath("//li[text()='999']"));
        term999.click();
        Thread.sleep(3000);
        // Tìm và nhấn vào dropdown để mở danh sách
        WebElement dropdown2 = driver.findElement(By.xpath("//span[@id='select2-lecturer-container']"));
        dropdown2.click();
        // Tìm và chọn giảng viên từ danh sách
        WebElement lecturer = driver.findElement(By.xpath("//li[text()='titi']"));
        lecturer.click();
        Thread.sleep(3000);
        // In kết quả
        WebElement element1 = driver.findElement(By.xpath(
            "/html[1]/body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/h4[1]"
        ));
        String text1 = element1.getText();
        System.out.println("Kết quả của TC2" );
        System.out.println( text1);
    }
    @AfterClass
    public void afterClass() {
    	driver.quit();
    	}
}
