package it.healthnet.staff.domain.staff;

import it.healthnet.staff.domain.shared.AggregateRoot;

import java.util.Objects;

public final class Staff implements AggregateRoot<Staff> {
    private final StaffId id;
    private final FullName fullName;
    private final Email email;
    private final String password;

    public Staff(StaffId id, FullName fullName) {
        this.id = id;
        this.fullName = fullName;
        this.password = null;
        this.email = null;
    }

    public Staff(StaffId id, FullName fullName, Email email){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = null;
    }

    public Staff(Email email, String password, FullName fullName){
        this.email = email;
        this.password = password;
        this.id = null;
        this.fullName = fullName;
    }

    public StaffId getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", fullName=" + fullName +
                '}';
    }
}
