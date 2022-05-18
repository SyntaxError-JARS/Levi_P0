package com.revature.banking.daos;

import com.revature.banking.models.user;

import java.io.IOException;
import java.util.ArrayList;

public interface Crudable<T> {

    // Create
    T create(T newObject);

    // Read
    ArrayList<user> findAll() throws IOException;

    T findById(String id);

    // Update
    boolean update(T updatedObj);

    //Delete
    boolean delete(String id);

}
