package libraryApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 32, message = "Имя должно быть в пределах от 2 до 32 символов")
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 32, message = "Фамилия должна быть в пределах от 2 до 32 символов")
    private String surname;
    @Column(name = "patronymic")
    @NotEmpty(message = "Отчество не может быть пустым")
    @Size(min = 2, max = 32, message = "Отчество должно быть в пределах от 2 до 32 символов")
    private String patronymic;
    @Column(name = "year_of_birth")
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    private int year;

    @OneToMany(mappedBy = "owningPerson", fetch = FetchType.EAGER)
    private List<Book> books;

    public Person() {
    }

    public Person(String name, String surname, String patronymic, int year) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.year = year;
        books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYear() {
        return year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
