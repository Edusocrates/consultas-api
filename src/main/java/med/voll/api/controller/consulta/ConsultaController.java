package med.voll.api.controller.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping("/nova-consulta")
    @Transactional
    public ResponseEntity agender(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println("Criando um novo agendamento de consulta! para o paciente: {}"+ dados.idPaciente());
        agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null,null,null,null));
    }

}
