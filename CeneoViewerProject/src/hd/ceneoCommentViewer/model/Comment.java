package hd.ceneoCommentViewer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

/**
 * 
 *	Klasa reprezentujaca encje komentarz.
 *
 */
@Entity
public class Comment implements Serializable {

	private static final long serialVersionUID = -5314336457766591759L;
	
	/**
	 * Id komentarza.
	 */
	private CommentId id;

	/**
	 * Zalety produktu opisanego w komentarzu.
	 */
	private List<String> advantages;

	/**
	 * Wady produktu opisanego w komentarzu.
	 */
	private List<String> disadvantages;

	/**
	 * Podsumowanie.
	 */
	private String summary;

	/**
	 * Ocena produktu opisanego w komentarzu.
	 */
	private Double stars;

	/**
	 * Autor komentarza.
	 */
	private String author;

	/**
	 * Data dodania komentarza.
	 */
	private Date date;

	/**
	 * Informacja czy produktu opisanego w komentarzu jest polecany.
	 */
	private Boolean recommended;

	/**
	 * Liczba pozytywnych opinii o komentarzu.
	 */
	private Integer numberOfPositiveOpinions;

	/**
	 * Liczba opinni o komentarzu.
	 */
	private Integer numberOfOpinions;

	/**
	 * Konstruktor.
	 */
	public Comment() {
	}

	@EmbeddedId
	public CommentId getId(){
		return id;
	}
	
	public void setId(CommentId id){
		this.id = id;
	}

	@ElementCollection
	@Type(type = "text")
	public List<String> getAdvantages() {
		return advantages;
	}

	public void setAdvantages(List<String> advantages) {
		this.advantages = advantages;
	}

	@ElementCollection
	@Type(type = "text")
	public List<String> getDisadvantages() {
		return disadvantages;
	}

	public void setDisadvantages(List<String> disadvantages) {
		this.disadvantages = disadvantages;
	}

	@Type(type = "text")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
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
