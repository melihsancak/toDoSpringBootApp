package com.melihsancak.ToDoApps;

import com.melihsancak.ToDoApps.resource.Todo;
import com.melihsancak.ToDoApps.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ToDoAppsApplication {

    private static final Logger logger = LoggerFactory.getLogger(ToDoAppsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ToDoAppsApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(TodoRepository todoRepository) {

        return (args) -> {
            Todo todo1 = new Todo();
            todo1.setTodoTitle(" More techno ");
            todo1.setFinished(true);
            todo1.setCreatedAt(new Date());
            todoRepository.save(todo1);


           // todoRepository.save(new Todo(" Easy checkout ", true));
          //  todoRepository.save(new Todo(" Learning Spring is so good !", false));
          //  todoRepository.save(new Todo(" Should i create bean or not? ", true));


        };
    }
}
