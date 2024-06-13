package br.com.marcoferreira.desafio.modules.curso.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
        super("Curso não encontrado!");
    }
}
