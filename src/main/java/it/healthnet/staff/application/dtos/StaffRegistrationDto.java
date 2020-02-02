package it.healthnet.staff.application.dtos;

public final class StaffRegistrationDto {
    public StaffRegistrationDto(String password, String email, String fullName) {
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public final String password;
    public final String email;
    public final String fullName;


}
