import java.util.List;

public class View {
    public void showMessage(String message){
        System.out.println(message);
    }

    public void showInputPrompt(String prompt){
        System.out.print(prompt);
    }


    public void showAnimalsList(List<Animal> animals){
        if(animals.isEmpty()){
            System.out.println("---- список пуст ----");
            return;
        }
        for (int i = 0; i < animals.size(); i++){
            String descript = description(animals.get(i));
            System.out.printf("  %s: %s\n", i+1, descript);
        }
    }

    public void showAnimal(Animal animal){
      
        System.out.printf("  %s\n", description(animal));

    }

    private String description(Animal animal){
        String type;
        switch (animal.getClass().getSimpleName()) {
            case "Cat":
                type = "Кот";
                break;
            case "Dog":
                type = "Собака";
                break;
            case "Hamster":
                type = "Хомяк";
                break;
            default:
                type = "Неизвестный тип";
                break;
        }

        String commands;
        if(animal.getCommands().size() == 0)
            commands = "нет";
        else
            commands = animal.getCommands().toString();

        return String.format("%s %s. Команды: %s", type, animal.getName(), commands);
    }
}
