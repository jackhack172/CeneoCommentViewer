package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

public abstract class DownloadService {

	protected Document productPage;

	protected List<Document> commentsPages;

	private int progress = 0;

	public abstract void downloadProductPage(Integer productId) throws IOException;

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
