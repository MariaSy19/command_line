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
    // this function to create a new directory.
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

    // this function for remove directories
    public void rmdir(String drc) {
        try {
            // Check if the provided directory is "*"
            if (drc.equals("*")) {
                // List all directories in the current directory
                Files.list(currDirectory)
                        .filter(Files::isDirectory)
                        .forEach(dir -> {
                            try {
                                // Check if the directory is empty
                                if (Files.list(dir).findFirst().isEmpty()) {
                                    // If empty, delete the directory
                                    Files.delete(dir);
                                    System.out.println("Directory removed: " + dir.toString());
                                }
                                // Handle any IOExceptions that occur during deletion
                            } catch (IOException e) {
                                System.out.println("Error removing directory: " + e.getMessage());
                            }
                        });
            } else {
                // Convert the directory name to a Path object
                Path dir = Paths.get(drc);

                if (Files.isDirectory(dir)) {
                    // Check if the directory is not empty
                    if (Files.list(dir).findFirst().isPresent()) {
                        // Directory is not empty, cannot be removed
                        System.out.println("Error: Directory is not empty.");
                    } else {
                        // Directory is empty, attempt to delete it
                        Files.delete(dir);
                        System.out.println("Directory removed: " + dir.toString());
                    }
                } else {
                    // The provided path is not a directory
                    System.out.println("Error: Not a directory.");
                }
            }
        } catch (IOException exp) {
            // Handle any IOExceptions that occur during the process
            System.out.println("Error: " + exp.getMessage());
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
            case "rmdir":
                if (parser.parse(input)) {
                    String[] args = parser.getArgs();
                    if (args.length == 1) {
                        rmdir(args[0]);
                    } else {
                        System.out.println("Invalid arguments!");
                    }
                } else {
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
