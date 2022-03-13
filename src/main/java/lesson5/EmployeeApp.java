package lesson5;

public class EmployeeApp {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Olga", "Svetlichnaya", "Java Developer", "corseque@ya.ru", "+7-963-925-04-48", 100000, 36);
        employees[1] = new Employee("Evgeniy", "Svetlichniy", "IT Director", "es.eujin@gmail.com", "+7-963-671-52-91", 300000, 37);
        employees[2] = new Employee("Nina", "Baranova", "Head of Customer Support", "nina.i.b@ya.ru", "+7-963-000-00-00", 100000, 75);
        employees[3] = new Employee("Tatyana", "Svetlichnaya", "Head of Sales Department", "tatyana.v.s@ya.ru", "+7-963-000-00-00", 100000, 59);
        employees[4] = new Employee("Ivan", "Ivanov", "Sale manager", "ivan.v.i@ya.ru", "+7-963-000-00-00", 50000, 30);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() >=40) {
                employees[i].employeeInfo();
            }
        }
    }
}
