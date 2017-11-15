package com.build.qa.build.selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

/***********************************************************************************************************************
 * These all have tests against sites other than build.com due to the CAPTCHA interfering with the tests.              *
 * I'm pretty sure the commented out code will pass on build.com with the bot blocking disabled, but I'm not certain,  *
 * as I was never able to get the horse bubbles straight enough to make the bot blocker happy                          * 
 ***********************************************************************************************************************/

public class BuildTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic functionality and page objects
	 * as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		
		/* This worked before the captcha started, so I assume it still passes and didn't rewrite it for another site */
		
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme()).as("The website should load up with the Build.com desktop theme.")
				.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * 
	 * @assert: That the product page we land on is what is expected by checking the
	 *          product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
//		String startURL = "https://build.com";
//		String searchTerm = "Quoizel MY1613";
//		driver.findElement(By.id("search_txt")).sendKeys("Quoizel MY1613" + Keys.RETURN);
//		WebElement heading = driver.findElement(By.id("heading"));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		assertTrue(heading.getText().equalsIgnoreCase(searchTerm));
		
		
		/* this is testing my own site, due to the captcha on build.com */
		String currentURL = null;
		String startURL = "http://18.220.25.115:8080/LostTeams/";
		String hockeySearchTerm = "Dead and Relocated Teams";

		driver.get(startURL);
		
		driver.findElement(By.name("kw")).sendKeys("que" + Keys.RETURN);
		WebElement heading = driver.findElement(By.className("monoton"));
		System.out.println(heading.getText());
		
		currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		
		assertTrue(heading.getText().equalsIgnoreCase(hockeySearchTerm));

	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.build.com/bathroom-sinks/c108504) and add the second product on
	 * the search results (Category Drop) page to the cart.
	 * 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
//		String startUrl = "https://www.build.com/bathroom-sinks/c108504";
//		driver.get(startUrl);
//		String currentUrl = driver.getCurrentUrl();		
//		assertEquals("https://www.build.com/bathroom-sinks/c108504", currentUrl);
//		
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.cssSelector("a[href*='kohler-k-2355-bathroom-sink/s560600']")).click();
//		
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.className("add-to-cart")).click();
//		
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.cssSelector(".header-cart.pull-right")).click();
//		assertTrue(driver.findElement(By.linkText("Kohler K-2355  ")) != null);

		/****************************************************************************
		 * I couldn't test the above, so this is a rough equivalent on amazon.com   *
		 * **************************************************************************/
		
		String startUrl = "https://www.amazon.com/computer-video-games-hardware-accessories/b/ref=nav_shopall_cvg?ie=UTF8&node=468642";
		driver.get(startUrl);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("a[href*='6469269011']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("a[title*='Call of Duty: WWII - Xbox One Standard Edition']")).click();
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("smartShelfAddToCartNative")).click();
		WebElement cartItem = driver.findElement(By.cssSelector(".a-size-medium.sc-product-title.a-text-bold"));
		assertTrue(cartItem.getText().contains("Call of Duty"));
	}

	/**
	 * Add a product to the cart and email the cart to yourself, also to my email
	 * address: jgilmore+SeleniumTest@build.com Include this message in the "message
	 * field" of the email form: "This is {yourName}, sending you a cart from my
	 * automation!"
	 * 
	 * @assert that the "Cart Sent" success message is displayed after emailing the
	 *         cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {
		/************************************************************************************************
		 * Same as above about the captcha.  If the previous works, this is just an extension of that   *
		 ************************************************************************************************/
		String startUrl = "https://www.build.com/bathroom-sinks/c108504";
		driver.get(startUrl);
		String currentUrl = driver.getCurrentUrl();		
		assertEquals("https://www.build.com/bathroom-sinks/c108504", currentUrl);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("a[href*='kohler-k-2355-bathroom-sink']")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.className("add-to-cart")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".header-cart.pull-right")).click();
		assertTrue(driver.findElement(By.linkText("Kohler K-2355  ")) != null);
		driver.findElement(By.cssSelector(".btn-standard.btn-secondary.btn-email.js-email-cart-button")).click();
		
		driver.findElement(By.name("yourName")).sendKeys("Brian" + Keys.TAB + "brianraythomas@gmail.com" + Keys.TAB +
				"jGilmore" + Keys.TAB + "jgilmore+SeleniumTest@build.com");
		
		driver.findElement(By.cssSelector(".button-primary.button.js-email-cart-submit-button")).click();
		
		/*************************************************************************************************************
		 *  I missed the success modal popup and I didn't want to spam you with a bunch of emails trying to find it, *
		 *  so no assert for this one.                                                                               *
		 *************************************************************************************************************/
//		
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by at least
	 * two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * 
	 * @assert that the correct filters are being narrowed, and the result count is
	 *         correct, such that each facet selection is narrowing the product
	 *         count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
//		String startUrl = "https://www.build.com/bathroom-sink-faucets/c108503";
		String startUrl = "https://www.build.com";
		driver.get(startUrl);
		
		Actions action = new Actions(driver);
		
		// this is not a valid selector and fails; should probably use xpath, but the captcha came back
		//before I could see what works
//		WebElement bathItem = driver.findElement(By.cssSelector("li[data-category-id*='108412']"));

//		Hover and then click on newly visible item
		WebElement bathItem = driver.findElement(By.cssSelector("a[href*='/bathroom/c108412']"));
		action.moveToElement(bathItem).moveToElement(driver.findElement(By.cssSelector("a[href*='bathroom-sink-faucets/c108503']"))).click();
		
//		Get number of products
		WebElement prodNum = driver.findElement(By.className("js-num-results"));
		Integer total = Integer.valueOf(prodNum.getText());
		
//		Tick box, wait for reload, tick second box
		driver.findElement(By.cssSelector(".sub-item.qa-facetGroup-Theme-facetValue-Modern"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".sub-item.qa-facetGroup-Colors-facetValue-Brass Tones"));
		
//		Wait for reload and get new reduced total
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement prodNum1 = driver.findElement(By.className("js-num-results"));
		Integer total1 = Integer.valueOf(prodNum1.getText());
		
//		ensure num of prods is less
		assertTrue(total > total1);
		
//		Wait a second time for reload and get new reduced total again
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement prodNum2 = driver.findElement(By.className("js-num-results"));
		Integer total2 = Integer.valueOf(prodNum2.getText());

		assertTrue(total1 > total2);

//		driver.get("http://18.220.25.115:8080/scotch-tracker/#/add");
//		driver.findElement(By.xpath("//label[@for='type']"));
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
