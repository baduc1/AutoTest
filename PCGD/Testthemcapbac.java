package PCGD;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Testthemcapbac {
    private WebDriverWait wait;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
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
          //Lam moi trang de ap dung cookie
            driver.navigate().refresh();
            WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofHours(10));
            driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
            }
        	driver.findElement(By.linkText("Thù lao")).click();
            
            driver.findElement(By.linkText("Học hàm, học vị")).click();
            driver.findElement(By.linkText("Cấp bậc")).click();
            
            }

    @Test(priority = 0)
    public void capbac() throws InterruptedException, IOException {
    	 // Open Excel file
    	FileInputStream fis = new FileInputStream("C:\\capbac.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String macapbac = sheet.getRow(1).getCell(0).getStringCellValue();
            
            // Tìm và nhấn nút "Thêm"
            WebElement addButton = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]"));
            addButton.click();
           
            // Tìm phần tử ô học hàm/học vị và click vào nó để mở danh sách dropdown
            WebElement academicDegreeDropdown = driver.findElement(By.id("select2-academic_degree_id-container"));
            academicDegreeDropdown.click();
            
    
            
            // Tìm và click vào mục mong muốn trong danh sách dropdown
            WebElement hocham = driver.findElement(By.xpath("//li[contains(text(),'Giáo sư')]"));
            hocham.click();
            
            // Thực hiện thêm cấp bậc trên trang web với dữ liệu từ Excel
            driver.findElement(By.xpath("//input[@id='id']")).sendKeys(macapbac);
            
            WebElement luuButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
            luuButton.click();
           
        }
    @Test(priority = 1)
    public void updateAcademicRank() throws InterruptedException, IOException {
        // Mở tệp Excel
    	FileInputStream fis = new FileInputStream("C:\\capbac.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String macapbac = sheet.getRow(1).getCell(0).getStringCellValue();
        WebElement searchInput = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
        searchInput.sendKeys(macapbac); // Sử dụng mã học hàm từ file Excel
        searchInput.sendKeys(Keys.ENTER);

            // Tìm và nhấn nút "Sửa" tương ứng với cấp bậc
        	driver.findElement(By.xpath("//tbody/tr[2]/td[2]/a[1]/i[1]")).click(); 
        		

            // Tìm phần tử ô học hàm/học vị và click vào nó để mở danh sách dropdown
            WebElement academicDegreeDropdown = driver.findElement(By.id("select2-academic_degree_id-container"));
            academicDegreeDropdown.click();
           
            // Tìm và click vào mục mong muốn trong danh sách dropdown
            WebElement hocham = driver.findElement(By.xpath("//li[contains(text(),'Trợ giảng')]"));
            hocham.click();

            // Nhấn nút "Lưu" để cập nhật
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
            saveButton.click();
         // Đóng tệp Excel sau khi sử dụng
            fis.close();
        }
    @Test(priority = 2)
    public void deleteAcademicRank() throws InterruptedException, IOException {
        // Mở tệp Excel
        FileInputStream fis = new FileInputStream("C:\\capbac.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String macapbac = sheet.getRow(1).getCell(0).getStringCellValue();
        WebElement searchInput = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
        
        searchInput.sendKeys(Keys.ENTER);

        // Tìm và nhấn nút "Xóa" tương ứng với cấp bậc
        WebElement deleteButton = driver.findElement(By.xpath("//tbody/tr[2]/td[2]/a[1]/i[1]"));
        deleteButton.click();

        // Xác nhận xóa
        WebElement confirmDeleteButton = driver.findElement(By.xpath("//button[contains(text(),'Xoá')]"));
        confirmDeleteButton.click();

        // Đóng tệp Excel sau khi sử dụng
        fis.close();
    }

    @AfterClass
    public void tearDown() throws IOException {
        
    }
}
