package br.com.gian418.customersuccessbalancing.services;

import br.com.gian418.customersuccessbalancing.dtos.ClienteDTO;
import br.com.gian418.customersuccessbalancing.dtos.GerenteSucessoDTO;
import br.com.gian418.customersuccessbalancing.dtos.RequisicaoDTO;
import br.com.gian418.customersuccessbalancing.services.aux.GerenteQuantidadeAux;
import br.com.gian418.customersuccessbalancing.services.exceptions.MaximoGenretesIndisponiveisException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerSuccessBalancingService {

    public Integer balancear(RequisicaoDTO requisicao) {
        this.validarQuantidadeGerentesIndisponiveis(requisicao);

        List<ClienteDTO> clientes = requisicao.getClientes()
                .stream().sorted(Comparator.comparingInt(ClienteDTO::getScore)).collect(Collectors.toList());

        List<GerenteSucessoDTO> gerentes = requisicao.getGerentes()
                .stream().sorted(Comparator.comparingInt(GerenteSucessoDTO::getScore)).collect(Collectors.toList());

        Map<ClienteDTO, GerenteSucessoDTO> clienteGerenteMap = new HashMap<>();

        for(ClienteDTO cliente : clientes) {
            GerenteSucessoDTO gerenteAtual =
                    this.verificarGerenteAdequado(gerentes, requisicao.getGerentesIndisponiveis(), cliente);

            if(gerenteAtual != null) {
                clienteGerenteMap.put(cliente, gerenteAtual);
            }
        }

        return gerenteComMaisClientes(clienteGerenteMap);
    }

    private GerenteSucessoDTO verificarGerenteAdequado(List<GerenteSucessoDTO> gerentes, List<Integer> gerentesIndisponiveis, ClienteDTO cliente) {
        Integer diferencaSubtracaoAux = null;
        GerenteSucessoDTO gerenteAtual = null;

        for(GerenteSucessoDTO gerente : gerentes) {
            if(gerentesIndisponiveis.contains(gerente.getId())) continue;
            if(cliente.getScore() > gerente.getScore()) continue;

            Integer diferencaSubtracao = gerente.getScore() - cliente.getScore();
            if(diferencaSubtracaoAux == null) {
                diferencaSubtracaoAux = diferencaSubtracao;
                gerenteAtual = gerente;
            }

            if(diferencaSubtracaoAux > diferencaSubtracao) {
                diferencaSubtracaoAux = diferencaSubtracao;
                gerenteAtual = gerente;
            }
        }

        return gerenteAtual;
    }

    private Integer gerenteComMaisClientes(Map<ClienteDTO, GerenteSucessoDTO> clienteGerenteMap) {
        if(clienteGerenteMap.isEmpty()) return 0;

        Map<GerenteSucessoDTO, Integer> gerenteQuantidadeMap = this.calcularClientesPorGerente(clienteGerenteMap);

        Set<GerenteQuantidadeAux> gerentesQuantidadeAux = new HashSet<>();
        for(GerenteSucessoDTO gerente : gerenteQuantidadeMap.keySet()) {
            gerentesQuantidadeAux.add(new GerenteQuantidadeAux(gerente, gerenteQuantidadeMap.get(gerente)));
        }

        return obterGerenteIdComMaisClientes(this.obterGerentesComMaisClientes(gerentesQuantidadeAux));
    }

    private Map<GerenteSucessoDTO, Integer> calcularClientesPorGerente(Map<ClienteDTO, GerenteSucessoDTO> clienteGerenteMap) {
        Map<GerenteSucessoDTO, Integer> gerenteQuantidadeMap = new HashMap<>();

        for(GerenteSucessoDTO gerente : clienteGerenteMap.values()) {
            if(!gerenteQuantidadeMap.containsKey(gerente)) {
                gerenteQuantidadeMap.put(gerente, 1);
                continue;
            }

            gerenteQuantidadeMap.put(gerente, gerenteQuantidadeMap.get(gerente) + 1);
        }

        return gerenteQuantidadeMap;
    }

    private Set<GerenteQuantidadeAux> obterGerentesComMaisClientes(Set<GerenteQuantidadeAux> gerentesQuantidadeAux) {
        Integer totalAux = gerentesQuantidadeAux.stream()
                .max(Comparator.comparingInt(GerenteQuantidadeAux::getQuantidade)).get().getQuantidade();

        List<GerenteQuantidadeAux> gerentesRemoverAux = new ArrayList<>();
        for(GerenteQuantidadeAux gerente : gerentesQuantidadeAux) {
            if(gerente.getQuantidade() < totalAux) {
                gerentesRemoverAux.add(gerente);
            }
        }

        gerentesQuantidadeAux.removeAll(gerentesRemoverAux);
        return gerentesQuantidadeAux;
    }

    private Integer obterGerenteIdComMaisClientes(Set<GerenteQuantidadeAux> gerentesQuantidadeAux) {
        return gerentesQuantidadeAux.size() == 1 ? gerentesQuantidadeAux.stream().findFirst().get().getGerente().getId() : 0;
    }

    private void validarQuantidadeGerentesIndisponiveis(RequisicaoDTO requisicaoDTO) {
        if(!requisicaoDTO.getGerentesIndisponiveis().isEmpty()) {
            Integer quantidadeGerentes = requisicaoDTO.getGerentes().size();
            Integer quantidadeGerentesIndisponiveis = requisicaoDTO.getGerentesIndisponiveis().size();
            Double qtdeMaxPermitidaIndisponiveis = Math.floor(quantidadeGerentes/2);

            if(quantidadeGerentesIndisponiveis > qtdeMaxPermitidaIndisponiveis) {
                throw new MaximoGenretesIndisponiveisException("Quantidade máxima de gerentes indisponiveis ultrapassada");
            }
        }

    }

}
