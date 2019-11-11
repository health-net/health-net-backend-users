package it.healthnet.staff.data.mappers;

import it.healthnet.staff.data.Database;
import it.healthnet.staff.domain.staff.FullName;
import it.healthnet.staff.domain.staff.Staff;
import it.healthnet.staff.domain.staff.StaffId;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public final class StaffMapper {
    private final Database database;

    public StaffMapper(Database database) {
        this.database = database;
    }

    public void insert(Staff staff) {
        try (var connection = database.getConnection();
             var statement = connection.prepareStatement("INSERT INTO `staff` VALUES (?, ?);")) {
            statement.setString(1, staff.getId().getValue());
            statement.setString(2, staff.getFullName().getValue());
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Staff staff) {
        try (var connection = database.getConnection();
             var statement = connection.prepareStatement("DELETE FROM `staff` WHERE id=?;")) {
            statement.setString(1, staff.getId().getValue());
            boolean execute = statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Staff> selectById(StaffId id) {
        try (var connection = database.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM `staff` WHERE id=?;")) {
            statement.setString(1, id.getValue());
            ResultSet result = statement.executeQuery();
            return getPatientsFromResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Staff> selectAll() {
        try (var connection = database.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM `staff`;")) {
            ResultSet result = statement.executeQuery();
            return getPatientsFromResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Staff> getPatientsFromResultSet(ResultSet resultSet) throws SQLException {
        var patients = new LinkedList<Staff>();
        while(resultSet.next()) {
            patients.add(new Staff(
                    new StaffId(resultSet.getString(1)),
                    new FullName(resultSet.getString(2))
            ));
        }
        return patients;
    }
}
