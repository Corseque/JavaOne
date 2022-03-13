package lesson6;

import java.awt.*;

public class AnimalApp {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Barsik");
        Cat cat2 = new Cat("Murzik");
        System.out.println("Популяция котов: " + Cat.getCountOfCats() + " шт.");

        Dog dog1 = new Dog("Zhuchka");
        Dog dog2 = new Dog("Sharik");
        System.out.println("Популяция собак: " + Dog.getCountOfDogs() + " шт.");

        System.out.println("Живтоных итого: " + Animal.getCountOfAnimals() + " шт.");

        cat1.run(100);
        cat2.swim(10);

        dog1.run(350);
        dog2.swim(50);
    }
}
