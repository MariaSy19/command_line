import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal
{
    Parser parser;
    private static Path currDirectory = Paths.get(System.getProperty("user.dir"));// current working directory
    List<String> histComm = new ArrayList<>(); // for storing history of commands
    // Constructor
    public Terminal()
    {
        this.parser = new Parser();
    }
    // This function is used to print text or messages
    public void echo(String[] str)
    {

        for (String arg : str) {
            System.out.print(arg + " "); // print each argument separated by a space
        }
        System.out.println();
    }
    // This function prints the current working directory
    public void pwd()
    {
        System.out.println(currDirectory.toAbsolutePath());
    }
    public void mkdir(String[] drc)
    {
        for (String directory : drc) {
            // Create a Path object representing the directory
            Path newDirectory = Paths.get(directory);

            try {
                Files.createDirectories(newDirectory);//create nonexistent directory
                System.out.println("Directory created: " + newDirectory.toString());
            } catch (FileAlreadyExistsException exp) {
                // if directory already exists
                System.out.println("Directory already exists: " + newDirectory.toString());
            } catch (Exception exp) {
                System.out.println("Error creating directory: " + exp.getMessage());
            }
        }
    }

    //this function to display command history
    public void history()
    {
        for (int var = 0; var < histComm.size(); var++) {
            System.out.println((var + 1) + " " + histComm.get(var));
        }
    }

//This method will choose the suitable command method to be called
    public void chooseCommandAction(String ch, String input)
    {
        switch (ch)
        {
            case "echo":
                if (parser.parse(input))
                {
                    echo(parser.getArgs());
                }
                break;
            case "pwd":
                pwd();
                break;
            case "history":
                history();
                break;
            case "mkdir":
                if (parser.parse(input))
                {
                    String[] args = parser.getArgs();
                    mkdir(args);
                } else
                {
                    System.out.println("Invalid arguments!");
                }
                break;
            default:
                System.out.println("command not found");

        }
    }
    public static void main(String[] args)
    {
        Terminal terminal = new Terminal();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            terminal.histComm.add(input);

            String[] inputParts = input.split(" ");
            String command = inputParts[0];
            String[] arguments = new String[inputParts.length - 1];
            System.arraycopy(inputParts, 1, arguments, 0, arguments.length);

            terminal.chooseCommandAction(command, input);
        }
    }

}
