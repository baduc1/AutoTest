package ngành;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PCGD.ReadInput;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Addnganh {
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
            // Lam moi trang de ap dung cookie
            driver.navigate().refresh();
            WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofHours(10));
            driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        }

        driver.findElement(By.linkText("Học kỳ và ngành")).click();
        driver.findElement(By.linkText("Ngành")).click();

    }

    @Test(priority = 0)
    public void testAddMajor() throws InterruptedException, IOException {
        // Đọc dữ liệu từ file Excel
    	// Load file Excel
    	// Load file Excel
        FileInputStream fis = new FileInputStream("C:\\nganh.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Lấy sheet đầu tiên từ workbook
        

        // Bỏ qua dòng tiêu đề
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        // Duyệt qua từng dòng dữ liệu trong sheet
        for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);

            // Lấy dữ liệu từ các cột của dòng hiện tại
            String maNganh = row.getCell(0).getStringCellValue();
            String tenNganh = row.getCell(1).getStringCellValue();
            String tenVietTat = row.getCell(2).getStringCellValue();

            // Nhấn nút Thêm mới
            WebElement addNewButton = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"));
            addNewButton.click();

            // Nhập thông tin của ngành
            WebElement maNganhInput = driver.findElement(By.xpath("//input[@id='id']"));
            maNganhInput.sendKeys(maNganh);

            WebElement tenNganhInput = driver.findElement(By.xpath("//input[@id='name']"));
            tenNganhInput.sendKeys(tenNganh);

            WebElement tenVietTatInput = driver.findElement(By.xpath("//input[@id='abbreviation']"));
            tenVietTatInput.sendKeys(tenVietTat);

            // Chọn CTĐT
            WebElement ctdtDropdown = driver.findElement(By.xpath("//span[@id='select2-program_type-container']"));
            ctdtDropdown.click();
            WebElement ctdtOption = driver.findElement(By.xpath("//li[text()='Tiêu chuẩn']"));
            ctdtOption.click();
            Thread.sleep(1000);

            // Nhấn nút Lưu
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
            saveButton.click();

            // Chờ thông báo thành công hoặc xác nhận rằng ngành đã được thêm thành công
            // Có thể sử dụng các phương thức kiểm tra khác như assert để đảm bảo rằng ngành đã được thêm thành công
        }

        // Đóng workbook và input stream
        workbook.close();
        
    }

    @Test(priority = 1)
    public void testUpdateMajor() throws InterruptedException, IOException {
        // Đọc dữ liệu từ file Excel
    	// Load file Excel
        FileInputStream fis = new FileInputStream("C:\\nganh.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("update");

        // Bỏ qua dòng tiêu đề
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        

        // Duyệt qua từng dòng dữ liệu trong sheet
        for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
        	
            Row row = sheet.getRow(i);

            // Lấy dữ liệu từ các cột của dòng hiện tại
            String maNganh = row.getCell(0).getStringCellValue();
            String tenNganh = row.getCell(1).getStringCellValue();
            String tenVietTat = row.getCell(2).getStringCellValue();

            // Click vào núttìm kiếmcủa ngành cần cập nhật
            WebElement searchButton = driver.findElement(By.xpath("//label[contains(text(),'Tìm kiếm')]/input"));
            searchButton.sendKeys(maNganh);
            searchButton.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchButton.clear();
            searchButton.sendKeys(maNganh);
            searchButton.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchButton.clear();
            searchButton.sendKeys(maNganh);
            searchButton.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchButton.clear();
            

            // Click vào nút Sửa của ngành cần cập nhật
            WebElement editButton = driver.findElement(By.className("feather-edit"));
            editButton.click();

            WebElement tenNganhInput = driver.findElement(By.xpath("//input[@id='name']"));
            tenNganhInput.clear();
            tenNganhInput.sendKeys(tenNganh);

            WebElement tenVietTatInput = driver.findElement(By.xpath("//input[@id='abbreviation']"));
            tenVietTatInput.clear();
            tenVietTatInput.sendKeys(tenVietTat);

            // Chọn loại CTĐT
            WebElement ctdtDropdown = driver.findElement(By.xpath("//span[@id='select2-program_type-container']"));
            ctdtDropdown.click();
            WebElement ctdtOption = driver.findElement(By.xpath("//li[text()='Đặc biệt']"));
            ctdtOption.click();
            Thread.sleep(1000);

            // Nhấn nút Lưu để cập nhật ngành
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
            saveButton.click();
        }

        // Đóng workbook và input stream
        workbook.close();
        
    }

    @Test(priority = 2)
    public void testDeleteMajor() throws InterruptedException, IOException {
    	// Đọc dữ liệu từ file Excel
    	// Load file Excel
        FileInputStream fis = new FileInputStream("C:\\nganh.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("update");

        // Bỏ qua dòng tiêu đề
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        // Duyệt qua từng dòng dữ liệu trong sheet
        for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);

            // Lấy dữ liệu từ cột đầu tiên của dòng hiện tại
            String maNganh = row.getCell(0).getStringCellValue();

            // Nhập thông tin của ngành cần xóa vào ô tìm kiếm
            WebElement searchInput = driver.findElement(By.xpath("//label[contains(text(),'Tìm kiếm')]/input"));
            searchInput.clear();
            searchInput.sendKeys(maNganh);
            searchInput.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchInput.clear();
            searchInput.sendKeys(maNganh);
            searchInput.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchInput.clear();
            searchInput.sendKeys(maNganh);
            searchInput.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            searchInput.clear();
            // Click vào nút "Xóa" của ngành cần xóa
            WebElement deleteButton = driver.findElement(By.className("feather-trash-2"));
            deleteButton.click();
            Thread.sleep(1000);
            WebElement confirmdeleteButton = driver.findElement(By.xpath("//button[contains(text(),'Xoá')]"));
            confirmdeleteButton.click();
        }

        // Đóng workbook và input stream
        workbook.close();
       
    }
    @AfterClass
    public void afterClass() {
        
    }
  }

