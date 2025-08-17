package com.projetctJava.ProjectSpring.exceptions.custom;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id, String resource) {
        super(resource +" with id " + id + " not found.");
    }
    public ResourceNotFoundException(String resource){
        super("No "+ resource.toLowerCase()+" found");
    }
}
