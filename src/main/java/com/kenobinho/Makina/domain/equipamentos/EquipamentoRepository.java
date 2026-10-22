package com.kenobinho.Makina.domain.equipamentos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    // criar a busca pelo codigo interno
    Optional<Equipamento> findByNumeroPatrimonio(String numeroPatrimonio);
}
