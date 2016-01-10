package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

/**
 * 
 * Serwis służący do pobierania danych ze stron internetowych.
 *
 */
public abstract class DownloadService {

	/**
	 * Strona produktu.
	 */
	protected Document productPage;

	/**
	 * Lista stron z komentarzami.
	 */
	protected List<Document> commentsPages;

	/**
	 * Postęp pobierania stron.
	 */
	private int progress = 0;

	/**
	 * Metoda pobiera stronę z produktem.
	 * @param productId Id produktu.
	 * @throws IOException Zwraca wyjątek jeśli produkt o podanym id nie istnieje.
	 */
	public abstract void downloadProductPage(Integer productId) throws IOException;

	/**
	 * Metoda pobiera strony z komentarzami.
	 * @param productId Id produktu dla którego mają zostać pobrane komentarze.
	 */
	public abstract void downloadCommentsPages(Integer productId);

	public Document getProductPage() {
		return productPage;
	}

	public List<Document> getCommentsPages() {
		return commentsPages;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

}
