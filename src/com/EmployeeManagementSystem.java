package com;

import java.util.*;

public class EmployeeManagementSystem {

    private ArrayList<Employee> employees;
    private HashMap<String,Employee> employeeMap;
    private Scanner scanner;

    public EmployeeManagementSystem(){

        employees = EmployeeFileHandler.loadEmployees();
        employeeMap = new HashMap<>();
        scanner = new Scanner(System.in);

        for(Employee e : employees)
            employeeMap.put(e.getId(),e);
    }

    public void start(){

        while(true){

            System.out.println("\n========== EMPLOYEE MANAGEMENT SYSTEM ==========");
            System.out.println("1 Add Employee");
            System.out.println("2 View Employees");
            System.out.println("3 Search by ID");
            System.out.println("4 Search by Name");
            System.out.println("5 Search by Department");
            System.out.println("6 Update Employee");
            System.out.println("7 Delete Employee");
            System.out.println("8 Sort by Salary");
            System.out.println("9 Sort by Name");
            System.out.println("10 Sort by Department");
            System.out.println("11 Salary Report");
            System.out.println("12 Department Report");
            System.out.println("13 Exit");

            int choice = getValidChoice();

            switch(choice){

                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: searchById(); break;
                case 4: searchByName(); break;
                case 5: searchByDepartment(); break;
                case 6: updateEmployee(); break;
                case 7: deleteEmployee(); break;
                case 8: sortBySalary(); break;
                case 9: sortByName(); break;
                case 10: sortByDepartment(); break;
                case 11: EmployeeReportGenerator.salaryStatistics(employees); break;
                case 12: EmployeeReportGenerator.departmentSummary(employees); break;
                case 13: System.out.println("Exiting..."); return;
            }
        }
    }

    private int getValidChoice(){

        while(true){

            try{
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if(choice>=1 && choice<=13)
                    return choice;

                System.out.println("Enter number 1-13.");

            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }
    }

    private String getValidName(){

        while(true){

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            if(name.matches("[a-zA-Z ]+"))
                return name;

            System.out.println("Name must contain only letters.");
        }
    }

    private double getValidSalary(){

        while(true){

            try{

                System.out.print("Enter Salary: ");
                String input = scanner.nextLine().replace(",","");

                double salary = Double.parseDouble(input);

                if(salary>0)
                    return salary;

                System.out.println("Salary must be positive.");

            }catch(Exception e){
                System.out.println("Invalid salary.");
            }
        }
    }

    private void addEmployee(){

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if(employeeMap.containsKey(id)){
            System.out.println("Employee already exists.");
            return;
        }

        String name = getValidName();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        System.out.print("Enter Position: ");
        String pos = scanner.nextLine();

        double salary = getValidSalary();

        Employee emp = new Employee(id,name,dept,pos,salary);

        employees.add(emp);
        employeeMap.put(id,emp);

        EmployeeFileHandler.saveEmployees(employees);

        System.out.println("Employee added.");
    }

    private void viewEmployees(){

        if(employees.isEmpty()){
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n-------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s %-18s %-12s %-12s\n",
                "ID","NAME","DEPARTMENT","POSITION","SALARY","JOIN DATE");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for(Employee e : employees){

            System.out.printf("%-10s %-20s %-15s %-18s %-11.2f %-12s\n",
                    e.getId(),
                    e.getName(),
                    e.getDepartment(),
                    e.getPosition(),
                    e.getSalary(),
                    e.getFormattedJoinDate());
        }

        System.out.println("-------------------------------------------------------------------------------------------------");
    }

   private void searchById(){

    System.out.print("Enter Employee ID: ");
    String id = scanner.nextLine();

    Employee emp = employeeMap.get(id);

    if(emp == null){
        System.out.println("Employee not found.");
        return;
    }

    System.out.println("\n---------------- SEARCH RESULT ----------------");

    System.out.printf("%-10s %-20s %-15s %-18s %-12s %-12s\n",
            "ID","NAME","DEPARTMENT","POSITION","SALARY","JOIN DATE");

    System.out.println("----------------------------------------------------------------------------------------");

    System.out.printf("%-10s %-20s %-15s %-18s %-11.2f %-12s\n",
            emp.getId(),
            emp.getName(),
            emp.getDepartment(),
            emp.getPosition(),
            emp.getSalary(),
            emp.getFormattedJoinDate());
}

    private void searchByName(){

    System.out.print("Enter name: ");
    String name = scanner.nextLine().toLowerCase();

    boolean found = false;

    System.out.println("\n---------------- SEARCH RESULT ----------------");

    System.out.printf("%-10s %-20s %-15s %-18s %-12s %-12s\n",
            "ID","NAME","DEPARTMENT","POSITION","SALARY","JOIN DATE");

    System.out.println("----------------------------------------------------------------------------------------");

    for(Employee e : employees){

        if(e.getName().toLowerCase().contains(name)){

            System.out.printf("%-10s %-20s %-15s %-18s %-11.2f %-12s\n",
                    e.getId(),
                    e.getName(),
                    e.getDepartment(),
                    e.getPosition(),
                    e.getSalary(),
                    e.getFormattedJoinDate());

            found = true;
        }
    }

    if(!found)
        System.out.println("No employee found.");
}

    private void searchByDepartment(){

    System.out.print("Enter department: ");
    String dept = scanner.nextLine().toLowerCase();

    boolean found = false;

    System.out.println("\n---------------- SEARCH RESULT ----------------");

    System.out.printf("%-10s %-20s %-15s %-18s %-12s %-12s\n",
            "ID","NAME","DEPARTMENT","POSITION","SALARY","JOIN DATE");

    System.out.println("-------------------------------------------------------------------------------------------");

    for(Employee e : employees){

        if(e.getDepartment().toLowerCase().contains(dept)){

            System.out.printf("%-10s %-20s %-15s %-18s %-11.2f %-12s\n",
                    e.getId(),
                    e.getName(),
                    e.getDepartment(),
                    e.getPosition(),
                    e.getSalary(),
                    e.getFormattedJoinDate());

            found = true;
        }
    }

    if(!found)
        System.out.println("No employee found.");
}

    private void updateEmployee(){

        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        Employee emp = employeeMap.get(id);

        if(emp==null){
            System.out.println("Employee not found.");
            return;
        }

        emp.setName(getValidName());

        System.out.print("Enter Department: ");
        emp.setDepartment(scanner.nextLine());

        System.out.print("Enter Position: ");
        emp.setPosition(scanner.nextLine());

        emp.setSalary(getValidSalary());

        EmployeeFileHandler.saveEmployees(employees);

        System.out.println("Employee updated.");
    }

    private void deleteEmployee(){

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        Employee emp = employeeMap.remove(id);

        if(emp!=null){

            employees.remove(emp);
            EmployeeFileHandler.saveEmployees(employees);

            System.out.println("Employee deleted.");

        } else {

            System.out.println("Employee not found.");
        }
    }

    private void sortBySalary(){

        employees.sort((a,b)->Double.compare(a.getSalary(),b.getSalary()));
        System.out.println("\nSorted by Salary");
        viewEmployees();
    }

    private void sortByName(){

        employees.sort(Comparator.comparing(Employee::getName));
        System.out.println("\nSorted by Name");
        viewEmployees();
    }

    private void sortByDepartment(){

        employees.sort(Comparator.comparing(Employee::getDepartment));
        System.out.println("\nSorted by Department");
        viewEmployees();
    }

    public static void main(String[] args){

        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.start();
    }
}