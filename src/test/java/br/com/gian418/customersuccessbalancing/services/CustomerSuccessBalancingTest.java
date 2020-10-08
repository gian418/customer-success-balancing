package br.com.gian418.customersuccessbalancing.services;

import br.com.gian418.customersuccessbalancing.dtos.ClienteDTO;
import br.com.gian418.customersuccessbalancing.dtos.GerenteSucessoDTO;
import br.com.gian418.customersuccessbalancing.dtos.RequisicaoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CustomerSuccessBalancingTest {

    @Autowired
    private CustomerSuccessBalancingService service;

    @Test
    public void cenarioUm() {
        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 90));
        clientes.add(new ClienteDTO(2, 20));
        clientes.add(new ClienteDTO(3, 70));
        clientes.add(new ClienteDTO(4, 40));
        clientes.add(new ClienteDTO(5, 60));
        clientes.add(new ClienteDTO(6, 10));

        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 60));
        gerentes.add(new GerenteSucessoDTO(2, 20));
        gerentes.add(new GerenteSucessoDTO(3, 95));
        gerentes.add(new GerenteSucessoDTO(4, 75));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setClientes(clientes);
        requisicao.setGerentes(gerentes);

        requisicao.setGerentesIndisponiveis(Arrays.asList(2, 4));

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 1);
    }

    @Test
    public void cenarioDois() {
        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 10));
        clientes.add(new ClienteDTO(2, 10));
        clientes.add(new ClienteDTO(3, 10));
        clientes.add(new ClienteDTO(4, 20));
        clientes.add(new ClienteDTO(5, 20));
        clientes.add(new ClienteDTO(6, 30));
        clientes.add(new ClienteDTO(7, 30));
        clientes.add(new ClienteDTO(8, 30));
        clientes.add(new ClienteDTO(9, 20));
        clientes.add(new ClienteDTO(10, 60));

        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 11));
        gerentes.add(new GerenteSucessoDTO(2, 21));
        gerentes.add(new GerenteSucessoDTO(3, 31));
        gerentes.add(new GerenteSucessoDTO(4, 3));
        gerentes.add(new GerenteSucessoDTO(5, 4));
        gerentes.add(new GerenteSucessoDTO(6, 5));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setClientes(clientes);
        requisicao.setGerentes(gerentes);

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 0);
    }

    @Test
    public void cenarioTres() {
        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        for(int i = 1; i <= 1000; i++) {
            gerentes.add(new GerenteSucessoDTO(i, 0));
        }
        gerentes.get(998).setScore(100);

        List<ClienteDTO> clientes = new ArrayList<>();
        for(int i = 1; i <= 10000; i++) {
            clientes.add(new ClienteDTO(i, 10));
        }

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setGerentes(gerentes);
        requisicao.setClientes(clientes);
        requisicao.setGerentesIndisponiveis(Arrays.asList(1000));

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 999);
    }

    @Test
    public void cenarioQuatro() {
        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 1));
        gerentes.add(new GerenteSucessoDTO(2, 2));
        gerentes.add(new GerenteSucessoDTO(3, 3));
        gerentes.add(new GerenteSucessoDTO(4, 4));
        gerentes.add(new GerenteSucessoDTO(5, 5));
        gerentes.add(new GerenteSucessoDTO(6, 6));

        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 10));
        clientes.add(new ClienteDTO(2, 10));
        clientes.add(new ClienteDTO(3, 10));
        clientes.add(new ClienteDTO(4, 20));
        clientes.add(new ClienteDTO(5, 20));
        clientes.add(new ClienteDTO(6, 30));
        clientes.add(new ClienteDTO(7, 30));
        clientes.add(new ClienteDTO(8, 30));
        clientes.add(new ClienteDTO(9, 20));
        clientes.add(new ClienteDTO(10, 60));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setGerentes(gerentes);
        requisicao.setClientes(clientes);

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 0);

    }

    @Test
    public void cenarioCinco() {
        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 100));
        gerentes.add(new GerenteSucessoDTO(2, 2));
        gerentes.add(new GerenteSucessoDTO(3, 3));
        gerentes.add(new GerenteSucessoDTO(4, 3));
        gerentes.add(new GerenteSucessoDTO(5, 4));
        gerentes.add(new GerenteSucessoDTO(6, 5));

        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 10));
        clientes.add(new ClienteDTO(2, 10));
        clientes.add(new ClienteDTO(3, 10));
        clientes.add(new ClienteDTO(4, 20));
        clientes.add(new ClienteDTO(5, 20));
        clientes.add(new ClienteDTO(6, 30));
        clientes.add(new ClienteDTO(7, 30));
        clientes.add(new ClienteDTO(8, 30));
        clientes.add(new ClienteDTO(9, 20));
        clientes.add(new ClienteDTO(10, 60));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setGerentes(gerentes);
        requisicao.setClientes(clientes);

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 1);
    }

    @Test
    public void cenarioSeis() {
        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 100));
        gerentes.add(new GerenteSucessoDTO(2, 99));
        gerentes.add(new GerenteSucessoDTO(3, 88));
        gerentes.add(new GerenteSucessoDTO(4, 3));
        gerentes.add(new GerenteSucessoDTO(5, 4));
        gerentes.add(new GerenteSucessoDTO(6, 5));

        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 10));
        clientes.add(new ClienteDTO(2, 10));
        clientes.add(new ClienteDTO(3, 10));
        clientes.add(new ClienteDTO(4, 20));
        clientes.add(new ClienteDTO(5, 20));
        clientes.add(new ClienteDTO(6, 30));
        clientes.add(new ClienteDTO(7, 30));
        clientes.add(new ClienteDTO(8, 30));
        clientes.add(new ClienteDTO(9, 20));
        clientes.add(new ClienteDTO(10, 60));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setGerentes(gerentes);
        requisicao.setClientes(clientes);
        requisicao.setGerentesIndisponiveis(Arrays.asList(1, 3, 2));

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 0);
    }

    @Test
    public void cenarioSete() {
        List<GerenteSucessoDTO> gerentes = new ArrayList<>();
        gerentes.add(new GerenteSucessoDTO(1, 100));
        gerentes.add(new GerenteSucessoDTO(2, 99));
        gerentes.add(new GerenteSucessoDTO(3, 88));
        gerentes.add(new GerenteSucessoDTO(4, 3));
        gerentes.add(new GerenteSucessoDTO(5, 4));
        gerentes.add(new GerenteSucessoDTO(6, 5));

        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(new ClienteDTO(1, 10));
        clientes.add(new ClienteDTO(2, 10));
        clientes.add(new ClienteDTO(3, 10));
        clientes.add(new ClienteDTO(4, 20));
        clientes.add(new ClienteDTO(5, 20));
        clientes.add(new ClienteDTO(6, 30));
        clientes.add(new ClienteDTO(7, 30));
        clientes.add(new ClienteDTO(8, 30));
        clientes.add(new ClienteDTO(9, 20));
        clientes.add(new ClienteDTO(10, 60));

        RequisicaoDTO requisicao = new RequisicaoDTO();
        requisicao.setGerentes(gerentes);
        requisicao.setClientes(clientes);
        requisicao.setGerentesIndisponiveis(Arrays.asList(4, 5, 6));

        Integer gerenteId = service.balancear(requisicao);
        Assert.isTrue(gerenteId == 3);
    }
}
