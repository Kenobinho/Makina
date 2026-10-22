package com.kenobinho.Makina.domain.ocorrencias;

import com.kenobinho.Makina.domain.equipamentos.Equipamento;
import com.kenobinho.Makina.domain.equipamentos.EquipamentoRepository;
import com.kenobinho.Makina.domain.equipamentos.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OcorrenciaService {


    //construtor
    private final OcorrenciaRepository ocorrenciaRepository;
    private final  EquipamentoService equipamentoService;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, EquipamentoService equipamentoService) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.equipamentoService = equipamentoService;
    }


    // buscar por id
    public Ocorrencia buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada com Id: "+ id));
    }

    // busca todas as ocorrencias
    public List<Ocorrencia> listarTodasOcorrencias() {
        return ocorrenciaRepository.findAll();
    }

    public List<Ocorrencia> listarPorEquipamento(Long equipamentoId) {
        return ocorrenciaRepository.findByEquipamentoId(equipamentoId);
    }


    @Transactional // abrir ocorrencia
    public Ocorrencia abrirOcorrencia (Long equipamentoId, Ocorrencia novaOcorrencia) {
        Equipamento equipamento = equipamentoService.buscarPorId(equipamentoId);
        novaOcorrencia.setEquipamento(equipamento);
        novaOcorrencia.setStatusOcorrencia(StatusOcorrencia.ABERTA);
        novaOcorrencia.setDataEntrada(LocalDateTime.now());
        return ocorrenciaRepository.save(novaOcorrencia);
    }

    @Transactional
    public Ocorrencia atualizarStatus(Long ocorrenciaId, StatusOcorrencia novoStatus) {
        Ocorrencia ocorrencia = buscarPorId(ocorrenciaId);
        ocorrencia.setStatusOcorrencia(novoStatus);
        return ocorrencia;
    }

    @Transactional
    public Ocorrencia finalizarOcorrencia(Long ocorrenciaId, String solucaoAplicada, String retiradoPor) {
        //busca id
        Ocorrencia ocorrencia = buscarPorId(ocorrenciaId);

        if (solucaoAplicada == null || solucaoAplicada.isBlank()) {
            throw new IllegalArgumentException("A solução aplicada é obrigatória");
        }
        ocorrencia.setSolucaoAplicada(solucaoAplicada);

        // [ede nome da pessoa que retirou
        ocorrencia.setRetiradoPor(retiradoPor);
        ocorrencia.setStatusOcorrencia(StatusOcorrencia.CONCLUIDA);
        ocorrencia.setDataSaida(LocalDateTime.now());

        return ocorrencia;

    }

}
