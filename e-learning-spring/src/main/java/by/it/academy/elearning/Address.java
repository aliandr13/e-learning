package by.it.academy.elearning;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component("address")
public class Address implements IAddress {
    @Value("1")
    private Long Id;
    @Value("some street")
    private String street;
    @Value("Minsk")
    private String city;
    @Value("Belarus")
    private String country;
}
