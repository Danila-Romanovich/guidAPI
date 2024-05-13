package ru.guideapp.api.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.guideapp.api.exception.NotFoundException;
import ru.guideapp.api.models.Districts;
import ru.guideapp.api.repositories.DistrictRepository;

import java.util.List;

@Repository
public class DistrictService implements DistrictRepository {

    private final RowMapper<Districts> rowMapper = new DataClassRowMapper<>(Districts.class);
    private final JdbcTemplate jdbcTemplate;

    public DistrictService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Districts> readAll() {
        return jdbcTemplate.query("SELECT * FROM Districts", rowMapper);
    }

    @Override
    public Districts read(Long districtId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Districts WHERE districtId=?", rowMapper, districtId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("District with id = [" + districtId + "] not found", e);
        }
    }

    @Override
    public void save(Districts district) {
        jdbcTemplate.update("INSERT INTO Districts(name, description) VALUES(?, ?)", district.name(), district.description());
    }

    @Override
    public void update(Long districtId, Districts district) {
        try {
            jdbcTemplate.update("UPDATE Districts SET name=?, description=? WHERE districtId=?",
                    district.name(), district.description(), districtId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("District with id = [" + districtId + "] not found", e);
        }
    }

    @Override
    public void delete(Long districtId) {
        try {
            jdbcTemplate.update("DELETE FROM Districts WHERE districtId=?", districtId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("District with id = [" + districtId + "] not found", e);
        }
    }
}
