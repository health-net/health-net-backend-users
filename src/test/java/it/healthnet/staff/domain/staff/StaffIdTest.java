package it.healthnet.staff.domain.staff;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffIdTest {
    @Test
    void constructGivenValidValue() {
        assertDoesNotThrow(() -> new StaffId("96d3d6fe-f443-43f6-8989-afce006b288d"));
    }

    @Test
    void isValidGivenInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new StaffId("22ej8dja8-2ie23"));
    }

    @Test
    void getValue() {
        var uuid = new StaffId("96d3d6fe-f443-43f6-8989-afce006b288d");
        assertEquals("96d3d6fe-f443-43f6-8989-afce006b288d", uuid.getValue());
    }

    @Test
    void equals() {
        EqualsVerifier.forClass(StaffId.class).verify();
    }

    @Test
    void toStringTest() {
        ToStringVerifier.forClass(StaffId.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }
}