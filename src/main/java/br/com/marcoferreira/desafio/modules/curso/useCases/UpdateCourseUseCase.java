package br.com.marcoferreira.desafio.modules.curso.useCases;


import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UpdateCourseUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity update(CursoEntity cursoEntity, UUID id) {
        CursoEntity cursoEntity1 = cursoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found")
        );

        if ((cursoEntity.getCategory() == null || cursoEntity.getCategory().isEmpty())
            && (cursoEntity.getName() != null || !cursoEntity.getName().isEmpty())
        ) {
            cursoEntity1.setName(cursoEntity.getName());
        } else if((cursoEntity.getName() == null || cursoEntity.getName().isEmpty())
            && (cursoEntity.getCategory() != null || !cursoEntity.getCategory().isEmpty())
        ) {
            cursoEntity1.setCategory(cursoEntity.getCategory());
        } else {
            cursoEntity1.setName(cursoEntity.getName());
            cursoEntity1.setCategory(cursoEntity.getCategory());
        }

        return cursoRepository.save(cursoEntity1);
    }
}
