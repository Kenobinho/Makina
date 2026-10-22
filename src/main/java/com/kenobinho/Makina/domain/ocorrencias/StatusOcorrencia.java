package com.kenobinho.Makina.domain.ocorrencias;

public enum StatusOcorrencia {

    ABERTA, // quando a ocorrencia é criada, mas ninguem pega o equipamento para olhar
    EM_BANCADA, // quando um tecnico vai analisar o equipamento
    AGUARDANDO_PEÇA, // caso precise comprar algo
    CONCLUIDA, // occorencia encerrada/finalizada
    CANCELADA, // autoexplicativo
}
