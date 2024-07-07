package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c WHERE c.id = :idConsulta AND c.data < :dateTimeThreshold")
    Optional<Consulta> validaCancelamentoConsulta(@Param("idConsulta") Long idConsulta, @Param("dateTimeThreshold") LocalDateTime data);


    @Modifying
    @Transactional
    @Query("UPDATE Consulta c SET c.motivoCancelamento = :motivo WHERE c.id = :idConsulta")
    void cancelarConsulta(@Param("idConsulta") Long idConsulta, @Param("motivo") MotivoCancelamento motivo);
}
