package it.healthnet.staff.application.dtos;

public final class StaffDto {
    public final String id;
    public final String fullName;

    public StaffDto(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
