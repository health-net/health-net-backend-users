package it.healthnet.staff.domain.staff;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {

    @Test
    void constructGivenValidValue() {
        assertDoesNotThrow(() -> new FullName("John Riley"));
    }

    @Test
    void constructGivenTooLongValue() {
        var tooLongFullName = IntStream.range(0, FullName.MAX_LENGTH+1).mapToObj(i -> "a").collect(Collectors.joining());
        assertThrows(IllegalArgumentException.class, () -> new FullName(tooLongFullName));
    }

    @Test
    void getValue() {
        var fullName = new FullName("John Riley");
        assertEquals("John Riley", fullName.getValue());
    }

    @Test
    void equals() {
        EqualsVerifier.forClass(FullName.class).verify();
    }

    @Test
    void toStringTest() {
        ToStringVerifier.forClass(FullName.class).withClassName(NameStyle.SIMPLE_NAME).verify();
    }
}