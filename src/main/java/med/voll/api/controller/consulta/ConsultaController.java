package med.voll.api.controller.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping("/nova-consulta")
    @Transactional
    public ResponseEntity agender(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println("Criando um novo agendamento de consulta! para o paciente: {}"+ dados.idPaciente());
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/cancelar-consulta")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        System.out.println("Cancelando uma consulta! consulta: {}"+ dados.idConsulta());
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


}
