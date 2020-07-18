package com.melihsancak.ToDoApps.service;

import com.melihsancak.ToDoApps.resource.Todo;
import com.melihsancak.ToDoApps.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class TodoServiceTest {


    @Mock
    private TodoRepository toDoRepisitory;

    @InjectMocks
    private TodoService todoService;

    @Autowired
    private WebApplicationContext WebApplicationContext;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void testGetAllTodo() {

        List<Todo> TodoList = new ArrayList<Todo>();
        TodoList.add(new Todo(1, "Todo Sample 1", true));
        TodoList.add(new Todo(1, "Todo Sample 2", true));
        TodoList.add(new Todo(1, "Todo Sample 3", false));

        when(toDoRepisitory.findAll()).thenReturn(TodoList);

        List<Todo> result = todoService.getAllToDo();
        assertEquals(3, result.size());


    }


    @Test
    public void testGetTodoById() {
        Todo Todo = new Todo(1l, "Todo Sample 1", true);
        when(toDoRepisitory.findOne(1L)).thenReturn(Todo);
        Todo result = todoService.getToDoById(1);
        assertEquals(1L, result.getTodoId());
        assertEquals("Todo Sample 1", result.getTodoTitle());
        assertEquals(true, result.isFinished());


    }

    @Test
    public void saveTodo() {
        Todo Todo = new Todo(1L, "Todo Sample 2", false);
        when(toDoRepisitory.save(Todo)).thenReturn(Todo);
        Todo result = todoService.saveToDo(Todo);
        assertEquals(1L, result.getTodoId());
        assertEquals("Todo Sample 2", result.getTodoTitle());
        assertEquals(false, result.isFinished());

    }


    @Test
    public void removeTodo() {
        Todo todo = new Todo(8L, "Todo Sample 3", true);
        todoService.removeToDo(todo);
        assertEquals(toDoRepisitory, times(1).verify(todo);

    }


}
