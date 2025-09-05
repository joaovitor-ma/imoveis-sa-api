package imoveis.api.repository;

import imoveis.api.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
// TODO: Para o futuro: implementar filtros baseados na localização (cidade, estado, rua e etc...)
}
