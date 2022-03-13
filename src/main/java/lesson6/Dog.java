package lesson6;

public class Dog extends Animal {
    private final int MAX_DISTANCE_RUN = 500;
    private final int MAX_DISTANCE_SWIM = 10;
    private static int countOfDogs = 0;

    public Dog(String name) {
        super(name);
        countOfDogs++;
    }

    @Override
    public void run(int distanceRun) {
        int restDistance;
        if (distanceRun < MAX_DISTANCE_RUN) {
            System.out.println("Собака по имени " + super.name + " пробежала " + distanceRun + " метров.");
        } else {
            restDistance = distanceRun - MAX_DISTANCE_RUN;
            System.out.println("Собака по имени " + super.name + " пробежала " + MAX_DISTANCE_RUN + " метров и остальные " + restDistance + " не осилила.");
        }
    }

    @Override
    public void swim(int distanceSwim) {
        int restDistance;
        if (distanceSwim < MAX_DISTANCE_SWIM) {
            System.out.println("Собака по имени " + super.name + " проплыла " + distanceSwim + " метров.");
        } else {
            restDistance = distanceSwim - MAX_DISTANCE_SWIM;
            System.out.println("Собака по имени " + super.name + " проплыла " + MAX_DISTANCE_SWIM + " метров и остальные " + restDistance + " не осилила.");
        }
    }

    public static int getCountOfDogs() {
        return countOfDogs;
    }
}
