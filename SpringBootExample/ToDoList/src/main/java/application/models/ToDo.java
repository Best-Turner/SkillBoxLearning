package application.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "to_do_list")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "is_done")
    private boolean done;

    public ToDo(String description) {
        this.description = description;
    }

}
