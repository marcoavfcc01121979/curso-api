package br.com.marcoferreira.desafio.modules.curso.controllers;

import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import br.com.marcoferreira.desafio.modules.curso.repository.CursoRepository;
import br.com.marcoferreira.desafio.modules.curso.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class CursoController {

    @Autowired
    private CreateCursoUseCases createCursoUseCases;

    @Autowired
    private ListCourseUseCase listCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private DeleteCursoUseCase deleteCursoUseCase;

    @Autowired
    private PatchCourseUseCase patchCourseUseCase;

    @PostMapping("/courses")
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity) {
        try {
            var result = this.createCursoUseCases.execute(cursoEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CursoEntity>> list() {

        var result = this.listCourseUseCase.getAll();

        return new ResponseEntity<List<CursoEntity>>(result, HttpStatus.OK);

    }

    @GetMapping("/filter")
    public ResponseEntity<List<CursoEntity>> listNameOrListCategory(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String category
        ) {
            var result = this.listCourseUseCase.getBynameOrBycategory(name, category);
            return new ResponseEntity<List<CursoEntity>>(result, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody CursoEntity cursoEntity, @PathVariable UUID id) {
        try {
            if(cursoEntity.getName() == null && cursoEntity.getCategory() == null) {
                return ResponseEntity.badRequest().body("Nome e categoria não pode ser nulos!");
            }
            var curso = this.updateCourseUseCase.update(cursoEntity, id);
            return ResponseEntity.ok().body(curso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteCursoUseCase.deletar(id);
            return ResponseEntity.ok().body("deletado!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable UUID id) {
        try {
            String response = this.patchCourseUseCase.execute(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
