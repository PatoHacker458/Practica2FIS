package org.example.practica2fis.Models;

public class Validator
{
    public boolean checkInput(String str)
    {
        int num = 0;
        try{
            num = Integer.parseInt(str);
        } catch (NumberFormatException e){
            return false;
        }
        return (num < 25 && num > 0);
    }
}
