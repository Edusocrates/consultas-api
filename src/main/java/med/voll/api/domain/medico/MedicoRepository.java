package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medicos m
            WHERE m.ativo = 1
            AND m.especialidade = :especialidade
            AND m.id not in(
            SELECT c.medico.id FROM Consulta
            WHERE c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
             """)
    Medico escolherMedicoAleatorioLivreNaData(@Param("especialidade") Especialidade especialidade,
                                              @Param("data") LocalDateTime data);
}
