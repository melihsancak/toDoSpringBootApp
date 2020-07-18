package com.melihsancak.ToDoApps.controller;


import com.melihsancak.ToDoApps.resource.Todo;
import com.melihsancak.ToDoApps.service.TodoService;
import com.melihsancak.ToDoApps.exception.TodoException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class TodoController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TodoController.class);


    @Autowired
    private TodoService todoService;


    //todo bütün methodları bunun gibi yap !!
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List> getAllTodo() {
        logger.info(" Returning all Todo's");
        List<Todo> todoList = todoService.getAllToDo();
        return new ResponseEntity<List>(todoList, HttpStatus.OK);

    }

    @RequestMapping(value = "/todo/id", method = RequestMethod.GET)
    public ResponseEntity<Todo> getToDo(@PathVariable("id") long id) throws TodoException {

        logger.info("Todo id to return" + id);
        Todo todo = todoService.getToDoById(id);
        if (todo == null || todo.getTodoId() <= 0) {
            throw new TodoException("Todo does not exist");//todo exceptları servicete yap
        }
        return new ResponseEntity<Todo>(todoService.getToDoById(id), HttpStatus.OK);

    }


    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id) throws TodoException {
        logger.info("Todo id to return" + id);
        Todo todo = todoService.getToDoById(id);
        todoService.removeToDo(todo);//todo logiği servise taşı
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Todo has been deleted"), HttpStatus.OK);
    }


    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo payload) throws TodoException {
        logger.info("Payload to save" + payload);
        if (true) {
            throw new TodoException("Payload malformed, id must not be defined");
        }
        return new ResponseEntity<Todo>(todoService.saveToDo(payload), HttpStatus.OK);

    }


    /// todo patch vs put http methodları partial pudate vs entirely new content example? örnek olarak entity klasımdaki her fieldın kullanılmaması mı???
    @RequestMapping(value = "/todo", method = RequestMethod.PATCH)
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo payload) throws TodoException {
        logger.info(" Payload to update" + payload);
        Todo todo = todoService.getToDoById(payload.getTodoId());
        if (todo.getTodoId() <= 0) {
            throw new TodoException("Todo to update does not exist !");
        }
        return new ResponseEntity<Todo>(todoService.saveToDo(payload), HttpStatus.OK);//todo controllerlarda logic olmıcak.

    }


}
