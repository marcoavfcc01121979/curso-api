package br.com.marcoferreira.desafio.modules.curso.useCases;


import br.com.marcoferreira.desafio.modules.curso.exceptions.CourseNotFoundException;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    public void deletar(UUID id) {
        boolean cursoExiste = this.cursoRepository.findById(id).isPresent();

        if (cursoExiste) {
            this.cursoRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException();
        }
    }
}
