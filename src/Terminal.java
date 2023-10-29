public class Terminal
{
    Parser parser;
    //Implement each command in a method, for example:
    //public String pwd(){}
    public void cd(String[] args){}
    // ...
//This method will choose the suitable command method to be called
    public void chooseCommandAction(String ch)
    {
        switch (ch)
        {
            case "echo":
                echo(parser.getArgs());
                break;
            case "pwd":
                pwd(parser.getArgs());
                break;
            case "history":
                history(parser.getArgs());
                break;
            case "ls":
                ls(parser.getArgs());
                break;
            case "ls-r":
                ls-r(parser.getArgs());
                break;
            case "cp":
                cp(parser.getArgs());
                break;
            case "cd":
                cd(parser.getArgs());
                break;
            case "rm":
                rm(parser.getArgs());
                break;
            case "cat":
                cat(parser.getArgs());
                break;
            case ">":
                outputRedirect(parser.getArgs());
                break;
            case ">>":
                withAppendOutput(parser.getArgs());
                break;
            case "toch":
                toch(parser.getArgs());
                break;
            case "mkdir":
                mkdir(parser.getArgs());
                break;
            default:
                System.out.println("command not found");

        }
    }
    public static void main(String[] args){}

}
