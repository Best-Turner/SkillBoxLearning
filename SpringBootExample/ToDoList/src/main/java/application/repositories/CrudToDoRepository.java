package application.repositories;

import application.models.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudToDoRepository extends CrudRepository<ToDo, Integer> {
}
