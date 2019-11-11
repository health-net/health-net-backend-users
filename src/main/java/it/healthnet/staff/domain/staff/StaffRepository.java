package it.healthnet.staff.domain.staff;

import it.healthnet.staff.domain.shared.Repository;

import java.util.List;

public interface StaffRepository extends Repository<Staff> {
    void add(Staff staff);
    void remove(Staff staff);
    Staff get(StaffId id);
    List<Staff> getAll();
    boolean contains(Staff staff);
}
