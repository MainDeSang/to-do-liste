package com.example.to_do_liste;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.model.Todo.Status;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Optional;

// Zur Info:
// Diese Klasse habe ich vorübergehend erstellt, sie testet ob Daten in der Datenbank gespeichert werden
// Kann später wieder gelöscht werden. Wenn später das Backend steht (ohne Frontend) kann man mit 'Postman' Daten hinzufügen und bearbeiten.

@Component
public class DataSeeder implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final TodoRepository todoRepository;

    public DataSeeder(PersonRepository personRepository, TodoRepository todoRepository) {
        this.personRepository = personRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.findByUsername("testuser").isEmpty()) {
            Person p = new Person();
            p.setUsername("testuser");
            p.setPassword("testpasswort123");

            personRepository.save(p);
            System.out.println("***** Test-Person wurde gespeichert. *****");
        } else {
            System.out.println("***** Test-Person existiert bereits. *****");
        }
        Optional<Person> person = personRepository.findByUsername("testuser");
        if (todoRepository.count() == 0) {
            Todo todo1 = Todo.builder()
                    .title("Erstes Todo")
                    .description("Das Todo gehört testuser")
                    .startDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now().plusDays(2))
                    .status(Status.TODO)
                    .owner(person.orElseThrow(() -> new RuntimeException("person nicht gefunden")))
                    .build();

            Todo todo2 = Todo.builder()
                    .title("Zweites Todo")
                    .description("Noch eins für dieselbe Person")
                    .startDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now().plusDays(3))
                    .status(Status.DOING)
                    .owner(person.orElseThrow(() -> new RuntimeException("person nicht gefunden")))
                    .build();

            todoRepository.save(todo1);
            todoRepository.save(todo2);

            System.out.println("***** Test-Todos wurden gespeichert. *****");
        } else {
            System.out.println("***** Test-Todos existieren bereits. *****");
        }
    }
}
