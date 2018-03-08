package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Sword_Final {

	public static WebDriver wd;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//--------------------------------------------------
		//Given
		String URL = "http://www.sword-activerisk.com";
		String Keyword1 = "Risk Management";
		String Keyword2 = "Leverage";
		String Keyword3 = "Compatibility";
		//--------------------------------------------------
		
		//Launch & Navigate to URL
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.manage().window().maximize();
		
		String SearchKeys [] = {Keyword1,Keyword2,Keyword3};
		System.out.println("No. of Given Keywords : "+SearchKeys.length);
		
		for(int a=0; a<SearchKeys.length; a++) {
		String Keyword=SearchKeys[a];
		System.out.println("Keyword - "+(a+1)+" : "+Keyword);
		wd.navigate().to(URL);
		
		//Enter Keyword & search
		wd.findElement(By.name("s")).sendKeys(Keyword);
		wd.findElement(By.id("searchsubmit")).click();
		
		//Search Performed?
		String ActualTitle = wd.getTitle();
		//System.out.println("Title : "+ActualTitle);
		
		String ResultPageURL=wd.getCurrentUrl();
		
		if(ActualTitle.toLowerCase().contains(Keyword.toLowerCase())) {
			System.out.println("Search Performed Successfully for : "+Keyword);
		}
			
		//Get Result Links
		List<WebElement> ResultLinks=wd.findElements(By.className("metaPost"));
		int TotalResultLinks = ResultLinks.size();
		System.out.println(TotalResultLinks+" Result Links Found for "+Keyword);
		
		//Verify Result Links
		for(int i=0; i<TotalResultLinks; i++) {
			
			ResultLinks=wd.findElements(By.className("metaPost"));
			String LinkText=ResultLinks.get(i).getText();
			System.out.println("Result - "+(i+1)+" is "+LinkText);
			
			if(LinkText.toLowerCase().contains(Keyword.toLowerCase())) {
				System.out.println("Result Link-"+(i+1)+"is Valid");
			}
			
			//Links Working?
			String LinkDate[] = LinkText.split(" ");
			String Month=LinkDate[0];
			String Day=LinkDate[1];
			String Year=LinkDate[2].substring(0, 4);
			
			String DateLink = Month+" "+Day+" "+Year;
			//System.out.println("DateLink:"+DateLink);
			wd.findElement(By.linkText(DateLink)).click();
			
			
			String LoadedPageTitle = wd.getTitle();
			//System.out.println("LoadedPageTitle : "+LoadedPageTitle);
			
			//NAvigated to Correct Page?
			if(LinkText.toLowerCase().contains(LoadedPageTitle.toLowerCase())) {
				System.out.println("Loaded Page-"+(i+1)+" is Correct for : "+Keyword);
			}
			
			//Back to result PAge
			wd.navigate().to(ResultPageURL);
			System.out.println("-------------------------------------------------------------------------------------");
		}
		
		//Back to search page
		wd.navigate().to(URL);
		
		}	

	}

}

