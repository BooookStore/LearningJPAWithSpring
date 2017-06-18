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

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventReository;

	@Autowired
	private CategoryRepository categoryRepository;

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

		// イベントを取り出す
		Iterable<Event> events = eventReository.findAll();
		events.forEach(System.out::println);
	}

	@Test
	public void testFindEventByTitle() {
		List<Event> result = eventReository.findEventByTitle("Perfume");
		result.forEach(System.out::println);
		List<Event> result2 = eventReository.findEventByTitle(null);
	}

}
