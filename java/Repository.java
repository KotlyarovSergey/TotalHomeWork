import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    private List<Animal> animals;
    private String fileName;

    public Repository(String file){
        this.fileName = file;
        this.animals = loadFromFile();
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
            System.out.println("File has been written");
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 
    }

    // C
    public void addAnimal(Animal animal){
        this.animals.add(animal);
        saveToFile();
    }

    // R
    public List<Animal> getAnimals(){



        return this.animals;

    }

    // U
    public void updateAnimal(Animal oldAnimal, Animal newAnimal){
        int position = this.animals.indexOf(oldAnimal);
        if (position == -1)
            this.addAnimal(newAnimal);
        else
            this.animals.set(position, newAnimal);
        
        saveToFile();
    }


    // D
    public void deleteAnimal(Animal animal){
        this.animals.remove(animal);
        saveToFile();
    }
}
