import java.util.Date;

public class Hamster extends Pet {
    private int fluffyness;
    public Hamster(String name, Date birthDate, int levelOfCute, int fluffyness) {
        super(name, birthDate, levelOfCute);
        this.fluffyness = fluffyness;
    }
    
    public int getFluffyness() {
        return fluffyness;
    }
}
