package repos;

public class BotCreationRepo {

	public static final String LOGIN_URI = "/app/login";
	public static final String PATH_LOGIN_TAB = "//a[text()='LOGIN']";
	public static final String LOGIN_TAB_TEXT = "LOGIN";
	public static final String ID_OF_EMAIL_INPUT = "email";
	public static final String ID_OF_PASSWORD_INPUT = "password";
	public static final String PATH_OF_LOGIN_BUTTON = "//button[@type='submit'][text()='LOGIN']";
	public static final String DASHBOARD_URL = "https://automate.io/app/dashboard";
	public static final String PATH_CREATE_BOT_DIV = "//*[@class='add-bot-link-wrapper']";
	public static final String PATH_CREATE_A_BOT_BUTTON = "//a[text()='Create a Bot']"; 
	public static final String PATH_NEW_BOT_NAME_DIV = "//div[@class='workflow-name']";
	public static final String PATH_EDIT_BOT_NAME = "//i[@title='Edit Workflow Name']";
	public static final String ID_EDIT_BOT_NAME_TEXTBOX = "workflowNameEditor";
	public static final String PATH_SELECT_TRIGGERAPP_BUTTON = "//div[@id='workflowsContainer']/div/aio-workflow-card[1]//aio-user-app-select/div/button";
	public static final String URL_BOT_CREATION_PAGE = "https://automate.io/app/bots/add";
	public static final String PATH_TRIGGER_APP_SEARCH_BOX = "//div[@id='workflowsContainer']/div/aio-workflow-card[1]//input[@placeholder='Search']";
	public static final String PATH_GMAIL_APP_CONTAINER = "//div[@id='workflowsContainer']/div/aio-workflow-card[1]//aio-user-app-select-figure[@title='Gmail']";
	public static final String PATH_GMAIL_APP_CONTAINER_HELP = PATH_GMAIL_APP_CONTAINER+"//div[@class='ellipsed help-block identifier']";
	public static final String PATH_TRIGGER_EVENT_SEARCH = "//aio-searchable-dropdown[@class='menu']/form/input";
	public static final String PATH_NEW_EMAIL_DROPDOWN_ELEMENT = "//li[contains(@class,'data-item link-like link-like-padding')]/div[@title='New Email']";
	public static final String PATH_FOLDER_LABEL_DROPDOWN = "//label[@class='label-name'][contains(text(),'Folder / Label')]/parent::aio-content-label/following-sibling::div//button";
	public static final String PATH_INBOX_DROPDOWN_ITEM = "//li[contains(@class,'data-item link-like')]/div[@title='INBOX']";
	public static final String PATH_SELECT_ACTIONAPP_BUTTON = "//div[@id='workflowsContainer']/div/aio-workflow-card[2]//aio-user-app-select/div/button";
	public static final String PATH_ACTION_APP_SEARCH_BOX = "//div[@id='workflowsContainer']/div/aio-workflow-card[2]//input[@placeholder='Search']";
	public static final String PATH_GMAIL_APP_CONTAINER_IMG_ACTION = "//div[@id='workflowsContainer']/div/aio-workflow-card[2]//aio-user-app-select-figure[@title='Gmail']/div/img";
	public static final String PATH_ACTION_SEARCH_BOX = "//div[@id='workflowsContainer']/div/aio-workflow-card[2]//aio-searchable-dropdown[@class='menu']/form/input";
	public static final String PATH_SEND_AN_EMAIL_DROPDOWN_ELEMENT = "//li[contains(@class,'link-like link-like-padding')]/div[@title='Send an Email']";
	public static final String PATH_GENERIC_INPUT_FIELDS_PLUS_BUTTON = "//label[@class='label-name'][contains(text(),'?')]/parent::aio-content-label/following-sibling::div//div[@aiodropdown]/button";
	public static final String PATH_GENERIC_DROPDOWN_ELEMENT = "//label[@class='label-name'][contains(text(),'?')]/parent::aio-content-label/following-sibling::div//li[contains(@class,'data-item link-like')]/div[@title='#']";
	public static final String PATH_TO_ADDRESSES_TEXTFIELD = "//label[@class='label-name'][contains(text(),'To Addresses')]/parent::aio-content-label/following-sibling::div//div[@class='content-editable form-control buttoned']";
	public static final String PATH_SAVE_BUTTON = "//a[text()='Save']";
	public static final String PATH_ON_OFF_BUTTON = "//span[@class='text text-off'][text()='OFF']";
	public static final String PATH_I_M_DONE_BUTTON = "//button[text()=\"I'm Done\"]";
	public static final String PATH_GO_TO_BOT_LIST = "//a[text()='Go to Bot list']";
	public static final String PATH_TOGGLE_BOT_BUTTON = "//span[@class='switch checked']/span[@class='disc']";
	public static final String PATH_TRASH_BUTTON = "//a[@class='link-like'][@title='Delete']";
	public static final String PATH_ALERT_DELETE_BUTTON = "//a[@class='btn mh1 mw5 mv0 btn-danger'][text()='Delete']";
	public static final String PATH_USER_ICON = "//img[@class='profile-image']";
	public static final String PATH_LOGOUT_BUTTON = "//a[@class='link-like'][text()='Logout']";
	
}
