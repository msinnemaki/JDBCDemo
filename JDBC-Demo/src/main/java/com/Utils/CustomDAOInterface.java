package com.Utils;

public interface CustomDAOInterface <T>
{
    //CRUD =
    //All basic functionality that we should be able
    //to do when storing data

    //here we are returning an Integer
    //and expecting a param of type T
    Integer create(T t);

    //return type of 'T'
    //amd os expecting a param of type Integer
    T read(Integer id);

    //return type of T
    //also expecting a parent of type T
    T update(T t);

    //return type is boolean
    //expecting an Integer param
    boolean delete(Integer id);

}
