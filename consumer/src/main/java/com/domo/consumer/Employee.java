package com.domo.consumer;

/**
 * The type Employee.
 */
public class Employee {
    /**
     * The Name.
     */
    String name;
    /**
     * The Id.
     */
    int id;

    /**
     * Instantiates a new Employee.
     */
    public Employee(){

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets next id.
     *
     * @return the next id
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Sets next id.
     *
     * @param nextId the next id
     */
    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }

    /**
     * The Next id.
     */
    static int nextId = 0;

    /**
     * Instantiates a new Employee.
     *
     * @param name the name
     */
    public Employee(String name){
        this.id = nextId++;
        this.name = name;
    }
}
