package br.com.gian418.customersuccessbalancing.services.aux;

import br.com.gian418.customersuccessbalancing.dtos.GerenteSucessoDTO;

public class GerenteQuantidadeAux {

    private GerenteSucessoDTO gerente;
    private Integer quantidade;

    public GerenteQuantidadeAux(GerenteSucessoDTO gerente, Integer quantidade) {
        this.gerente = gerente;
        this.quantidade = quantidade;
    }

    public GerenteSucessoDTO getGerente() {
        return gerente;
    }

    public void setGerente(GerenteSucessoDTO gerente) {
        this.gerente = gerente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
