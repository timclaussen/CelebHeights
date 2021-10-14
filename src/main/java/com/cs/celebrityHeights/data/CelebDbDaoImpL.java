/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.celebrityHeights.data;

import com.cs.celebrityHeights.models.Celeb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Timothy Claussen
 */
@Repository
public class CelebDbDaoImpL implements CelebDbDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Celeb> listAllCelebs() {
        final String GET_ALL_CELEB = "SELECT * FROM Celeb;";
        return jdbc.query(GET_ALL_CELEB, new CelebMapper());
    }

    @Override
    public Celeb getCelebById(int id) {
        try {
            final String GET_Celeb_BY_ID = "SELECT * FROM Celeb WHERE CelebId = ?;";
            return jdbc.queryForObject(GET_Celeb_BY_ID, new CelebMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Celeb addCeleb(Celeb celeb) {
        final String INSERT_CELEB = "INSERT INTO Celeb(celebName, totalInches, totalInchesString, searchUrl, PhotoUrl, DateSearched) "
                + "VALUES(?,?,?,?,?,?);";
        java.sql.Date sqlDate = java.sql.Date.valueOf(celeb.getDateSearched().toLocalDate());
        jdbc.update(INSERT_CELEB,
                celeb.getCelebName(),
                celeb.getTotalInches(),
                celeb.getTotalInchesString(),
                celeb.getSearchUrl(),
                celeb.getPhotoUrl(),
                sqlDate);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        celeb.setCelebId(newId);
        return celeb;
    }

    @Override
    @Transactional
    public Celeb editCeleb(Celeb celeb) {
        final String UPDATE_CELEB = "UPDATE Celeb SET celebName =?, totalInches = ?, totalInchesString = ?, searchUrl = ?, PhotoUrl = ?, DateSearched = ? WHERE CelebId = ?;";
        java.sql.Date sqlDate = java.sql.Date.valueOf(celeb.getDateSearched().toLocalDate());
        jdbc.update(UPDATE_CELEB,
                celeb.getCelebName(),
                celeb.getTotalInches(),
                celeb.getTotalInchesString(),
                celeb.getSearchUrl(),
                celeb.getPhotoUrl(),
                sqlDate,
                celeb.getCelebId());

        return celeb;
    }

    @Override
    @Transactional
    public void removeCelebById(int id) {
        final String DELETE_VISITOR = "DELETE FROM Celeb WHERE CelebId = ?;";
        jdbc.update(DELETE_VISITOR, id);
    }

    //Mapping
    public static final class CelebMapper implements RowMapper<Celeb> {

        @Override
        public Celeb mapRow(ResultSet rs, int index) throws SQLException {
            Celeb celeb = new Celeb();
            celeb.setCelebId(rs.getInt("CelebId"));
            celeb.setCelebName(rs.getString("celebName"));
            celeb.setTotalInches(rs.getInt("totalInches"));
            celeb.setTotalInchesString(rs.getString("totalInchesString"));
            celeb.setDateSearched(LocalDateTime.parse(rs.getString("DateSearched"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            celeb.setSearchUrl(rs.getString("searchUrl"));
            celeb.setSearchUrl(rs.getString("PhotoUrl"));
            return celeb;
        }
    }
}
