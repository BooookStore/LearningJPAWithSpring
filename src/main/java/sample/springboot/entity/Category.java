package sample.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	private String name;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
