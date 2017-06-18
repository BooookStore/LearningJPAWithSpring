package sample.springboot.repository;

import java.util.Date;
import java.util.List;

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
	}

}
