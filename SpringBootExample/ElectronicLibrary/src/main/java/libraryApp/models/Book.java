package libraryApp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 1, max = 30, message = "Название книги должно быть в пределах от 1 до 30 символов")
    private String title;
    @Column(name = "autor")
    @NotEmpty(message = "У каждой книги должен быть автор!")
    private String author;
    //@NotEmpty(message = "Обязательно укажите год книги!")
    @Column(name = "year")
    @Min(value = 1900, message = "Книга должна быть не старше 1900 года")
    private int year;


    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owningPerson;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwningPerson() {
        return owningPerson;
    }

    public void setOwningPerson(Person owningPerson) {
        this.owningPerson = owningPerson;
    }
}
