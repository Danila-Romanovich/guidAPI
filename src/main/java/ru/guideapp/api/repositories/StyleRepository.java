package ru.guideapp.api.repositories;

import org.springframework.stereotype.Repository;
import ru.guideapp.api.models.Styles;

import java.util.List;

public interface StyleRepository {
    List<Styles> readAll();
    Styles read(Long styleId);
    void save(Styles style);
    void update(Long styleId, Styles style);
    void delete(Long styleId);
}
