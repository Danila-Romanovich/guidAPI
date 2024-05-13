package ru.guideapp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.guideapp.api.models.Districts;
import ru.guideapp.api.repositories.DistrictRepository;

import java.util.List;

@RestController
@RequestMapping("api/districts")
public class DistrictController {
    private final DistrictRepository districtRepository;

    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @GetMapping
    public List<Districts> getDistrict() {
        return districtRepository.readAll();
    }

    @GetMapping("/{district_id}")
    public Districts getDistrict(@PathVariable("district_id") Long districtId) {
        return districtRepository.read(districtId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDistrict(@RequestParam("name") String name,
                         @RequestParam("description") String description){
        Districts newDistrict = new Districts(null, name, description);
        districtRepository.save(newDistrict);
    }

    @PutMapping("/{district_id}")
    public void updateDistrict(
            @PathVariable("district_id") Long districtId,
            @RequestParam("name") String name,
            @RequestParam("description") String description) {

        Districts updatedDistrict = new Districts(
                districtId,
                name,
                description
        );
        districtRepository.update(districtId, updatedDistrict);
    }

    @DeleteMapping("/{district_id}")
    public void deleteDistrict(@PathVariable("district_id") Long districtId) {
        districtRepository.delete(districtId);
    }
}
