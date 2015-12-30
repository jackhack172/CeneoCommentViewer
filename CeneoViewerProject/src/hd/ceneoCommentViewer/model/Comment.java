package hd.ceneoCommentViewer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment implements Serializable {

	private static final long serialVersionUID = -5314336457766591759L;

	private Integer id;

	private List<String> advantages;

	private List<String> disadvantages;

	private String summary;

	private Integer stars;

	private String author;

	private Date date;

	private Boolean recommended;

	private Integer numberOfPositiveOpinions;

	private Integer numberOfOpinions;

	public Comment() {

	}

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ElementCollection
	public List<String> getAdvantages() {
		return advantages;
	}

	public void setAdvantages(List<String> advantages) {
		this.advantages = advantages;
	}

	@ElementCollection
	public List<String> getDisadvantages() {
		return disadvantages;
	}

	public void setDisadvantages(List<String> disadvantages) {
		this.disadvantages = disadvantages;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public Integer getNumberOfPositiveOpinions() {
		return numberOfPositiveOpinions;
	}

	public void setNumberOfPositiveOpinions(Integer numberOfPositiveOpinions) {
		this.numberOfPositiveOpinions = numberOfPositiveOpinions;
	}

	public Integer getNumberOfOpinions() {
		return numberOfOpinions;
	}

	public void setNumberOfOpinions(Integer numberOfOpinions) {
		this.numberOfOpinions = numberOfOpinions;
	}
}
