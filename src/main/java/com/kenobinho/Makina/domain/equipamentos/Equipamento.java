package com.kenobinho.Makina.domain.equipamentos;


import com.kenobinho.Makina.domain.ocorrencias.Ocorrencia;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@AllArgsConstructor //gera construtores vazios
@NoArgsConstructor //gera construtores com campos
@Data // get e set
@Table (name = "equipamentos")
@Entity
public class Equipamento {

    // id do equipamento
    @Id // chave primeira
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    // codigo do dispositivo no sistema
    @Column(unique = true)
    private String codigoInterno;

    // numero de patrimonio (opcional)
    private String numeroPatrimonio;

    //de onde o equipamento veio
    private String setorOrigem;

    //descrição (opcional)
    private String descricao;


    //historico de coorrencias
    @OneToMany(MappedBy= "equipamento", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias;
}
