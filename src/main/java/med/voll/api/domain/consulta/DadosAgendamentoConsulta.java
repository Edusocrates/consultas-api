    package med.voll.api.domain.consulta;

    import jakarta.validation.constraints.Future;
    import jakarta.validation.constraints.NotNull;
    import lombok.Data;

    import java.time.LocalDateTime;

    public record DadosAgendamentoConsulta(


            Long idMedico,

            @NotNull
            Long idPaciente,

            @Future
            @NotNull
            LocalDateTime data


    ) {
    }
