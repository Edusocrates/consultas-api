package med.voll.api.controller.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsultas {

    void validar(DadosAgendamentoConsulta dados);
}
