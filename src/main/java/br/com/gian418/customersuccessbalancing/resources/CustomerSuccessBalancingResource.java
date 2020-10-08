package br.com.gian418.customersuccessbalancing.resources;

import br.com.gian418.customersuccessbalancing.dtos.RequisicaoDTO;
import br.com.gian418.customersuccessbalancing.services.CustomerSuccessBalancingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class CustomerSuccessBalancingResource {

    @Autowired
    CustomerSuccessBalancingService service;

    @ApiOperation("Retorna o id do gerente com mais clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mensagem de sucesso"),
            @ApiResponse(code = 400, message = "Problema em algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/balancear", method = RequestMethod.POST)
    public ResponseEntity balancear(@RequestBody @Valid RequisicaoDTO requisicaoDTO) {
        Integer gerenteId = service.balancear(requisicaoDTO);
        return ResponseEntity.ok().body(gerenteId);
    }
}
