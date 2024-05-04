package PCGD;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestHMHV {
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
            
            }

    @Test(priority = 0)
    public void addAcademicDegree() throws InterruptedException, IOException {
    	 // Load file Excel
        FileInputStream fis = new FileInputStream("C:\\hochamhocvi.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Lấy dữ liệu từ file Excel
        String maHocHam = sheet.getRow(1).getCell(0).getStringCellValue();
        String tenHocHam = sheet.getRow(1).getCell(1).getStringCellValue();

        // Tìm và nhấn nút "Thêm"
        WebElement addButton = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]"));
        addButton.click();

        // Nhập mã học hàm
        WebElement maHocHamElement = driver.findElement(By.xpath("//input[@id='id']"));
        maHocHamElement.sendKeys(maHocHam);

        // Nhập tên học hàm
        WebElement tenHocHamElement = driver.findElement(By.xpath("//input[@id='name']"));
        tenHocHamElement.sendKeys(tenHocHam);
 
        WebElement heSoLuong = driver.findElement(By.xpath("//input[@id='level']"));
        heSoLuong.sendKeys("1");

        WebElement luuButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
        luuButton.click();
    }
    @Test(priority = 1)
    public void updateAcademicDegree() throws InterruptedException, IOException {
        // Load file Excel
        FileInputStream fis = new FileInputStream("C:\\hochamhocvi.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("update"); // Sử dụng tên của sheet "update"

        // Lặp qua từng dòng trong sheet Excel (bắt đầu từ dòng 1 vì dòng đầu tiên thường là tiêu đề)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // Lấy Tên học hàm từ cột thứ 2 (index 1)
            String tenHocHam = sheet.getRow(i).getCell(1).getStringCellValue(); 

            // Tìm mã học hàm tương ứng từ cột thứ 1 (index 0)
            String ABC12333 = sheet.getRow(i).getCell(0).getStringCellValue(); 

            // Tìm phần tử tìm kiếm và nhập mã học hàm cần cập nhật
            WebElement searchInput = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
            searchInput.sendKeys(ABC12333); // Sử dụng mã học hàm từ file Excel
            searchInput.sendKeys(Keys.ENTER);

            // Nhấn nút "Sửa"
            driver.findElement(By.xpath("//td[contains(text(),'" + ABC12333 + "')]/following-sibling::td[3]/a[1]")).click(); // Sử dụng mã học hàm từ file Excel

            // Nhập thông tin mới cho Tên học hàm
            WebElement tenHocHamElement = driver.findElement(By.xpath("//input[@id='name']"));
            tenHocHamElement.clear();
            tenHocHamElement.sendKeys(tenHocHam);
            
            WebElement heSoLuong = driver.findElement(By.xpath("//input[@id='level']"));
            heSoLuong.sendKeys("1.2");

            // Nhấn nút "Lưu"
            WebElement luuButton = driver.findElement(By.xpath("//button[contains(text(),'Lưu')]"));
            luuButton.click();
        }
        // Đóng file Excel sau khi sử dụng xong
        fis.close();
    }

    
    
    @Test(priority = 2)
    public void deleteAcademicDegree() throws InterruptedException {
    	// Tìm phần tử tìm kiếm và nhập mã học hàm cần xóa
        WebElement searchInput = driver.findElement(By.xpath("//body/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/input[1]"));
        searchInput.sendKeys(Keys.ENTER);
        
        // Nhấn nút "Xóa"
        driver.findElement(By.xpath("//i[@class='feather feather-trash-2 font-medium-3 me-1']")).click();
        Thread.sleep(2000);
        
        // Xác nhận xóa
        driver.findElement(By.xpath("//button[contains(text(),'Xoá')]")).click();
    }

 
    


    
    @AfterClass
    public void afterClass() {
       
    }
}
