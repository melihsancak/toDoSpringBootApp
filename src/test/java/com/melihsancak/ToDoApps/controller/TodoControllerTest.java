package com.melihsancak.ToDoApps.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class TodoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContextRunner webApplicationContextRunner;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilder.webAppContextSetup(webApplicationContextRunner).build();


    }

    @Test
    public void verifyAllToByList() throws Exception {
        mockMvc.perform(MockMvcBuilder.get("/todo")).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id".hasSize(4)).andDo(print());


    }


    @Test
    public void verifyToDoById() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.text").exists("Easy checkout"))
                .andExpect(jsonPath("$.completed").exists(false))
                .andDo(print());

    }


    //üstekkyle aynı test!!

    @Test
    public void verifyToDoById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.text").exists(" Learning Spring is so good !"))
                .andExpect(jsonPath("$.completed").exists(false))
                .andDo(print());


    }

    @Test
    public void verifyInvalidToDoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/f").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("Todo does not exist"))
                .andDo(print());


    }


    @Test
    public void verifyInvalidToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/todo/-1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Todo does not exist"))
                .andDo(print());

    }


    @Test
    public void verifyNullToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/todo/6").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Todo does not exist"))
                .andDo(print());


    }

    @Test
    public void verifyDeleteToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(200))
                .andExpect(jsonPath("$.message").value("Todo has been deleted"))
                .andDo(print());


    }


    @Test
    public void verifyInvalidToDoIdToDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/6").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("todo to delete  does not exist"))
                .andDo(print());


    }


    //content  kısmı yok??
    @Test
    public void verifySaveToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/todo/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MediaType.APPLICATION_JSON)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.text").exists(" İyzico rocks for new Todo App!"))
                .andExpect(jsonPath("$.completed").exists(false))
                .andDo(print());


    }


    @Test
    public void verifyMalformedSaveToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/todo/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))


    }

    @Test
    public void verifyUpdateToDo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/todo/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.text").value("New Todo text"))
                .andExpect(jsonPath("$.completed").value(false))
                .andDo(print());


    }

    @Test
    public void verifyInvalidToDoUpdate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/todo/").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Todo to update does not exist"))


    }

}
