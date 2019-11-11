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
        var patient = staffRepository.get(new StaffId(id));
        staffRepository.remove(patient);
    }

    public StaffDto getOneById(String id) {
        return convertPatientToDto(staffRepository.get(new StaffId(id)));
    }

    public List<StaffDto> getAll() {
        var patients = staffRepository.getAll();
        return patients.stream().map(patient -> convertPatientToDto(patient)).collect(Collectors.toList());
    }

    private Staff convertDtoToPatient(StaffDto staffDto) {
        return new Staff(
                new StaffId(staffDto.id),
                new FullName(staffDto.fullName)
        );
    }

    private StaffDto convertPatientToDto(Staff staff) {
        return new StaffDto(
                staff.getId().getValue(),
                staff.getFullName().getValue()
        );
    }
}
