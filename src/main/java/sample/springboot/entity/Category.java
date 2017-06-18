package sample.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * カテゴリーを表すエンティティクラス
 * 
 * @author bookstore
 * @since 2017/6/18
 */
@Entity
@Table(name = "Category")
public class Category {

	/** デフォルトコンストラクタ */
	public Category() {
	}

	/** カテゴリ名を指定してインスタンスを作成 */
	public Category(String name) {
		super();
		this.name = name;
	}

	/** カテゴリーのプライマリーキー */
	@Id
	@GeneratedValue(generator = "increment") // プライマリーキーの自動生成方法を指定
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	/** カテゴリ名 */
	private String name;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
