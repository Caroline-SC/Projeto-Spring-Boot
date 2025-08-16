package com.projetctJava.ProjectSpring.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException(Object id, String resource) {
        super(resource +" not found. Id " + id);
    }
    public ResourceNotFoundException(String resource){
        super("No "+ resource.toLowerCase()+" found");
    }
}
