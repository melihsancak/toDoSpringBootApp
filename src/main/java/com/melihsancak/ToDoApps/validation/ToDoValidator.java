package com.melihsancak.ToDoApps.validation;

import com.melihsancak.ToDoApps.controller.TodoController;
import com.melihsancak.ToDoApps.resource.Todo;
import com.melihsancak.ToDoApps.service.TodoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class ToDoValidator {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TodoController.class);


    @Autowired
    private TodoService todoService;
    public ToDoValidator(TodoService todoService)
    {

        this.todoService = todoService ;
    }




}
