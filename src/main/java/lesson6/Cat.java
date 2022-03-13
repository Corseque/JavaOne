package lesson6;

public class Cat extends Animal {
    private final int MAX_DISTANCE_RUN = 200;
    private static int countOfCats = 0;

    public Cat(String name) {
        super(name);
        countOfCats++;
    }

    @Override
    public void run(int distanceRun) {
        int restDistance;
        if (distanceRun < MAX_DISTANCE_RUN) {
            System.out.println("Кот по имени " + super.name + " пробежал " + distanceRun + " метров.");
        } else {
            restDistance = distanceRun - MAX_DISTANCE_RUN;
            System.out.println("Кот по имени " + super.name + " пробежал " + MAX_DISTANCE_RUN + " метров и остальные " + restDistance + " не осилил.");
        }
    }

    @Override
    public void swim(int distanceSwim) {
        System.out.println("Коты не умеют плавать.");
    }

    public static int getCountOfCats() {
        return countOfCats;
    }
}
