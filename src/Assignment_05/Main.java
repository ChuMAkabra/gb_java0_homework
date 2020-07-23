package Assignment_05;

public class Main {
    public static void main(String[] args) {
        Employee [] employees = new Employee[5];

        employees[0] = new Employee("Иванов Иван Иванович", "Principal программист" ,
                "iii@mail.ru", 89123456789L, 400000, 55);
        employees[1] = new Employee("Петров Петр Петрович", "Senior программист" ,
                "ppp@mail.ru", 89123456780L, 300000, 44);
        employees[2] = new Employee("Кузьмин Кузьма Кузьмич", "Middle программист" ,
                "kkk@mail.ru", 89123456700L, 200000, 33);
        employees[3] = new Employee("Сидоров Сидор Сидорович", "Junior программист" ,
                "sss@mail.ru", 89123456000L, 100000, 22);
        employees[4] = new Employee("Нубов Нуб Нубович", "Стажер-программист" ,
                "nnn@mail.ru", 89123450000L, 30000, 19);

        for (Employee emp :
                employees) {
            if (emp.getAge() > 40) System.out.println(emp.toString());
        }
    }
}
