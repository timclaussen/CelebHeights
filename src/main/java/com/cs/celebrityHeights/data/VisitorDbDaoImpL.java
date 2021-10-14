package com.cs.celebrityHeights.data;

import com.cs.celebrityHeights.models.Visitor;
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
public class VisitorDbDaoImpL implements VisitorDbDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Visitor> getAllVisitors() {
        final String GET_ALL_VISITORS = "SELECT * FROM Visitor;";
        return jdbc.query(GET_ALL_VISITORS, new VistorMapper());
    }

    @Override
    public Visitor getVisitorById(int id) {
        try {
            final String GET_Visitor_BY_ID = "SELECT * FROM Visitor WHERE visitorId = ?;";
            return jdbc.queryForObject(GET_Visitor_BY_ID, new VistorMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Visitor addVisitor(Visitor visit) {
        final String INSERT_VISITOR = "INSERT INTO Visitor(totalInches, entryTime) "
                + "VALUES(?,?);";
        java.sql.Date sqlDate = java.sql.Date.valueOf(visit.getEntryTime().toLocalDate());
        jdbc.update(INSERT_VISITOR,
                visit.getTotalInches(),
                //                visit.getEntryTime());
                sqlDate);
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        visit.setVisitorId(newId);
        return visit;
    }

    @Override
    @Transactional
    public Visitor editVisitor(Visitor visit) {
        final String UPDATE_VISITOR = "UPDATE Visitor SET totalInches = ?, entryTime = ? WHERE visitorId = ?;";
        java.sql.Date sqlDate = java.sql.Date.valueOf(visit.getEntryTime().toLocalDate());
        jdbc.update(UPDATE_VISITOR,
                visit.getTotalInches(),
                sqlDate,
                visit.getVisitorId());
        return visit;
    }

    @Override
    @Transactional
    public void removeVisitorById(int id) {
        final String DELETE_VISITOR = "DELETE * FROM Visitor WHERE visitorId = ?;";
        jdbc.update(DELETE_VISITOR, id);
    }

//Mapping
    public static final class VistorMapper implements RowMapper<Visitor> {

        @Override
        public Visitor mapRow(ResultSet rs, int index) throws SQLException {
            Visitor visitor = new Visitor();
            visitor.setVisitorId(rs.getInt("visitorId"));
            visitor.setTotalInches(rs.getInt("totalInches"));
            visitor.setEntryTime(LocalDateTime.parse(rs.getString("entryTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return visitor;
        }
    }

}
