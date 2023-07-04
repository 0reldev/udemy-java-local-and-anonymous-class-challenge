package dev.lpa;

import dev.lpa.domain.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Employee e1 = new Employee("Michael", "Jordan", "04/07/2023");
        Employee e2 = new Employee("John", "Wick", "03/07/2023");
        Employee e3 = new Employee("Indiana", "Jones", "02/07/1978");
        Employee e4 = new Employee("Donald", "Duck", "02/07/1943");
        Employee e5 = new Employee("Barbie", "Girl", "02/07/1940");

        List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

        printOrderedList(list, "name");
//        Barbie Girl has been an employee for 83 years
//        Donald Duck has been an employee for 80 years
//        Indiana Jones has been an employee for 45 years
//        John Wick has been an employee for 0 years
//        Michael Jordan has been an employee for 0 years

        printOrderedList(list, "year");
//        Michael Jordan has been an employee for 0 years
//        John Wick has been an employee for 0 years
//        Indiana Jones has been an employee for 45 years
//        Donald Duck has been an employee for 80 years
//        Barbie Girl has been an employee for 83 years
    }

    public static void printOrderedList(List<Employee> eList, String sortField) {

        int currentYear = LocalDate.now().getYear();

        class MyEmployee {
            Employee containedEmployee;
            int yearsWorked;
            String fullName;

            public MyEmployee(Employee containedEmployee) {
                this.containedEmployee = containedEmployee;
                yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);
                fullName = String.join(" ", containedEmployee.firstName(), containedEmployee.lastName());
            }

            @Override
            public String toString() {
                return "%s has been an employee for %d years".formatted(fullName, yearsWorked);
            }
        }
        List<MyEmployee> list = new ArrayList<>();
        for (Employee employee : eList) {
            list.add(new MyEmployee(employee));
        }

        var comparator = new Comparator<MyEmployee>() {
            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {
                if (sortField.equals("name")) {
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

        list.sort(comparator);

        for (MyEmployee myEmployee : list) {
            System.out.println(myEmployee);
        }
    }
}
