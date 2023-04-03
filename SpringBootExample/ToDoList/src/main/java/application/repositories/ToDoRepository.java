package application.repositories;

import application.models.ToDo;

import java.util.List;

public interface ToDoRepository {

    void add(ToDo toDo);

    void update(ToDo toDo);

    void delete(int id);

    ToDo getById(int id);

    List<ToDo> getAllToDoList();
}
