import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Controller {
    private final String helpText = "help - помощь, add - добавть питомца, all - список всех питомцев, pet - показать питомца, ncmd - новая комманда, q - выход";

    private View view;
    private Repository repository;
    private Scanner scanner;
    public Controller(View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }
    
    public void run(){
        scanner = new Scanner(System.in, "Cp866");
        view.showMessage(helpText);
        
        boolean doit = true;
        while(doit){
            String userIput = askUser(": ").toLowerCase();

            switch (userIput) {
                case "help":
                    view.showMessage(helpText);
                    break;
                case "all":
                    showAllPet();
                    break;
                case "add":
                    addNewPet();
                    break;
                case "pet":
                    showPet();
                    break;
                case "ncmd":
                    addNewCommand();
                    break;
                case "q":
                    doit = false;
                    break;
                default:
                    view.showMessage("Команда не распозанана. Повторите.");
                    break;
            }
        }

        view.showMessage("\n   -- Конец сеанса. До свидания! --");
    }

    private String askUser(String ask){
        view.showInputPrompt(ask);
        String answer = scanner.nextLine().trim();
        return answer;
    }

    private void showAllPet(){
        Predicate<Animal> filter = x -> true;
        List<Animal> pets = repository.getAnimals(filter);
        view.showAnimalsList(pets);
    }

    private void addNewPet(){
        Animal pet = inputNewPet();
        if(pet != null){
            repository.addAnimal(pet);
            view.showMessage("Питомец добавлен\n");
        }
    }

    private void showPet(){
        int index = selectPetIndex();
        if (index != -1)
            view.showAnimal(repository.getAnimals(x -> true).get(index));
    }

    private void addNewCommand(){
        int index = selectPetIndex();
        if (index != -1){
            Animal pet = repository.getAnimals(x -> true).get(index);
            String newCommand = askUser("  Введите комаду: ");
            if (newCommand.length() > 0){
                pet.addCommand(newCommand);
                repository.updateAnimal(pet);
                view.showMessage("команда добавлена\n");
            }
        }
    }

  

    private int selectPetIndex(){
        Predicate<Animal> filter = x -> true;
        List<Animal> pets = repository.getAnimals(filter);

        String input = askUser("  номер питомца: ");
        Integer number = stringToInteger(input);
        if(number == null)
            return -1;

        if (number < 1 || number > pets.size()){
            view.showMessage("  Неверный номер!\n");
            return -1;
        }

        return number-1;
    }

    private Animal inputNewPet(){
        String petClass = askUser("  Вид(соб, кот, хом): ").toLowerCase();

        List<String> relevantCalsses = new LinkedList<>(List.of("соб", "кот", "хом"));
        if (relevantCalsses.indexOf(petClass) < 0){
            view.showMessage("  Ошибка! Неизвестынй вид.\n");
            return null;
        }

        String name = askUser("  Имя: ");
        if(name.length() == 0){
            view.showMessage("  Ошибка! Имя не может быть пустым.\n");
            return null;
        }

        String dateString = askUser("  Дата рождения (в формате \"гггг-мм-дд\"): ");
        Date bith = Util.parseDate(dateString);
        if(bith == null){
            view.showMessage("  Ошибка! Неверная дата.\n");
            return null;
        }

        String answer = askUser("  Уровень милоты (0..100): ");
        int lewelOfCute = 0;
        try{

            lewelOfCute = Integer.parseInt(answer);
        
        }catch(NumberFormatException exception){
            view.showMessage("  Ошибка! Не число.\n");
            return null;
        }

        if (lewelOfCute > 100 || lewelOfCute < 0){
            view.showMessage("  Ошибка! Число вне диапазона.\n");
            return null;
        }
        
        
        // System.out.println("=== Уровень милоты [" + lewelOfCute + "]");

        Integer specificParameter = null;
        switch (petClass) {
            case "соб":
                specificParameter = inputSpecificParameter("  Громкость лая", 0, 100);
                break;
            case "кот":
                specificParameter = inputSpecificParameter("  Грация", 0, 100);
                break;
            case "хом":
                specificParameter = inputSpecificParameter("  Пушистость", 0, 100);
                break;
        }
        if(specificParameter == null)
            return null;

        Animal result = null;
        switch (petClass) {
            case "соб":
                result = new Dog(name, bith, lewelOfCute, specificParameter);
                break;
            case "кот":
                result = new Cat(name, bith, lewelOfCute, specificParameter);
                break;
            case "хом":
                result = new Hamster(name, bith, lewelOfCute, specificParameter);
                break;
        }
        
        return result;

    }

    private Integer inputSpecificParameter(String parameter, int minValue, int maxValue){
        String ask = String.format("%s (%s..%s): ",  parameter, minValue, maxValue);
        String input = askUser(ask).trim();
        Integer result = null;
        try{
            result = Integer.parseInt(input);
        }catch(NumberFormatException exception){
            view.showMessage("  Ошибка! Не число.\n");
            return null;
        }

        if (result > maxValue || result < minValue){
            view.showMessage("  Ошибка! Число вне диапазона.\n");
            return null;
        }

        return result;
    }

    private Integer stringToInteger(String str){
        try{
            Integer result = Integer.parseInt(str);
            return result;
        }catch(NumberFormatException exception){
            view.showMessage("  Ошибка! Не число.\n");
            return null;
        }
    }

}
