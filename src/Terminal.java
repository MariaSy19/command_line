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
            case "cd":
                cd(parser.getArgs());
                break;

            default:
                System.out.println("command not found");

        }
    }
    public static void main(String[] args){}

}
