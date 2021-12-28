package by.tms.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
public class SaveUserDto {

    @NotBlank(message = "Field name is empty")
    private String name;

    @NotBlank(message = "Field username is empty")
    private String username;

    @NotBlank(message = "Field password is empty")
    private String password;
}
