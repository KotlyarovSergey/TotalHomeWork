import java.util.Date;

public abstract class PackAnimal extends Animal {
    private int capacity;
   
    public PackAnimal(String name, Date birthDate, int capacity) {
        super(name, birthDate);
        this.capacity = capacity;
    }

     public int getCapacity() {
        return capacity;
    }
}