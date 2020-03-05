package it.healthnet.staff.domain.staff;

import it.healthnet.staff.domain.shared.ValueObject;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public final class Email implements ValueObject<Email> {
    private final String value;

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Email{" +
                "value='" + value + '\'' +
                '}';
    }
}
