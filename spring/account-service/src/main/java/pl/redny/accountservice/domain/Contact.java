package pl.redny.accountservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {

    private String email;
    private String phoneNumber;

}
