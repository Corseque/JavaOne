package lesson7;

public class FeedTheCatApp {
    public static void main(String[] args) {
//        Cat cat1 = new Cat("Bublik", 20);
//        cat1.info();
//        Plate plate1 = new Plate(10);
//        plate1.info();
//
//        cat1.feedTheCat(plate1);
//        cat1.info();
//        plate1.info();
//
//        plate1.increaseFood(10);
//        plate1.info();
//
//        cat1.feedTheCat(plate1);
//        cat1.info();
//        plate1.info();

        Cat[] cats = new Cat[5];
        Plate plate2 = new Plate(100);
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Cat" + (i + 1), 20 * (i + 1) / 2);
        }
        plate2.info();

        for (Cat cat : cats) {
            cat.info();
            cat.feedTheCat(plate2);
            cat.info();
            plate2.info();
        }

    }

}
