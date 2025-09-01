package com.projetctJava.ProjectSpring.exceptions.custom;


import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {
    private String attribute;
    public DuplicateResourceException(String attribute) {
        super(attribute +" already registered");
        this.attribute = attribute;
    }

}
