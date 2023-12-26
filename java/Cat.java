import java.util.Date;

public class Cat extends Pet {
    private int grace;
    
    public Cat(String name, Date birthDate, int levelOfCute, int grace) {
        super(name, birthDate, levelOfCute);
        this.grace = grace;
    }
    
    public int getGrace() {
        return grace;
    }
}
