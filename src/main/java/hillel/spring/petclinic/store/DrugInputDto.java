package hillel.spring.petclinic.store;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class DrugInputDto {
    @NotEmpty
    @LatinName
    private String name;
    @Range(min = 1, max = 1000)
    @NotNull
    private Integer amount;
    @PositiveOrZero
    @NotNull
    private Integer price;
    @NotEmpty
    private List<@LatinName String> components;
    @Email
    @NotNull
    private String supplierEmail;
}
