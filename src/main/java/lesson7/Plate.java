package lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }

    public int decreaseFood(int food) {
        if (this.food < food) return this.food;
        else return this.food -= food;
    }

    public int increaseFood(int food) {
        return this.food += food;
    }
}
