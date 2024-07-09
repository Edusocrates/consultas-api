package med.voll.api.controller.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsultas {

    void validar(DadosCancelamentoConsulta dados);
}
