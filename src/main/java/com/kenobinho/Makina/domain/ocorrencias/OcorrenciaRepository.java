package com.kenobinho.Makina.domain.ocorrencias;


import com.kenobinho.Makina.domain.equipamentos.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    //buscar por id
    List<Ocorrencia> findByEquipamentoId(Long equipamentoId);

    //buscar por status
    List<Ocorrencia> findByStatusOcorrencia(StatusOcorrencia statusOcorrencia);


}
