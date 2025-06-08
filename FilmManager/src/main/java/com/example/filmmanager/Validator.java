package com.example.filmmanager;

public class Validator {
    static public boolean isIntegerAndPositive(String str){
        int num;
        try{
            num = Integer.parseInt(str);
            return num > 0;
        } catch (Exception e){
            return false;
        }
    }

    static public boolean isIncomeValid(String str){
        double num;
        try{
            num = Double.parseDouble(str);
            return num > 0;
        } catch (Exception e){
            return false;
        }
    }

    static public boolean containsDigits(String str){
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}
