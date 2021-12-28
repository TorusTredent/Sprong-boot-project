package by.tms.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {

    @NotBlank(message = "Field username is empty")
    private String username;

    @NotBlank(message = "Field password is empty")
    private String password;
}
