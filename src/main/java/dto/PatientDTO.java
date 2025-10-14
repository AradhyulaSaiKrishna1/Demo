package dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(min = 7, max = 15)
    private String phone;

    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;
}
