package ru.guideapp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.guideapp.api.models.Objects;
import ru.guideapp.api.repositories.ObjectRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/objects")
public class ObjectController {
    private final ObjectRepository objectRepository;

    public ObjectController(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Objects> getObj(
            @RequestParam(name = "styleId", required = false) Long styleId,
            @RequestParam(name = "districtId", required = false) Long districtId
    ) {
        if (styleId == null && districtId == null) {
            return objectRepository.readAll();
        } else if (styleId == null && districtId != null) {
            return objectRepository.readByDistrictId(districtId);
        } else if (styleId != null && districtId != null){
            return  objectRepository.readByDistrictIdAndStyleId(districtId,styleId);
        } else return objectRepository.readByStyleId(styleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addObj(
            @RequestParam(name = "styleId") Long styleId,
            @RequestParam(name = "districtId") Long districtId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "pos1") Double pos1,
            @RequestParam(name = "pos2") Double pos2,
            @RequestParam(name = "pathToPhoto", required = false) String pathToPhoto,
            @RequestParam(name = "pathToAudio", required = false) String pathToAudio
    ) {
        Objects newObject = new Objects(null, name, description, address, districtId, pos1, pos2, pathToPhoto, pathToAudio, styleId);
        objectRepository.save(newObject);
    }

    @PutMapping("/{object_id}")
    public void updateObj(
            @PathVariable("object_id") Long objectId,
            @RequestParam(name = "styleId") Long styleId,
            @RequestParam(name = "districtId") Long districtId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "pos1") Double pos1,
            @RequestParam(name = "pos2") Double pos2,
            @RequestParam(name = "pathToPhoto", required = false) String pathToPhoto,
            @RequestParam(name = "pathToAudio", required = false) String pathToAudio
            ) {
        Objects updatedObject = new Objects(
                objectId,
                name,
                description,
                address,
                districtId,
                pos1,
                pos2,
                pathToPhoto,
                pathToAudio,
                styleId
        );
        objectRepository.update(objectId, updatedObject);
    }


    @DeleteMapping("/{object_id}")
    public void deleteObj(@PathVariable("object_id") Long objectId) {objectRepository.delete(objectId);}


}
