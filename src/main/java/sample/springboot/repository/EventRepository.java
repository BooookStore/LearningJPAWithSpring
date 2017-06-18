package sample.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sample.springboot.entity.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {
	
	public List<Event> findEventByTitle(String title);

}
