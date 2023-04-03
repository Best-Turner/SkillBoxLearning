package application.controllers;

import application.models.ToDo;
import application.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    private final ToDoService toDoService;

    @Autowired
    public MainController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("allTask", toDoService.getAllToDoList());
        return "index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("detailsAboutTask", toDoService.getById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("emptyToDo") ToDo toDo) {
        return "new";
    }

    @PostMapping
    public String addTask(@ModelAttribute("completedTask") ToDo toDo) {
        toDoService.add(toDo);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        toDoService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String needToChange(@PathVariable("id") int id,
                               Model model) {
        model.addAttribute("needToChange", toDoService.getById(id));
        return "edit";
    }

    @PatchMapping("{id}")
    public String doUpdate(@PathVariable("id") int id,
                           @ModelAttribute("updatedTask") ToDo updatedTask) {
        toDoService.edit(id, updatedTask);
        return "redirect:/";
    }
}
