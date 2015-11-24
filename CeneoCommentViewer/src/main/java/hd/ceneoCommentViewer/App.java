package hd.ceneoCommentViewer;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class App {

	private static int COMMENTS_PER_PAGE = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Document doc = null;
		String mainLink = "http://www.ceneo.pl/";
		String commentsFirstPageLinkPart = "#tab=reviews_scroll";
		String commentsNextPagesLinkPart = "/opinie-";
		String productCode = "";

		Integer numberOfComments = 0;

		System.out.println("Enter product code:");
		productCode = scanner.nextLine();

		try {
			doc = Jsoup.connect(mainLink + productCode + commentsFirstPageLinkPart).get();
			String title = doc.title();
			System.out.println("title: " + title);

			Element commentsPaginator = doc.select("span[itemprop=reviewCount]").first();
			numberOfComments = Integer.parseInt(commentsPaginator.text());
			Integer commentsPages = (int) Math.ceil(numberOfComments / (double) COMMENTS_PER_PAGE);
			System.out.println("numberOfComments: " + numberOfComments + ", " + "pages: " + commentsPages);

			for (int i = 2; i <= commentsPages; i++) {
				doc = Jsoup.connect(mainLink + productCode + commentsNextPagesLinkPart + i).get();
				title = doc.title();
				System.out.println("title: " + title);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		scanner.close();
	}
}
