package Lesson5;

public class Employee {
    //ФИО, должность, email, телефон, зарплата, возраст
    private String name;
    private String surname;
    private String position;
    private String email;
    private String phoneNum;
    private int gross;
    private int age;

    public Employee(String name, String surname, String position, String email, String phoneNum, int gross, int age) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNum = phoneNum;
        this.gross = gross;
        this.age = age;
    }

    void employeeInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", gross=" + gross +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }
}
