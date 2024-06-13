package br.com.marcoferreira.desafio.modules.curso.exceptions;

public class CourseFoundException extends RuntimeException{

    public CourseFoundException() {
        super("Curso já existe");
    }
}
