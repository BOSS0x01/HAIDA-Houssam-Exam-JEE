package haida.houssam.backendexam.repositories;

import haida.houssam.backendexam.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
