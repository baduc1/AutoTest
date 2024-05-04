package PCGD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CompareTitle {
    public static void main(String[] args) {
        // Thiết lập đường dẫn cho WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
        
        // Mở trang web
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        
        // So sánh tiêu đề của trang web với giá trị mong muốn
        String expectedTitle = "Tiêu đề mong muốn"; // Thay đổi thành tiêu đề mong muốn
        String actualTitle = driver.getTitle();
        
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Tiêu đề của trang web khớp với giá trị mong muốn: " + expectedTitle);
        } else {
            System.out.println("Tiêu đề của trang web không khớp với giá trị mong muốn.");
            System.out.println("Tiêu đề hiện tại: " + actualTitle);
        }
        
        // Đóng trình duyệt
        driver.quit();
    }
}
