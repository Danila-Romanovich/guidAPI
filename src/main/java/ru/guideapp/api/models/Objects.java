package ru.guideapp.api.models;

public record Objects(
        Long objectId,
        String name,
        String description,
        String address,
        Long districtId,
        double pos1,
        double pos2,
        String pathToPhoto,
        String pathToAudio,
        Long styleId) {
}
