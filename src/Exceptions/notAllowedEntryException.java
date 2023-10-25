package Exceptions;

public class notAllowedEntryException extends Exception{
    public notAllowedEntryException(){
        super();
    }
    public notAllowedEntryException(String s){
        super(s);
    }
}
