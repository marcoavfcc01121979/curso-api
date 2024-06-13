package br.com.marcoferreira.desafio.modules.curso.useCases;

import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCourseUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoEntity> getAll() {
        return this.cursoRepository.findAll();
    }

    public List<CursoEntity> getBynameOrBycategory(String name, String category) {
        return this.cursoRepository.findByNameOrCategory(name, category);
    }
}
