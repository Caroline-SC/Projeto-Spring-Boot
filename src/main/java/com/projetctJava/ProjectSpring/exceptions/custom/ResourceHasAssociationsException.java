package com.projetctJava.ProjectSpring.exceptions.custom;

public class ResourceHasAssociationsException extends RuntimeException {
    public ResourceHasAssociationsException(String resource, Long id) {

        super("It is not possible to delete the "+ resource +" with ID "+id+" because there are associated resources");
    }
}
