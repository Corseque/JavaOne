package lesson6;

public abstract class Animal {
    protected String name;
    private static int countOfAnimals = 0;

    public Animal(String name) {
        this.name = name;
        countOfAnimals++;
    }

    public static int getCountOfAnimals() {
        return countOfAnimals;
    }

    public abstract void run(int distanceRu);

    public abstract void swim(int distanceSwim);


}
