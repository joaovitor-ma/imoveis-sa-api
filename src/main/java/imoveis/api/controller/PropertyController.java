package imoveis.api.controller;

import imoveis.api.dto.PropertyDTO;
import imoveis.api.model.Property;
import imoveis.api.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    private PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> findAll() {
        return propertyService.findAll();
    }

    @GetMapping("/{id}")
    public Property findById(@PathVariable("id") Long id) {
        return propertyService.findById(id);
    }

    @PostMapping
    public Property create(@RequestBody PropertyDTO propertyDTO) {
        return propertyService.create(propertyDTO);
    }

    @PutMapping("/{id}")
    public Property edit(@PathVariable("id") Long id, @RequestBody PropertyDTO propertyDTO) {
        return propertyService.edit(id, propertyDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        propertyService.delete(id);
    }
}
