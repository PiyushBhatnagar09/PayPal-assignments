import java.sql.*;

public class EmployeeJDBC {
    private Connection conn;

    public EmployeeJDBC() throws Exception {
        // Replace "yourDB" with your actual DB name
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "root", "password");
    }

    public void create(Employee e) throws SQLException {
        String q = "INSERT INTO employees(id, name, department) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.setString(3, e.getDepartment());
        ps.executeUpdate();
        System.out.println("Inserted: " + e);
    }

    public Employee read(int id) throws SQLException {
        String q = "SELECT * FROM employees WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
        }
        return null;
    }

    public void update(int id, String newName) throws SQLException {
        String q = "UPDATE employees SET name = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setString(1, newName);
        ps.setInt(2, id);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Updated ID " + id : "ID not found");
    }

    public void delete(int id) throws SQLException {
        String q = "DELETE FROM employees WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Deleted ID " + id : "ID not found");
    }

    public static void main(String[] args) {
        try {
            EmployeeJDBC db = new EmployeeJDBC();

            // Create
            db.create(new Employee(1, "Alice", "HR"));
            db.create(new Employee(2, "Bob", "IT"));

            // Read
            System.out.println("\nRead ID 1: " + db.read(1));

            // Update
            db.update(2, "Bobby");

            // Read after update
            System.out.println("\nRead ID 2: " + db.read(2));

            // Delete
            db.delete(1);

            // Read after delete
            System.out.println("\nRead ID 1: " + db.read(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
