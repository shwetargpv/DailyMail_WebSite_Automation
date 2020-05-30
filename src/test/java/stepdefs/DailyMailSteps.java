package stepdefs;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

import allocator.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import listeners.ExtentReportListener;

public class DailyMailSteps extends ExtentReportListener 
{ 
	ReusableMethods RM = new ReusableMethods();
	ExtentTest logInfo=null;
	@Given("^I navigate to DailyMail website$")
	public void navigateToAmazonWebsite() throws ClassNotFoundException, IOException
	{
		try {
			test = extent.createTest(Feature.class, "Close JB Player and Google Ad");                         
			test=test.createNode(Scenario.class, "Verify closing of JB Player and Google Ad");                       
			logInfo=test.createNode(new GherkinKeyword("Given"), "Close JB player and Google Ad");
			driver.get(prop.getProperty("AppURL"));
			RM.reportandscreenshot("DailyMailLogo_HomePage", "User navigation to DailyMail Website", logInfo, captureScreenShot(driver));
		}
		catch(Exception e)
		{
			testStepHandle("FAIL",driver,logInfo,e);    
		}
	}
	@When("^I close the JB Player$")
	public void closingPlayerAd() throws Exception
	{
		Thread.sleep(10000);
		RM.PerformActionOnElement("DailyMailJbPLYRClsBtn_HomePage", "clickwithactionclass", "");
		RM.reportandscreenshot1("DailyMailJbPLYRClsBtn_HomePage", "JB Player is closed", logInfo, captureScreenShot(driver));
	}
	@When("^I click and Verify Facebook window with title$")
	public void clickingFcBkIcon() throws Exception
	{
		RM.PerformActionOnElement("DailyMailFacebookIcn_HomePage", "click", "");
		RM.SwitchToChildWindow();
		String ActualTitle=RM.getTitle();
		System.out.println(ActualTitle+"ActualTitle");
		RM.compareAndreport(ActualTitle, "Daily Mail - Home | Facebook", "Facebook window opened and title is displayed", logInfo, captureScreenShot(driver));
		RM.closebrowser();
		RM.switchBackToParentWindow();
	}
	@When("^I select web and Pass the word \"([^\"]*)\"$")
	public void searchandverify(String Keyword) throws Exception
	{
		//RM.PerformActionOnElement("DailyMailWebRdoBtn_HomePage", "click", "");
		RM.PerformActionOnElement("DailyMailSearchBox_HomePage", "sendkeys", Keyword );
		//Thread.sleep(5000);
		RM.PerformActionOnElement("DailyMailSearchBtn_HomePage", "waitforelementclickable", "" );
		//RM.PerformActionOnElement("DailyMailJbPLYRClsBtn_HomePage", "click", "");
		WebElement element = RM.FindAnElement("DailyMailSearchBtn_HomePage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		RM.PerformActionOnElement("DailyMailSearchBtn_HomePage", "clickwithactionclass", "" );
		Thread.sleep(5000);
	}
	@And("^I sort the results with relevence$")
	public void sortingResult() throws Exception
	{
		RM.PerformActionOnElement("DailyMailRelevenceRdoBtn_ResultPage", "click", "" );
		RM.reportandscreenshot("DailyMailPagingDetail_ResultPage", "Details of Results pagination", logInfo, captureScreenShot(driver));
		String FirstPageUrl=RM.getUrl();
		RM.PerformActionOnElement("DailyMailNextBtn_ResultPage", "click", "" );
		String SecondPageUrl=RM.getUrl();
		if(FirstPageUrl.contains(SecondPageUrl))
		{
			logInfo.fail("Pagination is not working as expected for page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  

		}
		else
		{
			logInfo.pass("Pagination is working as expected for page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}


	}
	@Then("^I click on random search and verify the search results$")
	public void verifyingResults() throws Exception
	{
		String SearchedWord=RM.getTextOnRandomElement("DailyMailSearchKeyword_ResultPage");
		System.out.println("SearchedWord : " +SearchedWord);
		String ExpectedWord=RM.getTextFromElement("DailyMailResultText_ResultSpecificPage");
		RM.compareAndreport(SearchedWord, ExpectedWord, "Results are the same in navigated page", logInfo, captureScreenShot(driver));
	}
	@And("^I click on checkbox in Advance search filter with \"([^\"]*)\" and later find the \"([^\"]*)\"$")
	public void verifyAdvanceFilter(String Catagory, String element) throws Exception
	{
		RM.PerformActionOnElement("DailyMailCheckBox_ResultPage", "clickwithactionclass", Catagory );
		Thread.sleep(5000);
		RM.PerformActionOnElement("DailyMailGoLink_Resultpage", "clickwithactionclass", "");
		Thread.sleep(5000);
		boolean condition=RM.listOfWebelementAsString("DailyMailCommomLink_ResultPage", element);
		if(condition)
		{
			logInfo.pass("All the search results are having choosen link in filter option");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
		}
		else
		{
			logInfo.fail("All the search results are not having choosen link in filter option");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
		}

	}
	@And("^I click on Total result count that is Ten$")
	public void totalresult() throws Exception
	{
		RM.PerformActionOnElement("DailyMailResultNum_ResultPage", "click", "");
		Thread.sleep(5000);
		RM.totalNumOfLink("DailyMailAllResults_ResultPage", logInfo, captureScreenShot(driver));
	}
	@Then("^I verify the search key word is highlighted on the webpage$")
	public void verifyingHighlightedSearchWord() throws Exception
	{
		String ActualColor=RM.getCssProps("DailyMailSearchWordHighLighted_ResultPage", "color");
		String ActualBackColor=RM.getCssProps("DailyMailSearchWordHighLighted_ResultPage", "backgroundColor");
		if(!ActualColor.equals(ActualBackColor))
		{

			logInfo.pass("Text is highlighted");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Text is highlighted!");

		}

		else
		{
			logInfo.fail("Text is not highlighted");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Text is not highlighted!");
		}
	}
	@Then("^I refresh the page and verify that the results are same$")
	public void afterRefreshResults() throws Exception
	{
		ArrayList<String> ResultsBeforeRefresh=RM.webElementList("DailyMailAllResults_ResultPage");
		RM.refreshWebPage();
		ArrayList<String> ResultAfterRefresh=RM.webElementList("DailyMailAllResults_ResultPage");
		if(ResultsBeforeRefresh.equals(ResultAfterRefresh))
		{
			logInfo.pass("Results are same even after page refresh");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Results are same even after page refresh");

		}
		else
		{
			logInfo.fail("Results are not same after page refresh");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Results are not same after page refresh");
		}


	}
	@And("^I click on Top Button$")
	public void takeToTopOfThePage() throws Exception
	{
		WebElement element = RM.FindAnElement("DailyMailGoLinkBottom_Resultpage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		RM.PerformActionOnElement("DailyMailTopLink_ResultPage", "click", "");
	}
	@Then("^I verify that i am on the top of the page$")
	public void verifyingControlOnTop() throws IOException
	{
		boolean Condition=RM.waitForCondition("NotPresent", "DailyMailTopLink_ResultPage", 5000);
		if(Condition==true)
		{
			logInfo.pass("I am on the Top of the Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("I am on the Top of the Page");

		}
		else 
		{
			logInfo.fail("I am not on the Top of the Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("I am not on the Top of the Page");
		}
	}
	@And("^I click on the Ad$")
	public void clickOnAd() throws Exception
	{
		RM.PerformActionOnElement("DailyMailAdImage_HomePage", "click", "");
	}
	@When("^I click on 5days forcast$")
	public void clickOnForcast() throws Exception
	{
		RM.PerformActionOnElement("DailyMailForcast_HomePage", "click", "");
		Thread.sleep(5000);
	}
	@And("^I verify forcast information of \"([^\"]*)\" \"([^\"]*)\"$")
	public void sydneyTemp(String City, String search1) throws Exception
	{
		String SydneyTemp=RM.getDynamicTextFromElement("DailyMailForcast_Temp",City);

		int valueFromWebsite= Integer.parseInt(SydneyTemp.replace("°C", "").trim());
		driver.get(prop.getProperty("AppURL2"));
		Thread.sleep(5000);
		RM.PerformActionOnElement("DailyMailForcast_SearchBoxGoogle", "sendkeys", search1);
		RM.PerformActionOnElement("DailyMailForcast_SearchBoxGoogle", "enterkeys", "");
		String SydTemGoogle= RM.getTextFromElement("DailyMailTem_GooglePage");
		int ValueFromGoogl = Integer.parseInt(SydTemGoogle);
		if((valueFromWebsite==ValueFromGoogl+1) ||(valueFromWebsite==ValueFromGoogl)||(valueFromWebsite==ValueFromGoogl+2)||(valueFromWebsite+1==ValueFromGoogl)||(valueFromWebsite+2==ValueFromGoogl))
		{
			logInfo.pass("Temprature are same in both");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Temprature are same in both");
		}
		else
		{
			logInfo.fail("Temprature are not same in both");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Temprature are not same in both"+valueFromWebsite +ValueFromGoogl );
		}
	}
	@When("^I click on Privacy Policy$")
	public void clickingOnPrivacy() throws Exception
	{
		RM.PerformActionOnElement("DailyMailPrivacyPolicyLink_HomePage", "click", "");
		WebElement element = RM.FindAnElement("DailyMailShareIcon_PolicyPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		RM.PerformActionOnElement("DailyMailShareIcon_PolicyPage", "click", "");
	}
	@Then("^I share the article and verify new window$")
	public void sharingArticle() throws Exception
	{
		RM.PerformActionOnElement("DailyMailRedditIcon_PolicyPage", "click", "");
		Thread.sleep(2000);
		RM.SwitchToChildWindow();
		Thread.sleep(2000);
		String ActualTitle=RM.getTitle();
		System.out.println("Title of new window is:" +ActualTitle);
		RM.compareAndreport(ActualTitle, "reddit.com: Log in", "New window opens up when click on reddit", logInfo, (captureScreenShot(driver)));
		RM.closebrowser();
		RM.switchBackToParentWindow();
		String TitleOfParent=RM.getTitle();
		System.out.println("Title of Parent: "+TitleOfParent);

	}
	@When("^I click on comment Link$")
	public void  clickOnCommentLink() throws Exception
	{
		WebElement element = RM.FindAnElement("DailyMailCommentLink_HomePage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);	
		RM.PerformActionOnElement("DailyMailCommentLink_HomePage", "click", "");
		Thread.sleep(5000);
	}
	@Then("^I verify the AddComment section with \"([^\"]*)\"$")
	public void verifyingCommentSection(String Comment) throws Exception
	{
		WebElement element = RM.FindAnElement("DailyMailAddYourCommentBtn_CommentPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);	
		RM.PerformActionOnElement("DailyMailAddYourCommentBtn_CommentPage", "click", "");
		RM.PerformActionOnElement("DailyMailEnterYourCommentBox_CommentPage", "sendkeys", Comment);
		RM.PerformActionOnElement("DailyMailSubmitCommentBtn_CommentPage", "click", "");
		//RM.SwitchToChildWindow();
		Thread.sleep(5000);
		RM.reportandscreenshot("DailyMailLoginHeading_LoginPage", "Login window opened", logInfo, (captureScreenShot(driver)));
		//	RM.closebrowser();

	}
	@Then("^I verify by default all the comments are sorted with newest$")
	public void newestComment() throws Exception
	{
		WebElement element = RM.FindAnElement("DailyMailAddYourCommentBtn_CommentPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);	
		String ActualColor=RM.getCssProps("DailyMailNewestTab_CommentPage", "color");
		String ActualBackColor=RM.getCssProps("DailyMailNewestTab_CommentPage", "backgroundColor");
		if(!ActualColor.equals(ActualBackColor))
		{

			logInfo.pass("By default Newest tab is highlighted");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("By default Newest tab is highlighted");

		}

		else
		{
			logInfo.fail("Newest tab is not highlighted");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("Newest tab is not highlighted!");
		}
		WebElement ShowMoreElement = RM.FindAnElement("DailyMailShowMoreResults_CommentPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ShowMoreElement);
		RM.PerformActionOnElement("DailyMailShowMoreResults_CommentPage", "click", "");
		boolean condition=RM.SortOfWebElementsAsString("DailyMailTimeOfComment_CommentPage");
		if(condition)
		{
			logInfo.pass("All the comments are sorted with newest");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("All the comments are sorted with newest");

		}
		else 
		{
			logInfo.fail("All the comments are not sorted with newest");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("All the comments are not sorted with newest");
		}
	}
	@And("^I verify clicking on Oldest it should display all comments with Oldest order$")
	public void oldestcomments() throws Exception
	{
		Thread.sleep(5000);
		RM.PerformActionOnElement("DailyMailOldestTab_CommentPage", "clickwithactionclass", "");
		WebElement ShowMoreElement = RM.FindAnElement("DailyMailShowMoreResults_CommentPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ShowMoreElement);
		RM.PerformActionOnElement("DailyMailShowMoreResults_CommentPage", "click", "");
		boolean condition=RM.SortOfWebElementsInDecOrder("DailyMailTimeOfComment_CommentPage");
		if(condition)
		{
			logInfo.pass("All the comments are sorted with oldest");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("All the comments are sorted with oldest");

		}
		else 
		{
			logInfo.fail("All the comments are not sorted with oldest");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
			System.out.println("All the comments are not sorted with oldest");
		}
		
	}
	@And("^I verify best rated sorting$")
	public void bestRatedSorting() throws Exception
	{
		Thread.sleep(5000);
		RM.PerformActionOnElement("DailyMailBestRatedTab_CommentPage", "clickwithactionclass", "");
		WebElement ShowMoreElement = RM.FindAnElement("DailyMailShowMoreResults_CommentPage");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ShowMoreElement);
		if(RM.SortOfWebElementsInDecOrderWtoutSplit("DailyMailNumOfUpRates_CommentPage"))
		{
			logInfo.pass("All the comments are sorted with Best rated");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
		}
		else {
			logInfo.fail("All the comments are not sorted with Best rated");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));  
		}
	}
	@When("^I click news link and verify breaking news$")
	public void breakingNewsPage() throws Exception
	{
		RM.PerformActionOnElement("DailyMailNewsLink_DailyMailHomePage", "click", "");
		RM.PerformActionOnElement("DailyMailBreakingNewsLink_HomePage", "click", "");
		Thread.sleep(7000);
		String [] FirstTopNewsDate=RM.getTextFromElement("DailyMailBreakingNewsTopOneNews_HomePage").split(" ");
		String [] SecondTopNewsDate=RM.getTextFromElement("DailyMailBreakingNewsTopSecondNews_HomePage").split(" ");
		String [] ThirdTopNewsDate= RM.getTextFromElement("DailyMailBreakingNewsTopThreeNews_HomePage").split(" ");
		String CurrentDate= RM.getCurrentDate();
		String YesterdayDate= RM.getYesturdayDate();
		if((FirstTopNewsDate[0].equalsIgnoreCase(CurrentDate))||(FirstTopNewsDate[0].equalsIgnoreCase(YesterdayDate)))
		{
			logInfo.pass("Top first news is either from today date or yesterday date");
		}
		else {
			logInfo.fail("Top first news is not from today date niether from yesterday date");
		}
		
		if((SecondTopNewsDate[0].equalsIgnoreCase(CurrentDate))||(SecondTopNewsDate[0].equalsIgnoreCase(YesterdayDate)))
		{
			logInfo.pass("Top second news is either from today date or yesterday date");
		}
		else {
			logInfo.fail("Top second news is not from today date niether from yesterday date");
		}
		if((ThirdTopNewsDate[0].equalsIgnoreCase(CurrentDate))||(ThirdTopNewsDate[0].equalsIgnoreCase(YesterdayDate)))
		{
			logInfo.pass("Top third news is either from today date or yesterday date");
		}
		else {
			logInfo.fail("Top third news is not from today date niether from yesterday date");
		}
	}
	@When("^I verify the top right corner day and date$")
	public void getRightCornerDayandDate() throws Exception
	{
		String Day= RM.getTodayDayName();
		String Date=RM.getdate();
		String [] TodayDayNDate=RM.getTextFromElement("DayNDateTextTopRight_HomePage").split(", ");
		TodayDayNDate[0].toUpperCase();
		if(Day.equalsIgnoreCase(TodayDayNDate[0]))
		{
			logInfo.pass("Today day name and displayed name on top right corner are same");
		}
		else {
			logInfo.fail("Today day name and displayed name on top right corner are not same");
		}
		if(Date.equalsIgnoreCase(TodayDayNDate[1]))
		{
			logInfo.pass("Today date and displayed date on top right corner are same");
		}
		else {
			logInfo.fail("Today date and displayed date on top right corner are not same");
		}
	}
	@When("^I verify overcast timing is 1 hour later than current time$")
	public void overcastVerifying() throws Exception
	{
		int result=0;
		String[] OvercastTime=RM.getTextFromElement("OvercastTiming_HomePage").split("P");
		int OvercastTimeDisplayed = Integer.parseInt(OvercastTime[0]);
		String []ActualTime=RM.getCurrentTime().split(" ");
		String []Value= ActualTime[1].split(":");
		int CurrentTime = Integer.parseInt(Value[0]);
		if(result>12)
         result= CurrentTime-12;
         if(result-OvercastTimeDisplayed==1)
         {
        	 logInfo.pass("Time is later than current time");
         }
         else {
        	 logInfo.fail("Time is same than current time");
         }
		
		
	}
	@When("^I verify archive list$")
	public void verifyingArchive() throws Exception
	{
		
		RM.scrollToView("ArchiveLink_FooterPage");
		RM.PerformActionOnElement("ArchiveLink_FooterPage", "click", "");
		Thread.sleep(5000);
	//	String[] CurrentMonth=RM.getCurrentMonthNYear().split(" ",2);
		String CurrentMonth=RM.getCurrentMonthNYear();
		String CurrentMonthNYear=CurrentMonth.substring(2, 11);
		RM.PerformActionOnElement("CurrentMonth_ArchivePage", "click", "");
		if(RM.listOfDates("ListOfDates_ArchivePage", CurrentMonthNYear, logInfo))
		{
			logInfo.pass("All the dates are from currentMonth and sorted");
		}
		else {
			logInfo.fail("All the dates are not from current month and not even sorted");
		}
		
	}
	@When("^I verify yesterday date$")
	public void verifyingYesterdayDate() throws Exception
	{
		
		RM.scrollToView("ArchiveLink_FooterPage");
		RM.PerformActionOnElement("ArchiveLink_FooterPage", "click", "");
		Thread.sleep(5000);
		RM.PerformActionOnElement("CurrentMonth_ArchivePage", "click", "");
		String YesterdayDate=RM.getYesterdayInYYYYFormat();
		RM.PerformActionOnElement("YesterdayDate_ArchivePage", "click", YesterdayDate);
        String TextOfNewsHeading= RM.getTextFromElement("HeadingOfNews_ArchivePage");
		
		if(TextOfNewsHeading.contains(YesterdayDate))
		{
			logInfo.pass("After click on yesterday date, yesterday news displayed");
		}
		else {
			logInfo.fail("After click on yesterday date, yesterday news not displayed");
		}
		
	}
	@When("^I verify SiteMap$")
	public void verifyingSiteMap() throws Exception
	{
		RM.scrollToView("SiteMapLink_Homepage");
		RM.PerformActionOnElement("SiteMapLink_Homepage", "click", "");
		RM.PerformActionOnElement("LatestHeadline_HealthSiteMap", "click", "");
	    ArrayList<String> ResultDisplayedBefore=RM.webElementList("ResultsDisplayed_SiteMap");
	  //  RM.scrollToView("MoreDropDown_SiteMap");
	    RM.PerformActionOnElement("MoreDropDown_SiteMap", "click", "");
	    RM.PerformActionOnElement("Property_SiteMap", "click", "");
	   ArrayList<String> ResultDisplayedAfter= RM.webElementList("PropertyResults_SiteMap");
	   if(!ResultDisplayedBefore.equals(ResultDisplayedAfter))
	   {
		   logInfo.pass("Result got changed");
	   }
	   else {
		   logInfo.fail("Result not changed");
   
	   }
		
	}

	
}










