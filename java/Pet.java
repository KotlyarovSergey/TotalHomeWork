import java.util.Date;

public abstract class Pet extends Animal{
    private int levelOfCute;
    
    public Pet(String name, Date birthDate, int levelOfCute) {
        super(name, birthDate);
        this.levelOfCute = levelOfCute;
    }
    
    public int getLevelOfCute() {
        return levelOfCute;
    }
}
