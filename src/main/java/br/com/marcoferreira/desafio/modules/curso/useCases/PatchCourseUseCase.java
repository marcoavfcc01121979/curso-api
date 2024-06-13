package br.com.marcoferreira.desafio.modules.curso.useCases;

import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import br.com.marcoferreira.desafio.modules.curso.entity.valueObject.CourseStatus;
import br.com.marcoferreira.desafio.modules.curso.exceptions.CourseNotFoundException;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatchCourseUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    public String execute(UUID id) {
        CursoEntity cursoEntity = cursoRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException()
        );

        if(cursoEntity.getActive() == CourseStatus.ACTIVE) {
            cursoEntity.setActive(CourseStatus.INACTIVE);
            cursoRepository.save(cursoEntity);
            return "Curso desativado!";
        } else {
            cursoEntity.setActive(CourseStatus.ACTIVE);
            cursoRepository.save(cursoEntity);
            return "Curso Ativado!";
        }
    }
}
