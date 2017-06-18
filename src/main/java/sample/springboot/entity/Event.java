package sample.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "event")
public class Event {

	/** デフォルトコンストラクタ */
	public Event() {
	}

	/** イベント名とタイトルを指定してインスタンスを作成 */
	public Event(String title, Date date) {
		// for application use, to create new events
		this.title = title;
		this.date = date;
	}
	
	/** イベント名、タイトル、カテゴリーを指定してインスタンスを作成 */
	public Event(String title, Date date, Category category) {
		this(title, date);
		this.category = category;
	}

	private Long id;

	private String title;
	
	private Date date;

	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "Category_Id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"))
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENT_DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", date=" + date + ", category=" + category + "]";
	}

	
}