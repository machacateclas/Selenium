package principalTesting;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class reservationFlightTest {

	public static void main(String[] args) throws Throwable  {
		

		 WebDriver driver = new ChromeDriver();
	//	 String i=null;
		 
		driver.get("http://www.norwegian.com/es/");
		
		//driver.findElement(By.id("fly_from")).click();
		driver.findElement(By.id("fly_from")).sendKeys("Barcelona");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#fly_from-BCN > a")).click();
		
		
		driver.findElement(By.id("fly_to")).sendKeys("Copenhague (CPH)");
		
		driver.findElement(By.cssSelector("#fly_to-CPH > a > strong")).click();
		
		//i = driver.getCurrentUrl();
		
        Thread.sleep(1000);  	
    /////    #datepicker-724-416-title                  
      driver.findElement(By.xpath("//*[@id=\"outboundDate\"]/div/div/label/input")).click();
      Thread.sleep(1000);
        // calendar__datepicker
      
      driver.findElement(By.cssSelector("#outboundDate > div > div > div.calendar__datepicker-wrapper > div > div > div > div > button.calendarnavigation__button.pull-right > i")).click();
      
      Thread.sleep(2000);
      busquedaMesDiaVuelo("diciembre 2016", "23", driver, false);
      
      Thread.sleep(2000);
      
      busquedaMesDiaVuelo("enero 2017", "04", driver, true);
      
      
      driver.findElement(By.cssSelector("#adultCount")).sendKeys("2");
  /*    
      driver.findElement(By.cssSelector("#addChildren")).click();
      driver.findElement(By.cssSelector("body > main > div.daysearch-cover > div > div.tab-box.tab-box--booking > div.tab-box__page > div > div > form > div > div > div > fieldset:nth-child(3) > section > div:nth-child(2) > div > div:nth-child(2) > div > button.numberfield__button.numberfield__button--add")).click();
      
  */    
      
      
     // driver.findElement(By.name("radio_2")).click();
      //driver.findElement(By.xpath("//*[@id=\"searchButton\"]")).click();
      //driver.findElement(By.cssSelector("#searchButton")).click();
     // driver.findElement(By.xpath("/html/body/main/div[4]/div/div[2]/div[2]/div/div/form/div/div/div/fieldset[4]/div/span[1]/label/input")).click();
      
      driver.findElement(By.id("searchButton")).click();
      
	 
 //  driver.findElement(By.xpath("//*[@id=\"ctl01_ctl00_MainContentRegion_MainRegion_ctl00_lbtContinue\"]"));
    		Thread.sleep(5000);
		//driver.close();
	}
	
	public static void busquedaMesDiaVuelo(String mes, String dia, WebDriver driver, Boolean retorno) throws Throwable
	{
		 WebElement datepicker =null;
		 WebElement datepickerBusqueda=null;
		 List<WebElement> divs=null;
		String botonSig ="#outboundDate > div > div > div.calendar__datepicker-wrapper > div > div > div > div > button.calendarnavigation__button.pull-right > i"; 
		if (retorno)
		{
			botonSig="#returnDate > div > div > div.calendar__datepicker-wrapper > div > div > div > div > button.calendarnavigation__button.pull-right";
		}
		
		
	  datepicker = driver.findElement(By.className("ui-datepicker-content"));
	                                //                calendar__datepicker
	  if (retorno)
	  {
		  datepicker= driver.findElement(By.cssSelector("#returnDate > div > div > div.calendar__datepicker-wrapper > div > div > div"));
	     
	     System.out.println("Mes Retorno: "+datepicker.getText().toString());
	  }
	  else 
	  	{
		  datepicker = driver.findElement(By.className("ui-datepicker-content"));
		}
	  
      divs=datepicker.findElements(By.tagName("button"));  
      
      String mesaBusqueda=divs.get(1).getText().toString(); 
      System.out.println("este el nuevo mes"+mesaBusqueda);
      
    while (!mesaBusqueda.equals(mes))
    {
    	Thread.sleep(2000);
    	driver.findElement(By.cssSelector(botonSig)).click();
    									    
    	  if (retorno)
    	  	{
    		  datepickerBusqueda= driver.findElement(By.cssSelector("#returnDate > div > div > div.calendar__datepicker-wrapper > div > div > div"));
    	  	}
    	  else
    	  	{
    		  datepickerBusqueda = driver.findElement(By.className("ui-datepicker-content"));  
    	  	}
	     
    	  
	      List<WebElement> divsBusqueda=datepickerBusqueda.findElements(By.tagName("button"));
	      
	      mesaBusqueda=divsBusqueda.get(1).getText().toString();
	         
	      System.out.println("mes igual a? "+mesaBusqueda);
	      
    }
    
    
    
    if (retorno)
  	{
	  datepickerBusqueda= driver.findElement(By.cssSelector("#returnDate > div > div > div.calendar__datepicker-wrapper > div > div > div"));
  	}
  else
  	{
	  datepickerBusqueda = driver.findElement(By.className("ui-datepicker-content"));  
  	}
     
      List<WebElement> diasBusqueda=datepickerBusqueda.findElements(By.tagName("button"));
    
    //Busqueda de los Dias 
      for (WebElement elemntDiv: diasBusqueda)
		{  
			//Select 20th Date
    	  System.out.println("dia :"+elemntDiv.getText().toString());
    	  
			if (elemntDiv.getText().equals(dia))
			  { 
				System.out.println("encontrado el dia : "+elemntDiv.getText().toString());
				
				elemntDiv.click();
				break;  
			  }
		}
}
	
	
}
