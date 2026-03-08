package com;

import java.io.*;
import java.util.ArrayList;

public class EmployeeFileHandler {

    private static final String FILE_NAME = "data/employees.dat";

    public static void saveEmployees(ArrayList<Employee> employees) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(employees);
            System.out.println("Data saved.");

        } catch (IOException e) {

            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadEmployees() {

        ArrayList<Employee> employees = new ArrayList<>();

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            employees = (ArrayList<Employee>) ois.readObject();
            System.out.println("Employees loaded from file.");

        } catch (FileNotFoundException e) {

            System.out.println("No previous employee data found.");

        } catch (Exception e) {

            System.out.println("Error loading data: " + e.getMessage());
        }

        return employees;
    }
}