package com.kenobinho.Makina.domain.ocorrencias;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kenobinho.Makina.domain.equipamentos.Equipamento;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ocorrencias" )
@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // quando entrou
    private LocalDateTime dataEntrada;

    // quando saiu
    private LocalDateTime dataSaida;

    //quem enviou
    private String entreguePor;

    //quem levou
    private String retiradoPor;

    private String tecnicoResponsavel;

    //Status
    @Enumerated(EnumType.STRING)
    private StatusOcorrencia statusOcorrencia;

    // autoexplicativo
    private String problemaRelatado;

    // autoexplicativo ao quadrado
    private String solucaoAplicada;

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
@JsonIgnoreProperties("ocorrencias")
@lombok.ToString.Exclude
@lombok.EqualsAndHashCode.Exclude
private Equipamento equipamento;
}
