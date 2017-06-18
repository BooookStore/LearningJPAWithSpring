package sample.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sample.springboot.entity.Category;
import sample.springboot.entity.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {
	
	/** イベントタイトルから、イベントエンティティを取得 */
	public List<Event> findEventByTitle(String title);

	public List<Event> findEventByCategory(Category category);
}
