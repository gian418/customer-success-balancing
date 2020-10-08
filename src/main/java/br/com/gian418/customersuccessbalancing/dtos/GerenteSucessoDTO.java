package br.com.gian418.customersuccessbalancing.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class GerenteSucessoDTO {

    @NotNull(message = "É obrigatório informar o ID do Gerente")
    @Min(value = 0L, message = "Valor minimo da ID é 0")
    @Max(value = 1000L, message = "Valor maximo do ID é 1000")
    private Integer id;

    @NotNull(message = "É obrigatório informar o SCORE do Gerente")
    private Integer score;

    public GerenteSucessoDTO(Integer id, Integer score) {
        this.id = id;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
