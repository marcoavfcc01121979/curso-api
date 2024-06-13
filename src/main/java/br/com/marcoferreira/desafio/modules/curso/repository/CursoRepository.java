package br.com.marcoferreira.desafio.modules.curso.repository;

import br.com.marcoferreira.desafio.modules.curso.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {
    List<CursoEntity> findByNameOrCategory(String name, String category);
    Optional<CursoEntity> findByname(String username);

    Optional<CursoEntity> findByCategoryname(String category);
}
