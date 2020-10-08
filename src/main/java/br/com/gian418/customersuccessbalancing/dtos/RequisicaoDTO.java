package br.com.gian418.customersuccessbalancing.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoDTO {

    @NotEmpty(message = "É obrigatório informar os Gerentes")
    List<@Valid GerenteSucessoDTO> gerentes = new ArrayList<>();

    @NotEmpty(message = "É obrigatório informar os Clientes")
    List<ClienteDTO> clientes = new ArrayList<>();

    List<Integer> gerentesIndisponiveis = new ArrayList<>();

    public List<GerenteSucessoDTO> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<GerenteSucessoDTO> gerentes) {
        this.gerentes = gerentes;
    }

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    public List<Integer> getGerentesIndisponiveis() {
        return gerentesIndisponiveis;
    }

    public void setGerentesIndisponiveis(List<Integer> gerentesIndisponiveis) {
        this.gerentesIndisponiveis = gerentesIndisponiveis;
    }
}
