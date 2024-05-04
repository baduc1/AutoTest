package PCGD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ReadInput {
	public static void main(String[] args) {
		String[] credentials = readCredentials();
		String username = credentials[0];
		String password = credentials[1];
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		
		//Ghi cookies vao tep cau hinh
		writeCookies("cookie_data");
	}
	//Read Cookie
	public static String readCookies() {
		try {
			Properties properties = new Properties();
			InputStream input = new FileInputStream("config.properties");
			properties.load(input);
			
			//Lay gia tri tu tep cau hinh
			String cookies = properties.getProperty("COOKIES");
			
			//Dong luong doc
			input.close();
			
			return cookies;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//Doc du lieu tu tep cau hinh
	public static String[] readCredentials() {
		try {
			Properties properties = new Properties();
			InputStream input = new FileInputStream("config.properties");
			properties.load(input);
			
			//Lay cac gia tri tu tep cau hinh
			String username = properties.getProperty("USERNAME");
			String password = properties.getProperty("PASSWORD");
			
			//Dong luong doc
			input.close();
			
			return new String[] { username, password};
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//Ghi Cookies vao tep cau hinh
	public static void writeCookies(String cookies) {
		try {
			//Doc du lieu tu tep cau hinh goc
			String[] credentials = readCredentials();
			String username = credentials[0];
			String password = credentials[1];
			
			//Tao mot doi tuong Properties cho tep cau hinh tam thoi
			Properties properties = new Properties();
			properties.setProperty("COOKIES",cookies);
			properties.setProperty("USERNAME",username);
			properties.setProperty("PASSWORD",password);
			
			//Ghi du lieu vao tep cau hinh tap thoi
			FileOutputStream output = new FileOutputStream("temp_config.properties");
			properties.store(output, null);
			output.close();
			
			//Sao chep noi dung tu tep cau hinh tam thoi vao tep cau hinh goc
			copyFile("temp_config.properties", "config.properties");
			
			//Xoa tep cau hinh tam thoi
			File tempFile = new File("temp_config.properties");
			tempFile.delete();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//Sao chep noi dung tu mot tep sang tep khac
	public static void copyFile(String sourceFileName, String destFileName) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourceFileName);
		FileOutputStream outputStream = new FileOutputStream(destFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
}