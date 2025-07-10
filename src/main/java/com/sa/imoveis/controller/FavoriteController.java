package com.sa.imoveis.controller;

import com.sa.imoveis.dto.FavoriteDTO;
import com.sa.imoveis.model.Favorite;
import com.sa.imoveis.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    private FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        return favoriteService.addFavorite(favoriteDTO);
    }

    @DeleteMapping("/{customer_id}/{property_id}")
    public void deleteFavoriteByCustomer_IdAndProperty_Id(@PathVariable("customer_id") Long customerId, @PathVariable("property_id") Long propertyId) {
        favoriteService.deleteFavoriteByCustomer_IdAndProperty_Id(customerId, propertyId);
    }
}
