package se.lexicon.todoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    @Lob
    @Column(length = 10485760) // Specify the maximum size of the LOB in bytes (10 MB)
    private byte[] data; // Store the file content

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Todo todo;

    public void setTodo(Todo todo) {
        this.todo = todo;
        // Sync the other side if not already present
        if (todo != null) {
            todo.getAttachments().add(this);
        }
    }
}
