import java.util.Date;

public class Horse extends PackAnimal{
    private int speed;
    public Horse(String name, Date birthDate, int capacity, int speed) {
        super(name, birthDate, capacity);
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
}
