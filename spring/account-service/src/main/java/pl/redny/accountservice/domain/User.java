package pl.redny.accountservice.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Address address;
    private Contact contact;

}
