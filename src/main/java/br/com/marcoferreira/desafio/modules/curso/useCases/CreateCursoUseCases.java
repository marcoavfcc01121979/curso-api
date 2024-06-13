package br.com.marcoferreira.desafio.modules.curso.useCases;

import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import br.com.marcoferreira.desafio.modules.curso.entity.valueObject.CourseStatus;
import br.com.marcoferreira.desafio.modules.curso.exceptions.CourseFoundException;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateCursoUseCases {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(CursoEntity cursoEntity) {
        this.cursoRepository.findByname(cursoEntity.getName())
                .ifPresent(course -> {
                    throw new CourseFoundException();
                });
        cursoEntity.setActive(CourseStatus.ACTIVE);
        return this.cursoRepository.save(cursoEntity);
    }
}
