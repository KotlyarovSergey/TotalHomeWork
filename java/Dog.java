import java.util.Date;

public class Dog extends Pet {
    private int barking;
    
    public Dog(String name, Date birthDate, int levelOfCute, int barking) {
        super(name, birthDate, levelOfCute);
        this.barking = barking;
    }
    
    public int getBarking() {
        return barking;
    }
}
