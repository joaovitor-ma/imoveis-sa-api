package imoveis.api.repository;

import imoveis.api.model.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    public List<Favorite> findFavoritesByCustomer_Id(Long id);
    @Modifying
    @Transactional
    public void deleteFavoriteByCustomer_IdAndProperty_Id(Long customerId, Long propertyId);
}
