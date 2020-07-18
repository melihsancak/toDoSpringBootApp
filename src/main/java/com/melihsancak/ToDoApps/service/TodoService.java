package com.melihsancak.ToDoApps.service;

import com.melihsancak.ToDoApps.exception.TodoException;
import com.melihsancak.ToDoApps.repository.TodoRepository;
import com.melihsancak.ToDoApps.resource.Todo;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TodoService {

    //overrride lar silindi adam neden service interface kurdu, ben şimdilik kurmadım gerek yok gibi şuanlık...
    //todo hepsini constructor injection yap

    private TodoRepository toDoRepository;

    public TodoService(TodoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public List<Todo> getAllToDo() {


        return toDoRepository.findAll();


    }


    public Todo getToDoById(long id) {


        if (todo == null || todo.getTodoId() <= 0) {
            throw new TodoException("Todo does not exist");//todo exceptları servicete yap
        }

        return toDoRepository.findById(id).get();

    }


    public Todo saveToDo(Todo todo) throws TodoException {
        if (todo.getTodoId() <= 0) {
            throw new TodoException("Todo to update does not exist !");
        }
        return toDoRepository.save(todo);

    }


    public void removeToDo(Todo todo) throws TodoException {

        if (todo == null || todo.getTodoId() <= 0) {
            throw new TodoException("todo to delete  does not exist");
        }
        toDoRepository.delete(todo);

    }


}
