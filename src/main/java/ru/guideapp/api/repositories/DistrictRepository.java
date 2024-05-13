package ru.guideapp.api.repositories;

import ru.guideapp.api.models.Districts;

import java.util.List;

public interface DistrictRepository {
    List<Districts> readAll();
    Districts read(Long districtId);
    void save(Districts district);
    void update(Long districtId, Districts district);
    void delete(Long districtId);
}
