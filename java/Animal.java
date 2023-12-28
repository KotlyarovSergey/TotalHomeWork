import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Animal implements Serializable{
    private String name;
    private Date birthDate;
    private List<String> commandList;

    public Animal(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
        this.commandList = new LinkedList<>();
    }

    public String getName(){
        return this.name;
    }
    public Date getBirthDate(){
        return this.birthDate;
    }
    public void addCommand(String command){
        if (commandList.indexOf(command) == -1)
            commandList.add(command);
    }

    public void addCommands(String[] commands){
        for (String cmd : commands) {
            if (commandList.indexOf(cmd) == -1)
            commandList.add(cmd);
        }
        
    }

    public List<String> getCommands(){
        return this.commandList;
    }

    @Override
    public String toString(){
        return String.format("%s %s, cmds: %s", this.getClass().getSimpleName(), this.name, this.commandList);
    }
    
}