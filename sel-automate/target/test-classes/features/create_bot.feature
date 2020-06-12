#Author: Vinay Kumar Pawar
#Last Updated on: 12 June 2020
Feature: Create an automated bot to fowrard email from one email account
  		 to other email account using automate.io bots

  Scenario Outline: Create a Gmail Bot on automate.io using Selenium
    Given <host> login page is already open in browser and steps will get logged in <log-file>
    When user enter <from-email> and <password> to loging successfully
    And create bot to forward email from <from-email> to <to-email>
    And enable the bot that is created
    Then user disable the bot
    And delete the bot before logout from <host>

    Examples: 
      | host                  | log-file          | from-email                 | password          | to-email                |
      | "https://automate.io" | "TestLogSelenium" | "vinaypawar0902@gmail.com" | "Harharinfigar@1" | "vkpvinay016@gmail.com" |