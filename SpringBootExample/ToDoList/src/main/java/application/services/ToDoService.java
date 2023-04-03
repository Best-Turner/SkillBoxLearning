package application.services;

import application.models.ToDo;
import application.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(@Qualifier("simpleImplementation") ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public void add(ToDo toDo) {
        toDoRepository.add(toDo);
    }

    @Transactional(readOnly = true)
    public ToDo getById(int id) {
        return toDoRepository.getById(id);
    }

    public void deleteById(int id) {
        toDoRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<ToDo> getAllToDoList() {
        return toDoRepository.getAllToDoList();
    }

    public void edit(int id, ToDo updatedToDo) {
        updatedToDo.setId(id);
        toDoRepository.update(updatedToDo);
    }
}
