package com.dbfernandes.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbfernandes.os.api.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
