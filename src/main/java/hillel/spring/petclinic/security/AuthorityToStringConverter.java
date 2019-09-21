package hillel.spring.petclinic.security;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AuthorityToStringConverter implements AttributeConverter<Set<ClinicAuthority>, String> {
    @Override
    public String convertToDatabaseColumn(Set<ClinicAuthority> attribute) {
        return attribute.stream()
                        .map(ClinicAuthority::getAuthority)
                        .collect(Collectors.joining(","));
    }

    @Override
    public Set<ClinicAuthority> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(","))
                     .map(ClinicAuthority::new)
                     .collect(Collectors.toSet());
    }
}
