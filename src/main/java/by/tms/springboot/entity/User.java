package by.tms.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class User {

    private String name;

    private String username;

    private String password;

}
