package it.healthnet.staff.application.services;

import it.healthnet.staff.application.dtos.StaffDto;
import it.healthnet.staff.application.dtos.StaffRegistrationDto;
import it.healthnet.staff.domain.staff.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void create(StaffRegistrationDto staffDto) {
        staffRepository.add(convertStaffRegistrationDtoToStaff(staffDto));
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

    private StaffDto convertStaffToDto(Staff staff) {
        return new StaffDto(
                staff.getId().getValue(),
                staff.getFullName().getValue()
        );
    }

    private Staff convertStaffRegistrationDtoToStaff(StaffRegistrationDto dto){
        return new Staff(new Email(dto.email),dto.password, new FullName(dto.fullName));
    }
}
