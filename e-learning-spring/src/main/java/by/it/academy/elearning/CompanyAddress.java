package by.it.academy.elearning;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component("compatyAddress")
public class CompanyAddress implements IAddress {
    private Long Id;
    private String street;
    private String city;
    private String country;
}
