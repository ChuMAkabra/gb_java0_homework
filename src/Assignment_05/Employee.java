package Assignment_05;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private long phone;
    private int salary;
    private int age;

    public Employee() {
    }

    public Employee(String fullName) {
        this();
        this.fullName = fullName;
    }

    public Employee(String fullName, String position) {
        this(fullName);
        this.position = position;
    }

    public Employee(String fullName, String position, String email) {
        this(fullName, position);
        this.email = email;
    }

    public Employee(String fullName, String position, String email, long phone) {
        this(fullName, position, email);
        this.phone = phone;
    }

    public Employee(String fullName, String position, String email, long phone, int salary) {
        this(fullName, position, email, phone);
        this.salary = salary;
    }

    public Employee(String fullName, String position, String email, long phone, int salary, int age){
        this(fullName, position, email, phone, salary);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }
}