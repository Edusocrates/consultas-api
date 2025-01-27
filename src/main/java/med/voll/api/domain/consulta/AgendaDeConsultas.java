package med.voll.api.domain.consulta;

import med.voll.api.controller.consulta.validacoes.agendamento.ValidadorAgendamentoConsultas;
import med.voll.api.controller.consulta.validacoes.cancelamento.ValidadorCancelamentoConsultas;
import med.voll.api.domain.exceptions.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoConsultas> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente não encontrado!");
        }

        if(dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Medico não encontrado!");
        }

        //percorre todos os validadores de regras de negocio
        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Necessário escolher uma especialidade!");
        }

        var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
        if(medico == null){
            throw new ValidacaoException("Não possui medico disponivel nessa Data!");
        }

        return medico;
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        validadoresCancelamento.forEach(v -> v.validar(dados));

        consultaRepository.cancelarConsulta(dados.idConsulta(),dados.motivo());

    }
}
