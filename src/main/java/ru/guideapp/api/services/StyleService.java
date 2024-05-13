package ru.guideapp.api.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.guideapp.api.exception.NotFoundException;
import ru.guideapp.api.models.Styles;
import ru.guideapp.api.repositories.StyleRepository;

import java.util.List;

@Repository
public class StyleService implements StyleRepository {

    private final RowMapper<Styles> rowMapper = new DataClassRowMapper<>(Styles.class);
    private final JdbcTemplate jdbcTemplate;

    public StyleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Styles> readAll() {
        return jdbcTemplate.query("SELECT * FROM Styles", rowMapper);
    }

    @Override
    public Styles read(Long styleId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Styles WHERE styleId=?", rowMapper, styleId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Style with id = [" + styleId + "] not found", e);
        }
    }

    @Override
    public void save(Styles style) {
        jdbcTemplate.update("INSERT INTO Styles(name, discription) VALUES(?, ?)", style.name(), style.description());
    }

    @Override
    public void update(Long styleId, Styles style) {
        try {
            jdbcTemplate.update("UPDATE Styles SET name=?, discription=? WHERE styleId=?",
                    style.name(), style.description(), styleId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Style with id = [" + styleId + "] not found", e);
        }
    }

    @Override
    public void delete(Long styleId) {
        try {
            jdbcTemplate.update("DELETE FROM Styles WHERE styleId=?", styleId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Style with id = [" + styleId + "] not found", e);
        }
    }
}
