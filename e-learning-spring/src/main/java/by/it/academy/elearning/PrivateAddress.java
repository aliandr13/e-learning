package by.it.academy.elearning;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component("privateAddress")
public class PrivateAddress implements Address {
    @Value("Belarus")
    private String country;
    @Value("my street")
    private String street;
    @Value("9")
    private Integer house;
    @Value("42")
    private Integer flat;

}
