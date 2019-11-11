package it.healthnet.staff.domain.staff;

import it.healthnet.staff.domain.shared.ValueObject;

import java.util.Objects;

public final class StaffId implements ValueObject<StaffId> {
    private final String value;

    public StaffId(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String uuid) {
        if(!isUuid(uuid)) {
            throw new IllegalArgumentException("The value specified for the staff's id is invalid");
        }
    }

    private static boolean isUuid(String value) {
        var regex = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})";
        return value.matches(regex);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffId)) return false;
        StaffId uuid = (StaffId) o;
        return Objects.equals(value, uuid.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "StaffId{" +
                "value='" + value + '\'' +
                '}';
    }
}
