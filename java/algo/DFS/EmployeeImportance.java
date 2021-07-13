package algo.DFS;

import java.util.*;

/**
 * You have a data structure of employee information, which includes the employee's unique id, their
 * importance value, and their direct subordinates' id.
 *
 * You are given an array of employees employees where:
 *
 * - employees[i].id is the ID of the ith employee.
 * - employees[i].importance is the importance value of the ith employee.
 * - employees[i].subordinates is a list of the IDs of the subordinates of the ith employee.
 *
 * Given an integer id that represents the ID of an employee, return the total importance value
 * of this employee and all their subordinates.
 *
 * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
 * Output: 11
 * Explanation: Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
 * They both have importance value 3.
 * So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 *
 * Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
 * Output: -3q
 */
public class EmployeeImportance {

    Map<Integer, Employee> employeeMap;

    public int getImportance(List<Employee> employees, int id) {
        employeeMap = new HashMap<>();
        // create a map of employee id and employee object
        for(Employee emp: employees) {
            employeeMap.put(emp.id, emp);
        }
        return helper(id);
    }

    public int helper(Integer employeeId) {
        Employee emp = employeeMap.get(employeeId);
        int totalImportance = emp.importance;

        for(int e: emp.subordinates) {
            totalImportance += helper(e);
        }
        return totalImportance;
    }

    public int getImportanceIterative(List<Employee> employees, int id) {
        int totalImportance = 0;

        Map<Integer, Employee> employeeMap = new HashMap<>();
        // create a map of employee id and employee object
        for(Employee emp: employees) {
            employeeMap.put(emp.id, emp);
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.push(id);

        while(!queue.isEmpty()) {
            int empId = queue.pop();
            Employee emp = employeeMap.get(empId);
            totalImportance += emp.importance;
            for(Integer e: emp.subordinates) {
                queue.push(e);
            }
        }
        return totalImportance;
    }


    public static void main(String args[]) {
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee(1, 5);
        Employee e2 = new Employee(2, 3);
        Employee e3 = new Employee(3, 3);
        e1.subordinates.add(2);
        e1.subordinates.add(3);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        EmployeeImportance obj = new EmployeeImportance();
        System.out.println(obj.getImportance(employees, 1));
    }
}
