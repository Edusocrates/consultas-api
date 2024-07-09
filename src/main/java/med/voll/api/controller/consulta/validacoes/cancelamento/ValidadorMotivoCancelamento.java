package med.voll.api.controller.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import med.voll.api.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamento implements ValidadorCancelamentoConsultas{
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        if(dados.motivo() == null){
            throw new ValidacaoException("é necessário informar um motivo para o cancelamento!");
        }
        if(!dados.motivo().equals(MotivoCancelamento.paciente_desistiu)
        || !dados.motivo().equals(MotivoCancelamento.medico_cancelou)
        || dados.motivo().equals(MotivoCancelamento.outros)){
            throw new ValidacaoException("Necessário informar um motivo valido!");
        }

    }
}
