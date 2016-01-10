package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.eclipse.jetty.util.thread.Scheduler.Task;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * 
* Serwis pozwala pobrać dane ze strony www.morele.net.
 *
 */
@ManagedBean(name = "moreleDownloadService")
@ApplicationScoped
public class MoreleDownloadService extends DownloadService implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Główny link.
	 */
	private static String mainLink = "http://www.ceneo.pl/";

	/**
	 * Link do podstrony z serwisami zawierającymi produkt.
	 */
	private static String commentsClickPageLinkPart = "#tab=click";

	/**
	 * Link zawierajacy opis produktu.
	 */
	private static String commentsSpecPageLinkPart = "#tab=spec";

	/**
	 * Metoda pobiera stronę z produktem.
	 */
	@Override
	public void downloadProductPage(Integer productId) {
		try {
			productPage = Jsoup.connect(mainLink + productId + commentsSpecPageLinkPart).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Metoda pobiera strony z komentarzami.
	 */
	@Override
	public void downloadCommentsPages(Integer productId) {
		commentsPages = new ArrayList<>();

		Document doc = null;

		try {		
			WebDriver driver = new FirefoxDriver();
			driver.get(mainLink + productId + commentsClickPageLinkPart);
			
			List<WebElement> showRemainingOffers = driver.findElements(By.className("remaining-offers-trigger"));
			if(!showRemainingOffers.isEmpty()){
				showRemainingOffers.get(0).click();
			}

			WebElement moreleLink = driver.findElement(By.xpath("//img[contains(@alt,'morele.net')]/parent::a"));
			driver.navigate().to(moreleLink.getAttribute("href"));
					
			WebElement moreleCommentsTab = driver.findElement(By.id("li4"));
			moreleCommentsTab.click();
			
			
			List<WebElement> moreleShowAllcommentsButton =  driver.findElements(By.id("wwrozt"));
			if(!moreleShowAllcommentsButton.isEmpty()){
				moreleShowAllcommentsButton.get(0).click();
			}
			
			String morelePageSource = driver.getPageSource();
			driver.quit();
			
			doc = Jsoup.parse(morelePageSource);
			commentsPages.add(doc);
			
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.out.println("Brak produktu");
		}
	}
}