class Parser {
    String commandName;
    String[] args;

    // This method will divide the input into commandName and args
    // where "input" is the string command entered by the user
    public boolean parse(String input)
    {
        //split to get individual string
        String[] str = input.split(" ");
        //check if there are no strings
        if (str.length == 0)
        {
            System.out.println("Invalid command! ");
            return false; //parsing failure
        }
        commandName = str[0]; //first string assumed command name
        args = new String[str.length - 1]; // The rest of the strings (if any) are considered as arguments
        System.arraycopy(str, 1, args, 0, str.length - 1);// Copy the arguments from string into the args array
        if(( commandName.equals("pwd")) && args.length != 0)
        {
            System.out.println("ERROR: " + commandName + " not take any argumnent!");
            return false;
        }
        if(( commandName.equals("echo") || commandName.equals("mkdir") ||commandName.equals("cat") ) && args.length == 0 )
        {
            System.out.println("ERROR: " + commandName + " take at least 1 argument!");
            return false;
        }
        if(( commandName.equals("rmdir") ||commandName.equals("touch")
            ||commandName.equals("rm") ) && args.length != 1 )
        {
            System.out.println("ERROR: " + commandName + " take one argument!");
            return false;
        }
        return true;//parsing successful
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
}
