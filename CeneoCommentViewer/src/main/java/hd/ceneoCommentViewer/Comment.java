package hd.ceneoCommentViewer;

import java.util.Date;
import java.util.List;

class Comment {

	private String username;

	private String summary;

	private Date createDate;

	private List<String> disadvantages;

	private List<String> advantages;

	private int numberOfStarts;

	private int numberOfOpinions;

	private int numberOfPositiveOpinions;

	private boolean recommended;

	public Comment() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<String> getDisadvantages() {
		return disadvantages;
	}

	public void setDisadvantages(List<String> disadvantages) {
		this.disadvantages = disadvantages;
	}

	public List<String> getAdvantages() {
		return advantages;
	}

	public void setAdvantages(List<String> advantages) {
		this.advantages = advantages;
	}

	public int getNumberOfStarts() {
		return numberOfStarts;
	}

	public void setNumberOfStarts(int numberOfStarts) {
		this.numberOfStarts = numberOfStarts;
	}

	public int getNumberOfOpinions() {
		return numberOfOpinions;
	}

	public void setNumberOfOpinions(int numberOfOpinions) {
		this.numberOfOpinions = numberOfOpinions;
	}

	public int getNumberOfPositiveOpinions() {
		return numberOfPositiveOpinions;
	}

	public void setNumberOfPositiveOpinions(int numberOfPositiveOpinions) {
		this.numberOfPositiveOpinions = numberOfPositiveOpinions;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
}
