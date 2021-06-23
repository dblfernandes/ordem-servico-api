package com.dbfernandes.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbfernandes.os.domain.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

}
