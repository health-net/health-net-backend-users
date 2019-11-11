package it.healthnet.staff.application.repositories;

import it.healthnet.staff.data.mappers.StaffMapper;
import it.healthnet.staff.domain.staff.Staff;
import it.healthnet.staff.domain.staff.StaffRepository;
import it.healthnet.staff.domain.staff.StaffId;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public final class StaffPersistenceRepository implements StaffRepository {
    private final StaffMapper staffMapper;

    public StaffPersistenceRepository(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public void add(Staff staff) {
        if(contains(staff)) {
            throw new IllegalStateException("The staff already exists");
        }
        staffMapper.insert(staff);
    }

    @Override
    public boolean contains(Staff staff) {
        return staffMapper.selectById(staff.getId()).size() > 0;
    }

    @Override
    public void remove(Staff staff) {
        if(contains(staff)) {
            staffMapper.delete(staff);
        }
    }

    @Override
    public Staff get(StaffId id) {
        var patients = staffMapper.selectById(id);
        if(patients.size() == 0) {
            throw new NoSuchElementException("The staff does not exist");
        }
        return patients.get(0);
    }

    @Override
    public List<Staff> getAll() {
        return staffMapper.selectAll();
    }
}
