package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@ManagedBean(name = "ceneoDownloadService")
@ApplicationScoped
public class CeneoDownloadService extends DownloadService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static int COMMENTS_PER_PAGE = 10;

	private static String mainLink = "http://www.ceneo.pl/";

	private static String commentsFirstPageLinkPart = "#tab=reviews_scroll";

	private static String commentsNextPagesLinkPart = "/opinie-";

	private static String commentsSpecPageLinkPart = "#tab=spec";
	
	@Override
	public void downloadProductPage(Integer productId) {
		try {
			productPage = Jsoup.connect(mainLink + productId + commentsSpecPageLinkPart).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void downloadCommentsPages(Integer productId) {
		commentsPages = new ArrayList<>();

		Document doc = null;
		Integer numberOfComments = 0;

		try {
			doc = Jsoup.connect(mainLink + productId + commentsFirstPageLinkPart).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0").get();
			commentsPages.add(doc);

			Element commentsPaginator = doc.select("span[itemprop=reviewCount]").first();
			numberOfComments = Integer.parseInt(commentsPaginator.text());
			Integer numberOfCommentsPages = (int) Math.ceil(numberOfComments / (double) COMMENTS_PER_PAGE);

			setProgress(100 / numberOfCommentsPages);

			for (int i = 2; i <= numberOfCommentsPages; i++) {
				doc = Jsoup.connect(mainLink + productId + commentsNextPagesLinkPart + i).get();
				commentsPages.add(doc);
				setProgress(i * 100 / numberOfCommentsPages);
			}
		} catch (HttpStatusException e) {
			e.printStackTrace();
			System.out.println("Brak produktu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
