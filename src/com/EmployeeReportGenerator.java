package com;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeReportGenerator {

    public static void salaryStatistics(ArrayList<Employee> employees){

    if(employees.isEmpty()){
        System.out.println("No employees available.");
        return;
    }

    double total = 0;

    double maxSalary = employees.get(0).getSalary();
    double minSalary = employees.get(0).getSalary();

    ArrayList<Employee> highestEmployees = new ArrayList<>();
    ArrayList<Employee> lowestEmployees = new ArrayList<>();

    // Calculate total, max, min
    for(Employee e : employees){

        total += e.getSalary();

        if(e.getSalary() > maxSalary){
            maxSalary = e.getSalary();
        }

        if(e.getSalary() < minSalary){
            minSalary = e.getSalary();
        }
    }

    // Find all employees with max and min salaries
    for(Employee e : employees){

        if(e.getSalary() == maxSalary){
            highestEmployees.add(e);
        }

        if(e.getSalary() == minSalary){
            lowestEmployees.add(e);
        }
    }

    double avg = total / employees.size();

    System.out.println("\n-------------- SALARY REPORT --------------");

    System.out.printf("%-25s %-20s\n","Metric","Value");
    System.out.println("-------------------------------------------");

    System.out.printf("%-25s %-20d\n","Total Employees",employees.size());
    System.out.printf("%-25s %-19.2f\n","Total Salary",total);
    System.out.printf("%-25s %-19.2f\n","Average Salary",avg);

    // Highest salary names
    System.out.print("Highest Salary            " + String.format("%.2f",maxSalary) + " (");

    for(int i=0;i<highestEmployees.size();i++){

        System.out.print(highestEmployees.get(i).getName());

        if(i < highestEmployees.size()-1)
            System.out.print(", ");
    }

    System.out.println(")");

    // Lowest salary names
    System.out.print("Lowest Salary             " + String.format("%.2f",minSalary) + " (");

    for(int i=0;i<lowestEmployees.size();i++){

        System.out.print(lowestEmployees.get(i).getName());

        if(i < lowestEmployees.size()-1)
            System.out.print(", ");
    }

    System.out.println(")");

    System.out.println("-------------------------------------------");
}

    public static void departmentSummary(ArrayList<Employee> employees){

        HashMap<String,Integer> count = new HashMap<>();
        HashMap<String,Double> totalSalary = new HashMap<>();

        for(Employee e : employees){

            count.put(e.getDepartment(),
                    count.getOrDefault(e.getDepartment(),0)+1);

            totalSalary.put(e.getDepartment(),
                    totalSalary.getOrDefault(e.getDepartment(),0.0)+e.getSalary());
        }

        System.out.println("\n----------- DEPARTMENT REPORT -----------");
        System.out.printf("%-20s %-15s %-15s\n","DEPARTMENT","EMPLOYEES","TOTAL SALARY");
        System.out.println("-----------------------------------------");

        for(String dept : count.keySet()){

            System.out.printf("%-20s %-15d %-14.2f\n",
                    dept,
                    count.get(dept),
                    totalSalary.get(dept));
        }

        System.out.println("-----------------------------------------");
    }
}