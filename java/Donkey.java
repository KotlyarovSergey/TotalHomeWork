import java.util.Date;

public class Donkey extends PackAnimal{
    private int stubbornness;
    public Donkey(String name, Date birthDate, int capacity, int stubbornness) {
        super(name, birthDate, capacity);
        this.stubbornness = stubbornness;
    }
    public int getStubbornness() {
        return stubbornness;
    }
}
