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

/**
 * イベントを表すエンティティクラス。
 * 
 * @author bookstore
 * @since 2017/6/18
 */
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

	/** イベントのプライマリーキー。 */
	@Id
	@GeneratedValue // TODO : 値を自動的に生成。生成方法の指定方法はわからず。
	private Long id;

	/** イベントのタイトル。 */
	private String title;

	/** イベントの日時。 */
	@Temporal(TemporalType.TIMESTAMP) // TODO :
										// タイムスタンプ型を指定。これを指定すると、何が変わるんだろう？他にどんな指定ができるだろう？
	@Column(name = "EVENT_DATE") // 列名にDateは指定できないので、別の名前を列名に指定。
	private Date date;

	/** イベントのカテゴリ。 */
	@ManyToOne // 多対一であることを指定
	@JoinColumn(name = "Category_Id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK")) // TODO
																							// :
																							// 他テーブルの参照設定。これを書くことによる影響は？
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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