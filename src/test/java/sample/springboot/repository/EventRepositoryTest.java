package sample.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sample.springboot.entity.Category;
import sample.springboot.entity.Event;

/**
 * {@link EventRepository}の使用例を表すテストケースクラス。
 * 
 * @see EventRepository
 * @author bookstore
 * @since 2017/6/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

	/** イベントリポジトリクラス */
	@Autowired
	private EventRepository eventReository;

	/** カテゴリリポジトリクラス */
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * テスト実行前に初期データをデータベースへ登録。
	 */
	@Before
	public void setUp() {
		eventReository.deleteAll();
		categoryRepository.deleteAll();

		// カテゴリーを登録
		Category music = new Category("Music");
		Category race = new Category("Race");

		categoryRepository.save(music);
		categoryRepository.save(race);

		// イベントを登録
		Event event001 = new Event("Perfume", new Date(), music);
		Event event002 = new Event("Kaihimacuhari", new Date(), race);

		eventReository.save(event001);
		eventReository.save(event002);
	}

	/**
	 * イベント名からイベントエンティティオブジェクトを取得する。
	 */
	@Test
	public void testFindEventByTitle() {

		List<Event> result = eventReository.findEventByTitle("Perfume");

		// 検証
		assertThat(result.size()).isEqualTo(1);
		assertThat(result).extracting("title").containsOnly("Perfume");
	}

	/**
	 * カテゴリーからイベントエンティティを取得する。
	 */
	@Test
	public void testFindEventByCategory() {

		Iterable<Category> categories = categoryRepository.findAll();
		Category category = categories.iterator().next();

		// 検証
		List<Event> result = eventReository.findEventByCategory(category);
		assertThat(result)
				.extracting(e -> e.getCategory().getName())
				.containsOnly(category.getName());
	}

	/**
	 * フェッチしたオブジェクトを変更しても、データベースへは登録されない。
	 * フェッチしたオブジェクトを変更し、再度登録するとデータベースが更新される。
	 */
	@Test
	public void testFetchedObjectModify() {
		
		// タイトルが Perfume のイベントを取得し、タイトルを 赤い公園 へ変更
		Event perfumeEvent = eventReository.findEventByTitle("Perfume").get(0);
		perfumeEvent.setTitle("赤い公園");

		// DBへ登録していないため、DBは変更なし
		assertThat(eventReository.findEventByTitle("Perfume").get(0)).isNotNull();
		assertThat(eventReository.findEventByTitle("赤い公園").size()).isEqualTo(0);
		
		// イベントタイトルを変更
		perfumeEvent.setTitle("赤い公園");
		
		// DBへ登録したため、DBが更新される。
		eventReository.save(perfumeEvent);
		assertThat(eventReository.findEventByTitle("Perfume").size()).isEqualTo(0);
		assertThat(eventReository.findEventByTitle("赤い公園").get(0)).isNotNull();
	}

}
