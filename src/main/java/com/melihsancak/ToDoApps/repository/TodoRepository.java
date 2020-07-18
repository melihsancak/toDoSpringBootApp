package com.melihsancak.ToDoApps.repository;


import com.melihsancak.ToDoApps.resource.Todo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


//JpaRepository extends CrudRepository.

@Repository("todoRepository")
public interface TodoRepository extends JpaRepository<Todo, Long> {


}



