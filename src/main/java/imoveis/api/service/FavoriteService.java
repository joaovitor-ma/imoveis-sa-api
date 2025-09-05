package imoveis.api.service;

import imoveis.api.dto.FavoriteDTO;
import imoveis.api.model.Favorite;
import imoveis.api.model.Property;
import imoveis.api.model.User;
import imoveis.api.repository.FavoriteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final PropertyService propertyService;
    private final ModelMapper mapper = new ModelMapper();

    private FavoriteService(FavoriteRepository favoriteRepository, UserService userService, PropertyService propertyService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.propertyService = propertyService;
    }

    public Favorite addFavorite(FavoriteDTO favoriteDTO) {
        Long customerId = favoriteDTO.getCustomerId(); // Pegando o ID do usuário
        Long propertyId = favoriteDTO.getPropertyId(); // Pegando o ID do imóvel

        User user = userService.searchById(customerId); // Buscando o usuário baseado no ID
        Property property = propertyService.findById(propertyId); // Buscando o imóvel baseado no ID

        Favorite favoriteBody = new Favorite(); // Criando um novo favorite

        favoriteBody.setId(null); // Definindo ID como null para evitar possíveis erros
        favoriteBody.setCustomer(user); // Vinculando o ID de customer com a tabela de favorites
        favoriteBody.setProperty(property); // Vinculando o ID de property com a tabela de favorites

        user.getFavorites().add(property); // Atribuindo ao usuário um novo imóvel favorito

        return favoriteRepository.save(favoriteBody); // Salvando novo imóvel favorito no banco
    }

    public void deleteFavoriteByCustomer_IdAndProperty_Id(Long customerId, Long propertyId) {
        User user = userService.searchById(customerId); // Busca o usuário pelo customerId passado

        user.setFavorites(
                user
                .getFavorites() // Puxa a lista de favoritos
                .stream()
                .filter(p -> !p.getId().equals(propertyId)) // Filtra para deixar apenas os imóveis que tiverem o ID diferente de propertyId
                .toList() // Transforma em uma lista
        );

        favoriteRepository.deleteFavoriteByCustomer_IdAndProperty_Id(customerId, propertyId);
    }
}
