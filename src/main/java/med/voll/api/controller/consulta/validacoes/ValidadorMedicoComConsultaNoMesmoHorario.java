package med.voll.api.controller.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exceptions.ValidacaoException;

public class ValidadorMedicoComConsultaNoMesmoHorario {

    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        var medicoPossuiOutraConsulta = repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if(medicoPossuiOutraConsulta){
            throw new ValidacaoException("Medico ja possui outra consulta no mesmo horario!");
        }
    }
}
