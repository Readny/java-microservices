package pl.redny.accountservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String street;
    private String country;
    private String postCode;
    private String city;

}
