package lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", satiety=" + satiety +
                '}';
    }

    public boolean feedTheCat(Plate plate) {
        if (!satiety && plate.getFood() - appetite >= 0) {
            plate.setFood(plate.decreaseFood(appetite));
            satiety = true;
            System.out.println("Кот " + name + " поел и наелся.");
        } else if (satiety) {
            System.out.println("Кот " + name + " не голоден.");
        } else if (plate.getFood() - appetite < 0) {
            System.out.println("В этой тарелке не достаточно еды, чтобы накормить кота. Наполните её или поставте коту другую тарелку.");
        }
        return false;
    }

}
