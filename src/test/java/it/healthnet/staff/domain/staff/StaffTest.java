package it.healthnet.staff.domain.staff;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    @Test
    void constructGivenValidValues() {
        assertDoesNotThrow(() -> new Staff(
                new StaffId("96d3d6fe-f443-43f6-8989-afce006b288d"),
                new FullName("John Riley")
        ));
    }

    @Test
    void getId() {
        var uuid = new StaffId("96d3d6fe-f443-43f6-8989-afce006b288d");
        var fullName = new FullName("John Riley");
        var patient = new Staff(uuid, fullName);
        assertEquals(uuid, patient.getId());
    }

    @Test
    void getFullName() {
        var uuid = new StaffId("96d3d6fe-f443-43f6-8989-afce006b288d");
        var fullName = new FullName("John Riley");
        var patient = new Staff(uuid, fullName);
        assertEquals(fullName, patient.getFullName());
    }

    @Test
    void equalsTest() {
        EqualsVerifier.forClass(Staff.class).withOnlyTheseFields("id").verify();
    }

    @Test
    void toStringTest() {
        ToStringVerifier.forClass(Staff.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }
}