package com.melihsancak.ToDoApps.resource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "bank")
public class Todo implements Serializable {

    // todo convert to id, bizdeki herngai bir entity gibi id sistematiğiyle yap
    //todo bunu requestten alırken yap, bizdeki entity gibi yap bütün fiedları


    @Id
    @Column(name = "id")
    private long todoId;
    @Column(name = "name", nullable = false)
    private String todoTitle;

    @Column(name = "finished")
    private boolean finished;

    @Column(name = "create_date", nullable = false)
    private Date createdAt;


    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


}
