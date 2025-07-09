import java.util.*;

public class EmployeeCRUD {
    private final List<Employee> employees = new ArrayList<>();

    public void create(Employee emp) {
        employees.add(emp);
    }

    public Employee read(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public void update(int id, String name) {
        Employee e = read(id);
        if (e != null) e.setName(name);
    }

    public void delete(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    // Display all employees
    public void listAll() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();

        // Create
        crud.create(new Employee(1, "Alice", "HR"));
        crud.create(new Employee(2, "Bob", "IT"));
        crud.create(new Employee(3, "Charlie", "Finance"));

        System.out.println("All Employees after creation:");
        crud.listAll();

        // Read
        System.out.println("\nReading employee with ID 2:");
        System.out.println(crud.read(2));

        // Update
        crud.update(2, "Bobby");
        System.out.println("\nEmployee after update:");
        System.out.println(crud.read(2));

        // Delete
        crud.delete(1);
        System.out.println("\nAll Employees after deleting ID 1:");
        crud.listAll();
    }
}
