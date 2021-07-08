package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(how=How.NAME,using="UserName")
	WebElement txt_UserName;
	
	@FindBy(how=How.NAME,using="Password")
	WebElement txt_Password;
	
	@FindBy(how=How.NAME,using="Login")
	WebElement btn_Login;
	
	@FindBy(how=How.CSS,using="div[id='cssmenu']")
	WebElement elm_Logout;
	
	public void setUserName(String strUserName) {
		txt_UserName.sendKeys(strUserName);
	}
	public void setPassword(String strPassword) {
		txt_Password.sendKeys(strPassword);
	}
	public void Login() {
		btn_Login.submit();
	}
	public void LogOut() {
		elm_Logout.click();
		System.out.println("clicked logout");
	}
}
