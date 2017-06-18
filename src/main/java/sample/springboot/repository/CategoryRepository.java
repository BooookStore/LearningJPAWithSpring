package sample.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import sample.springboot.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
