package ru.guideapp.api.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.guideapp.api.exception.NotFoundException;
import ru.guideapp.api.models.Objects;
import ru.guideapp.api.repositories.ObjectRepository;

import java.util.List;

@Repository
public class ObjectsService implements ObjectRepository {

    private final RowMapper<Objects> rowMapper = new DataClassRowMapper<>(Objects.class);
    private final JdbcTemplate jdbcTemplate;

    public ObjectsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Objects> readByStyleId(Long styleId) {
        return jdbcTemplate.query("SELECT * FROM Objects WHERE styleId = ?", rowMapper, styleId);
    }

    @Override
    public List<Objects> readByDistrictId(Long districtId) {
        return jdbcTemplate.query("SELECT * FROM Objects WHERE districtId = ?", rowMapper, districtId);
    }

    @Override
    public List<Objects> readByDistrictIdAndStyleId(Long districtId, Long styleId) {
        return jdbcTemplate.query("SELECT * FROM Objects WHERE districtId = ? AND styleId = ?", rowMapper, districtId, styleId);
    }

    @Override
    public List<Objects> readAll() {
        return jdbcTemplate.query("SELECT * FROM Objects", rowMapper);
    }

    @Override
    public Objects read(Long objectId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Objects WHERE objectId=?", rowMapper, objectId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Object with id = [" + objectId + "] not found", e);
        }
    }

    @Override
    public void save(Objects object) {
        jdbcTemplate.update("INSERT INTO Objects(name, description, address, districtId, pos1, pos2, pathToPhoto, pathToAudio, styleId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                object.name(), object.description(), object.address(), object.districtId(), object.pos1(), object.pos2(), object.pathToPhoto(), object.pathToAudio(), object.styleId());
    }

    @Override
    public void update(Long objectId, Objects object) {
        try {
            jdbcTemplate.update("UPDATE Objects SET name=?, description=?, address=?, districtId=?, pos1=?, pos2=?, pathToPhoto=?, pathToAudio=?, styleId=? WHERE objectId=?",
                    object.name(), object.description(), object.address(), object.districtId(), object.pos1(), object.pos2(), object.pathToPhoto(), object.pathToAudio(), object.styleId(), objectId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Object with id = [" + objectId + "] not found", e);
        }
    }

    @Override
    public void delete(Long objectId) {
        try {
            jdbcTemplate.update("DELETE FROM Objects WHERE objectId=?", objectId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Object with id = [" + objectId + "] not found", e);
        }
    }
}
