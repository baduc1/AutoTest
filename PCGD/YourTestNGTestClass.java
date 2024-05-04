package PCGD;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YourTestNGTestClass {
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
    @Test
    public void testUserOperations() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream("C:\\them_sua_ngdung.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Bắt đầu từ dòng thứ 2 (dòng đầu tiên là tiêu đề)
        for (int i = 1; i <= 3; i++) {
            XSSFRow row = sheet.getRow(i);
            String magv = row.getCell(0).getStringCellValue();
            String tengv = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String loaigv = row.getCell(3).getStringCellValue();
            String role = row.getCell(4).getStringCellValue();
            String email1 = row.getCell(5).getStringCellValue();

            // Thêm người dùng
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
            Thread.sleep(2000);
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"tblUser_wrapper\"]/div[1]/div[2]/div/div[2]/button"));
            addButton.click();
            Thread.sleep(2000);
            WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]"));
            saveButton.click();
            Thread.sleep(2000);

            // Kiểm tra thông báo lỗi
            checkErrorMessage("staff_id", magv);
            checkErrorMessage("full_name", tengv);
            checkErrorMessage("type", loaigv);
            checkErrorMessage("role_id", role);

            // Làm thêm các thao tác khác nếu cần

            // Đóng thông báo lỗi sau khi đã kiểm tra
            WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"errorModal\"]/div/div/div[3]/button"));
            closeButton.click();
            Thread.sleep(2000);
        }
    }

    private void checkErrorMessage(String fieldId, String expectedErrorMessage) {
        String xpath = "//*[@id=\"" + fieldId + "-error\"]";
        WebElement errorElement = driver.findElement(By.xpath(xpath));
        String actualErrorMessage = errorElement.getText();

        System.out.println("Field: " + fieldId);
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        System.out.println("Expected: " + expectedErrorMessage);
        System.out.println("Actual: " + actualErrorMessage);
    }

    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau khi kiểm thử hoàn thành
        driver.quit();
    }
}
