package it.healthnet.staff.application.services;

import it.healthnet.staff.application.dtos.StaffDto;
import it.healthnet.staff.domain.staff.FullName;
import it.healthnet.staff.domain.staff.Staff;
import it.healthnet.staff.domain.staff.StaffRepository;
import it.healthnet.staff.domain.staff.StaffId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void create(StaffDto staffDto) {
        staffRepository.add(convertDtoToPatient(staffDto));
    }

    public void delete(String id) {
        var staff = staffRepository.get(new StaffId(id));
        staffRepository.remove(staff);
    }

    public StaffDto getOneById(String id) {
        return convertStaffToDto(staffRepository.get(new StaffId(id)));
    }

    public List<StaffDto> getAll() {
        var staffList = staffRepository.getAll();
        return staffList.stream().map(staff -> convertStaffToDto(staff)).collect(Collectors.toList());
    }

    private Staff convertDtoToPatient(StaffDto staffDto) {
        return new Staff(
                new StaffId(staffDto.id),
                new FullName(staffDto.fullName)
        );
    }

    private StaffDto convertStaffToDto(Staff staff) {
        return new StaffDto(
                staff.getId().getValue(),
                staff.getFullName().getValue()
        );
    }
}
