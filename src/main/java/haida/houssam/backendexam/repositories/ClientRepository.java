package haida.houssam.backendexam.repositories;

import haida.houssam.backendexam.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
