import java.util.Date;

public class Camel extends PackAnimal {
    private int timeWithoutWater;
    public Camel(String name, Date birthDate, int capacity, int timeWithoutWater) {
        super(name, birthDate, capacity);
        this.timeWithoutWater = timeWithoutWater;
    }
    
    public int getTimeWithoutWater() {
        return timeWithoutWater;
    }
}
