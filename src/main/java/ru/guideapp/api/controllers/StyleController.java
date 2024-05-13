package ru.guideapp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.guideapp.api.models.Styles;
import ru.guideapp.api.repositories.StyleRepository;

import java.util.List;

@RestController
@RequestMapping("api/styles")
public class StyleController {
    private final StyleRepository styleRepository;

    public StyleController(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @GetMapping
    public List<Styles> getStyle() {
        return styleRepository.readAll();
    }

    @GetMapping("/{style_id}")
    public Styles getStyle(@PathVariable("style_id") Long styleId) {
        return styleRepository.read(styleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStyle(@RequestParam("name") String name,
                         @RequestParam("description") String description){
        Styles newStyle = new Styles(null, name, description);
        styleRepository.save(newStyle);
    }

    @PutMapping("/{style_id}")
    public void updateStyle(
            @PathVariable("style_id") Long styleId,
            @RequestParam("name") String name,
            @RequestParam("description") String description) {

        Styles updatedStyle = new Styles(
                styleId,
                name,
                description
        );
        styleRepository.update(styleId, updatedStyle);
    }

    @DeleteMapping("/{style_id}")
    public void deleteStyle(@PathVariable("style_id") Long styleId) {
        styleRepository.delete(styleId);
    }
}
