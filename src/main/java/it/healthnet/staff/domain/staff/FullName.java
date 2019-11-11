package it.healthnet.staff.domain.staff;

import it.healthnet.staff.domain.shared.ValueObject;

import java.util.Objects;

public final class FullName implements ValueObject<FullName> {
    public static final int MAX_LENGTH = 500;
    private final String value;

    public FullName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String fullName) {
        if(isTooLong(fullName)) {
            throw new IllegalArgumentException("The value specified for the full name is too long");
        }
    }

    private static boolean isTooLong(String fullName) {
        return fullName.length() > FullName.MAX_LENGTH;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullName)) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(value, fullName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FullName{" +
                "value='" + value + '\'' +
                '}';
    }
}
