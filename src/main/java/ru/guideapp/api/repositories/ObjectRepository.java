package ru.guideapp.api.repositories;

import ru.guideapp.api.models.Objects;

import java.util.List;

public interface ObjectRepository {
    List<Objects> readByStyleId(Long styleId);
    List<Objects> readByDistrictId(Long districtId);
    List<Objects> readByDistrictIdAndStyleId(Long districtId, Long styleId);
    List<Objects> readAll();
    Objects read(Long objectId);
    void save(Objects object);
    void update(Long objectId, Objects object);
    void delete(Long objectId);
}
