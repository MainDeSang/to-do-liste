package com.example.to_do_liste.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.to_do_liste.dto.TodoDto;
import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.ProjectRepository;
import com.example.to_do_liste.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TodoServiceTest {
                                    // @Mock - das getestete soll sich verhalten wie echt, ohne echt zu sein
    @Mock                           // Mock erstellt für das Repository eine Test-Attrappe, damit man nicht die echte Datenbank benötigt
    TodoRepository todoRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks                    // Baut ein echtes TodoService Objekt mit den Mocks als Abhängigkeiten
    TodoService todoService;

    @Test
    void testGetAllNotDeleted() {
        // drei To-dos erstellen, eines davon ist gelöscht
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Erstes aktives Todo");
        todo1.setDeletedAt(null);

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Zweites aktives Todo");
        todo2.setDeletedAt(null);

        Todo todo3 = new Todo();
        todo3.setId(3L);
        todo3.setTitle("Gelöschtes Todo");
        todo3.setDeletedAt(LocalDateTime.now());

        // Repository zeigt uns alle drei To-dos:
        // Mock tut so, als ob das die DB-Ergebnisse wären
        Mockito.when(todoRepository.findAll())
                .thenReturn(List.of(todo1, todo2, todo3));

        // Methode aufrufen
        List<Todo> result = todoService.getAllNotDeleted();

        // Test: nur die todo1 und todo2 sollen angezeigt werden:
        assertEquals(2, result.size());         // Behauptung gleicht: Ergebnisliste soll Größe = 2 haben
        assertTrue(result.contains(todo1));              // Behauptung richtig: Liste result enthält todo1
        assertTrue(result.contains(todo2));              // Behauptung richtig: Liste result enthält todo2
        assertFalse(result.contains(todo3));             // Behauptung falsch: Liste result enthält todo3
    }

    @Test
    void returnOnlyDeletedTodos_noError() {
        // drei To-dos erstellen, eines davon ist gelöscht
        Todo todo1 = new Todo();
        todo1.setTitle("Erstes aktives Todo");
        todo1.setDeletedAt(null);

        Todo todo2 = new Todo();
        todo2.setTitle("Zweites aktives Todo");
        todo2.setDeletedAt(null);

        Todo todo3 = new Todo();
        todo3.setTitle("Gelöschtes Todo");
        todo3.setDeletedAt(LocalDateTime.now());

        // Repository zeigt uns alle drei To-dos:
        // Mock tut so, als ob das die DB-Ergebnisse wären
        Mockito.when(todoRepository.findAll())
                .thenReturn(List.of(todo1, todo2, todo3));

        // Methode aufrufen
        List<Todo> result = todoService.getAllDeleted();

        assertEquals(1, result.size());        // Behauptung gleicht: Ergebnisliste soll Größe = 1 haben
        assertFalse(result.contains(todo1));            // Behauptung falsch: Liste result enthält todo1
        assertFalse(result.contains(todo2));            // Behauptung falsch: Liste result enthält todo2
        assertTrue(result.contains(todo3));             // Behauptung richtig: Liste result enthält todo3
    }

    @Test
    void createNewTodo_noError() {
        // To-do erstellen:
        TodoDto todoDto = new TodoDto();
        todoDto.title = "Test-Todo";
        todoDto.description = "Test-Beschreibung";
        todoDto.startDate = LocalDateTime.now();
        todoDto.endDate = LocalDateTime.now().plusDays(1);
        todoDto.status = "TODO";
        todoDto.ownerId = 1L;
        todoDto.projectId = 2L;

        // Person mit id = 1 mocken:
        Person mockedPerson = new Person();
        mockedPerson.setId(1L);

        // Projekt mit id = 2 mocken:
        Project mockedProject = new Project();
        mockedProject.setId(2L);

        Mockito.when(personRepository.findById(1L))         // wenn Mockito die Methode findById(1L) aufruft,
                .thenReturn(Optional.of(mockedPerson));     // dann gibt er die Person zurück die als mockedPerson vorbereitet wurde
        Mockito.when(projectRepository.findById(2L))        // wenn Mockito die Methode findById(2L) aufruft,
                .thenReturn(Optional.of(mockedProject));    // dann gibt er das Projekt zurück, das als mockedProject vorbereitet wurde
        Mockito.when(todoRepository.save(Mockito.any(Todo.class)))          // wenn Mockito die save(egal welches To-do) aufruft:
                .thenAnswer(invocation -> invocation.getArgument(0));       // invocation = Aufruf; antwortet mit dem ersten Argument, das übergeben wurde

        // Methode createTodo() ausführen
        Todo result = todoService.createTodo(todoDto);

        // Das Ergebnis sollte nicht null sein
        assertNotNull(result);

        // Die Felder des erstellten To-dos sollten korrekt gesetzt sein
        assertEquals("Test-Todo", result.getTitle());
        assertEquals("Beschreibung", result.getDescription());
        assertEquals(Todo.Status.TODO, result.getStatus());
        assertEquals(mockedPerson, result.getOwner());
        assertEquals(mockedProject, result.getProject());
        assertEquals(todoDto.startDate, result.getStartDate());
        assertEquals(todoDto.endDate, result.getEndDate());

        // Sicherstellen, dass todoRepository.save() aufgerufen wurde
        Mockito.verify(todoRepository).save(Mockito.any(Todo.class));
    }

    @Test
    void shouldThrowExceptionWhenPersonNotFound() {
        // dto erstellen
        TodoDto todoDto = new TodoDto();
        todoDto.title = "Fehlerfall-Todo";
        todoDto.description = "Test ohne gültige Person";
        todoDto.status = "TODO";
        todoDto.ownerId = 999L; // Diese ID wird es nicht geben

        // Person-Repository gibt leeres Ergebnis zurück (Person nicht gefunden)
        Mockito.when(personRepository.findById(999L)).thenReturn(Optional.empty());

        // Methode ausführen und auf Exception prüfen
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            todoService.createTodo(todoDto);
        });

        // Text der Exception prüfen
        assertEquals("Person nicht gefunden", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProjectNotFound() {
        // Eingabe-Daten
        TodoDto todoDto = new TodoDto();
        todoDto.title = "Mit ungültigem Projekt";
        todoDto.description = "Fehlertest";
        todoDto.status = "TODO";
        todoDto.ownerId = 1L;
        todoDto.projectId = 99L;

        // Gültige Person wird zurückgegeben
        Person mockedPerson = new Person();
        mockedPerson.setId(1L);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(mockedPerson));

        // Projekt wird nicht gefunden
        Mockito.when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        // Methode aufrufen und prüfen, ob Exception geworfen wird
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {       // assertThrows: es wird erwartet, dass ein Fehler kommt
            todoService.createTodo(todoDto);
        });

        // Prüfung des Fehlertexts
        assertEquals("Projekt nicht gefunden", exception.getMessage());
    }


}
