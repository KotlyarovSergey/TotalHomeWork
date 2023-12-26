// import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

class Main{
    public static void main(String[] args) {
        Cat cat1 = new Cat("Murka", new Date(2023, 02, 12), 12, 55);
        Cat cat2 = new Cat("Barsik", new Date(2022, 10, 4), 15, 22);

        Dog dog1 = new Dog("Rex", new Date(2020, 04, 13), 7, 40);

        List<Pet> pets = new LinkedList<>();
        pets.add(dog1);
        pets.add(cat1);

        List<Animal> animals = new LinkedList<>();
        animals.add(dog1);

        cat1.addCommand("sayMeow");
        dog1.addCommands(new String[]{"sit", "stay", "vioce"});

        for (String cmd : cat1.getCommands()) {
            System.out.println(cmd);
        }

        for (String cmd : dog1.getCommands()) {
            System.out.print(cmd + " ");
        }
        System.out.println();

        Calendar cl = new Calendar.Builder().setDate(2023, 12, 25).build();
        cl = new GregorianCalendar(2023,12,25);
        Date dt = cl.getTime();
        System.out.println(dt);

        // toFile();
        // catTotFile();
        // sereList();
        // catList();

        // Repository repo = new Repository("animals.dat");
        // repo.addAnimal(dog1);
        // repo.addAnimal(cat1);
        
        Repository repo2 = new Repository("animals.dat");
        animals = repo2.getAnimals();

        System.out.println(animals.size());
        for (Animal anm : animals) {
            // System.out.println(anm.getClass().getSimpleName() + " " + anm.getCommands());
            System.out.printf("%s %s, cmds: %s \n", anm.getClass().getSimpleName(), anm.getName(), anm.getCommands());
        }

        // repo2.deleteAnimal(animals.get(2));
        
        // Animal newDog = new Dog("Pitty", dt, 0, 0);
        // newDog.addCommands(new String[]{"sit", "stay"});
        // repo2.updateAnimal(animals.get(0), newDog);
    }








    private static void toFile(){
        String filename = "animals.dat";
        // создадим список объектов, которые будем записывать
        List<Animal> animals = new ArrayList<>();

        Calendar cl = new GregorianCalendar(2022,12,25);
        Animal cat = new Cat("Том", cl.getTime(), 10, 20);
        cl = new GregorianCalendar(2021,05,17);
        Animal dog = new Dog("Rex", cl.getTime(), 2, 15);
        animals.add(cat);
        animals.add(dog);
          
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(animals);
            System.out.println("File has been written");
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 
          
        // десериализация в новый список
        ArrayList<Animal> newAnimal= new ArrayList<Animal>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
             
            newAnimal=((ArrayList<Animal>)ois.readObject());
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 
          
        for(Animal a : newAnimal)
            System.out.printf("Name: %s \n", a.getName());
    }

    private static void catTotFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat")))
        {
            Calendar cl = new GregorianCalendar(2022,12,25);
            // Cat cat = new Cat("Том", cl.getTime(), 10, 20);
            Animal cat = new Cat("Том", cl.getTime(), 10, 20);
            
            oos.writeObject(cat);
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            Cat cat2 =(Cat)ois.readObject();
            // Animal cat2 =(Cat)ois.readObject();
            System.out.printf("Name: %s, Age: %s, Cute: %s \n", cat2.getName(), cat2.getBirthDate().getYear(), cat2.getLevelOfCute());
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        }
    }

    private static void sereList(){
        ArrayList<String> lst = new ArrayList<>();
        lst.add("один");
        lst.add("два");
        lst.add("Три");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat")))
        {
            oos.writeObject(lst); 
            System.out.println("File has been written");
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 

        ArrayList<String> newList = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            newList=((ArrayList<String>)ois.readObject());
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 

        System.out.println(newList);

    }

    private static void catList(){
        ArrayList<Cat> lst = new ArrayList<>();
        Calendar cl = new GregorianCalendar(2022,12,25);
        Cat cat = new Cat("Том", cl.getTime(), 10, 20);
            //Animal cat = new Cat("Том", cl.getTime(), 10, 20);
        lst.add(cat);

        cl = new GregorianCalendar(2021,03,12);
        cat = new Cat("Барсик", cl.getTime(), 13, 24);
        lst.add(cat);
    
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat")))
        {
            oos.writeObject(lst); 
            System.out.println("File has been written");
        }
        catch(Exception ex){
             
            System.out.println(ex.getMessage());
        } 

        ArrayList<Cat> newList = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            newList=((ArrayList<Cat>)ois.readObject());
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 

        for(Cat ct : newList)
            System.out.printf("Name: %s \t Birth: %d \n", ct.getName(), ct.getGrace());

    }
}