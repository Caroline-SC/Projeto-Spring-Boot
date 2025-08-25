package com.projetctJava.ProjectSpring.exceptions.custom;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String param,String possibleValues,String providedValue) {
    super("The param "+ param +" needs to be either "+possibleValues+".Provided value: "+providedValue);
    }
}
