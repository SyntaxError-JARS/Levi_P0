package com.revature.banking.daos;

import com.revature.banking.models.account;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Crudable<T> {

    // Create
    T create(T newObject);

    // Read
    T[] findAll() throws IOException;

    T findById(String id);

    // Update
    boolean update(T updatedObj);

    //Delete
    boolean delete(String id);

}
