package application.repositories;

import application.models.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("simpleImplementation")
public class ToDoRepositoryImp implements ToDoRepository {

    private final CrudToDoRepository repository;

    public ToDoRepositoryImp(CrudToDoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void add(ToDo toDo) {
        repository.save(toDo);
    }

    @Override
    public void update(ToDo toDo) {
        repository.save(toDo);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public ToDo getById(int id) {
        Optional<ToDo> byId = repository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<ToDo> getAllToDoList() {
        List<ToDo> allTask = new ArrayList<>();
        Iterable<ToDo> all = repository.findAll();
        all.forEach(allTask::add);
        return allTask;
    }
}
