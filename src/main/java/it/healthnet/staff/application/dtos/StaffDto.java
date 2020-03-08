package it.healthnet.staff.application.dtos;

public final class StaffDto {
    public final String id;
    public final String email;
    public final String fullName;

    public StaffDto(String id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }
}
