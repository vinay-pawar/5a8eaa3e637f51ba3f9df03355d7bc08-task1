package step_defs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import repos.BotCreationRepo;

public class CreateBotSteps {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	org.apache.log4j.Logger logger;
	String host;
	
	@Given("{string} login page is already open in browser and steps will get logged in {string}")
	public void login_page_is_already_open_in_browser(String host, String logFile) {
		System.setProperty("file.name",logFile);
	    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		logger = Logger.getLogger(CreateBotSteps.class);		
		logger.info("\n==============================Starting Test Create Gmail Bot using Selenium==============================");
	    driver = new ChromeDriver();
	    wait= new WebDriverWait(driver, 15);
	    action = new Actions(driver);
	    this.host=host;
	    driver.manage().window().maximize();
	    logger.info("Google Chrome browser launched.");
	    driver.get(host+BotCreationRepo.LOGIN_URI);
	}

	@When("user enter {string} and {string} to loging successfully")
	public void user_enter_and_to_loging_successfully(String toEmail, String password) {
		logger.info("\n****************************************Login Process Initiated****************************************");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_LOGIN_TAB)));
		String actual_login_tab_text = driver.findElement(By.xpath(BotCreationRepo.PATH_LOGIN_TAB)).getText();
		Assert.assertEquals("Login Tab Text Mismatch: Expected :"+BotCreationRepo.LOGIN_TAB_TEXT+"\nActual :"+ actual_login_tab_text,actual_login_tab_text,BotCreationRepo.LOGIN_TAB_TEXT);
		logger.info("Step 1: Login Tab on Login Page clicked.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(BotCreationRepo.ID_OF_EMAIL_INPUT))).sendKeys(toEmail);
	    logger.info("Step 2: Entered Login Email : "+toEmail+".");
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(BotCreationRepo.ID_OF_PASSWORD_INPUT))).sendKeys(password);
		logger.info("Step 3: Entered Login Password.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_OF_LOGIN_BUTTON))).click();
		logger.info("Step 4: Clicked on Login Button.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_CREATE_BOT_DIV)));
		Assert.assertEquals("User does not redirected to Dashboard Page. current page url: "+driver.getCurrentUrl(),BotCreationRepo.DASHBOARD_URL , driver.getCurrentUrl());
		logger.info("Step 5: Verificaton Login Successful, redirected to Dashboard Page successfully.");
		logger.info("\n****************************************Login Process Ended****************************************");
	}

	@When("create bot to forward email from {string} to {string}")
	public void create_bot_to_forward_email(String fromEmail, String toEmail) throws InterruptedException {
		logger.info("\n****************************************Bot Creation Process Initiated****************************************");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_CREATE_A_BOT_BUTTON))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_NEW_BOT_NAME_DIV)));
		Assert.assertEquals(BotCreationRepo.URL_BOT_CREATION_PAGE, driver.getCurrentUrl());
		logger.info("Step 1: Create a Bot button on Dashboard Page clicked.");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BotCreationRepo.PATH_EDIT_BOT_NAME))).click();
		Thread.sleep(5000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(BotCreationRepo.PATH_EDIT_BOT_NAME))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(BotCreationRepo.ID_EDIT_BOT_NAME_TEXTBOX))).clear();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(BotCreationRepo.ID_EDIT_BOT_NAME_TEXTBOX))).sendKeys("My Gmail Bot");
		Thread.sleep(2000);
		logger.info("Step 2: Bot name changed from Untitled Bot to My Gmail Bot");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BotCreationRepo.PATH_SELECT_TRIGGERAPP_BUTTON))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_TRIGGER_APP_SEARCH_BOX))).sendKeys("Gmail");
		Thread.sleep(2000);
		logger.info("Step 3: Searched for Gmail App in trigger app search box on  create bot page.");
		try{
			String actual_email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GMAIL_APP_CONTAINER_HELP))).getText();
			Assert.assertEquals(fromEmail, actual_email);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GMAIL_APP_CONTAINER+"/div/img"))).click();
			logger.info("Step 4: Verify Gmail App is Authenticated with "+fromEmail+" gmail account.");
		}
		catch(Exception e){
			logger.error("Step 4: Gmail App not Authenticated. throws following exception:\n"+e.getMessage());
			try{
				logout(host);
				logger.info("\n==============================Exiting Test Create Gmail Bot using Selenium==============================");
				System.exit(0);
			}
			catch(InterruptedException ex){
				logger.error("System Exiting due to error: "+ex.getMessage());
				logger.info("\n==============================Exiting Test Create Gmail Bot using Selenium==============================");
				System.exit(0);
			}
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_TRIGGER_EVENT_SEARCH))).sendKeys("New Email");
		logger.info("Step 5: Select triggger event as New Email");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_NEW_EMAIL_DROPDOWN_ELEMENT))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_FOLDER_LABEL_DROPDOWN))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_INBOX_DROPDOWN_ITEM)));
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_INBOX_DROPDOWN_ITEM))).click().build().perform();		
		logger.info("Step 6: Select Inbox folder to look for new email.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_SELECT_ACTIONAPP_BUTTON))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_ACTION_APP_SEARCH_BOX))).sendKeys("Gmail");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GMAIL_APP_CONTAINER_IMG_ACTION))).click();
		logger.info("Step 7: Searched for Gmail App in action app search box on  create bot page.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_ACTION_SEARCH_BOX))).sendKeys("Send an Email");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_SEND_AN_EMAIL_DROPDOWN_ELEMENT))).click();
		logger.info("Step 8: Select Send an Email as Action to be done.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON.replace("?", "From Name")))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "From Name").replace("#", "From Name"))));
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "From Name").replace("#", "From Name")))).click().build().perform();		
		logger.info("Step 9: Select From Name as From Name");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON.replace("?", "From Address")))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "From Address").replace("#", "From Email"))));
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "From Address").replace("#", "From Email")))).click().build().perform();		
		logger.info("Step 10: Select From Email as From Address");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_TO_ADDRESSES_TEXTFIELD))).sendKeys(toEmail);
		logger.info("Step 11: Set To Address field with value : "+ toEmail);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON.replace("?", "Subject")))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "Subject").replace("#", "Subject"))));
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "Subject").replace("#", "Subject")))).click().build().perform();		
		logger.info("Step 13: Select Subject as Subject");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON.replace("?", "Email Body")))).click();
	    action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON.replace("?", "Email Body")+"/i"))).sendKeys(Keys.ENTER).build().perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "Email Body").replace("#", "Body"))));
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_GENERIC_DROPDOWN_ELEMENT.replace("?", "Email Body").replace("#", "Body")))).click().build().perform();		
		logger.info("Step 14: Selected Body as Email Body");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_SAVE_BUTTON))).click();
		logger.info("Step 15: Save button Clicked");
		logger.info("\n****************************************Bot Creation Process Completed****************************************");
	}

	@When("enable the bot that is created")
	public void enable_the_bot_that_is_created() {
		logger.info("\n****************************************Bot Enabling Process Initiated****************************************");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_ON_OFF_BUTTON))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_I_M_DONE_BUTTON))).click();
		logger.info("\n****************************************Bot Enabling Process Completed****************************************");
	}

	@Then("user disable the bot")
	public void then_user_disable_the_bot() {
		logger.info("\n****************************************Bot Disabling Process Initiated****************************************");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_GO_TO_BOT_LIST))).click();
		logger.info("Step 1: Go to Bot List");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_TOGGLE_BOT_BUTTON))).click();
		action.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_ON_OFF_BUTTON)))
		.moveToElement(driver.findElement(By.xpath(BotCreationRepo.PATH_TRASH_BUTTON))).click().build().perform();
		logger.info("Step 2: Disable bot using toggle button.");
		logger.info("\n****************************************Bot Disabling Process Completed****************************************");
	}

	@Then("delete the bot before logout from {string}")
	public void delete_the_bot_before_logout_from(String string) {
		logger.info("\n****************************************Bot Deletion and Logout Process Initiated****************************************");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(BotCreationRepo.PATH_ALERT_DELETE_BUTTON)))).click();
		try{
			logout(host);
		}
		catch(InterruptedException ex){
			logger.error("System Exiting due to error: "+ex.getMessage());
			logger.info("\n==============================Exiting Test Create Gmail Bot using Selenium==============================");
			System.exit(0);
		}
		finally{
			logger.info("\n****************************************Bot Deletion and Logout Process Completed****************************************");
			logger.info("\n==============================Exiting Test Create Gmail Bot using Selenium==============================");
		}
	}

	public void logout(String host) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_USER_ICON))).click();
		logger.info("Step 1: Go to user icon.");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BotCreationRepo.PATH_LOGOUT_BUTTON))).click();
		Thread.sleep(3000);
		logger.info("Step 2: Click on Logout button.");
		Assert.assertEquals("Logout action not completed. User still on page : "+driver.getCurrentUrl(),host+BotCreationRepo.LOGIN_URI, driver.getCurrentUrl());
		Thread.sleep(3000);
		driver.quit();
		logger.info("Step 3: Close the Google Chrome Browser.");
	}
}
