import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Repository {
    private List<Animal> animals;
    private String fileName;

    public Repository(String file){
        this.fileName = file;
        this.animals = loadFromFile();
    }

    

    // C
    public void addAnimal(Animal animal){
        this.animals.add(animal);
        saveToFile();
    }

    // R
    public List<Animal> getAnimals(Predicate<Animal> filter){
        List<Animal> result = new LinkedList<>();
        for (Animal animal : this.animals) {
            if(filter.test(animal)) 
                result.add(animal);
        }

        // return this.animals;
        return result;
    }


    // U
    public void updateAnimal(Animal animal){
        int position = this.animals.indexOf(animal);
        if (position == -1)
            this.addAnimal(animal);

        saveToFile();
    }


    // D
    public void deleteAnimal(Animal animal){
        this.animals.remove(animal);
        saveToFile();
    }
    
    
    private List<Animal> loadFromFile(){
        List<Animal> result = new LinkedList<>();

        File file = new File(this.fileName);
        if(!file.exists())
            return new LinkedList<Animal>();


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName)))
        {
             
            result=(LinkedList<Animal>)ois.readObject();
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 

        return result;
    }

    private void saveToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName)))
        {
            oos.writeObject(this.animals); 
            // System.out.println("File has been written");
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 
    }
}
