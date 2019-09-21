package hillel.spring.petclinic.pet;

import java.io.File;
import java.nio.file.Files;

import hillel.spring.TestRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestRunner
public class PetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PetRepository repository;

    @After
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void shouldCreatePet() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/pets")
                                                                   .contentType("application/json")
                                                                   .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
                                                                   .content(fromResource("petclinic/pet/create-pet.json"))
        )
                                                  .andExpect(status().isCreated())
                                                  .andExpect(header().string("location", containsString("http://my-pet.com/pets/")))
                                                  .andReturn().getResponse();

        Integer id = Integer.parseInt(response.getHeader("location").replace("http://my-pet.com/pets/", ""));

        assertThat(repository.findById(id)).isPresent();
    }

    @Test
    public void shouldUpdateTom() throws Exception {

        Integer id = repository.save(new Pet("Tom", "Cat", 2, "Vasya")).getId();

        mockMvc.perform(put("/pets/{id}", id)
                                .contentType("application/json")
                                .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
                                .content(fromResource("petclinic/pet/update-pet.json")))
               .andExpect(status().isOk());

        assertThat(repository.findById(id).get().getAge()).isEqualTo(3);
    }

    @Test
    public void shouldDeleteJerry() throws Exception {
        Integer id = repository.save(new Pet("Tom", "Cat", 2, "Vasya")).getId();
        repository.save(new Pet("Jerry", "Mouse", 1, "Petya"));

        mockMvc.perform(delete("/pets/{id}", id)
                                .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
        )
               .andExpect(status().isNoContent());

        assertThat(repository.findById(id)).isEmpty();
    }

    @Test
    public void shouldFindAllPets() throws Exception {
        repository.save(new Pet("Tom", "Cat", 2, "Vasya"));
        repository.save(new Pet("Jerry", "Mouse", 1, "Petya"));

        mockMvc.perform(get("/pets")
                                .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
        )
               .andExpect(status().isOk())
               //.andExpect(content().json(fromResource("petclinic/pet/all-pets.json"), false))
               .andExpect(jsonPath("$.content", hasSize(2)))
               .andExpect(jsonPath("$.content[0].owner", is("Vasya")))
               .andExpect(jsonPath("$.content[0].id", notNullValue()))
               .andExpect(jsonPath("$.content[1].id", notNullValue()));
    }

    @Test
    public void shouldReturnTom() throws Exception {
        repository.save(new Pet("Tom", "Cat", 2, "Vasya"));
        repository.save(new Pet("Jerry", "Mouse", 1, "Petya"));

        mockMvc.perform(get("/pets").param("name", "Tom")
                                    .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content", hasSize(1)))
               .andExpect(jsonPath("$.content[0].name", is("Tom")));
    }

    @Test
    public void shouldReturnJerry() throws Exception {
        repository.save(new Pet("Tom", "Cat", 2, "Vasya"));
        repository.save(new Pet("Jerry", "Mouse", 1, "Petya"));

        mockMvc.perform(get("/pets")
                                .param("name", "Jerry")
                                .param("age", "1")
                                .header(HttpHeaders.AUTHORIZATION,"Basic bWF4OjEyMw==")
        )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content", hasSize(1)))
               .andExpect(jsonPath("$.content[0].name", is("Jerry")))
               .andExpect(jsonPath("$.content[0].age", is(1)));
    }

    public static String fromResource(String path) {
        try {
            File file = ResourceUtils.getFile("classpath:" + path);
            return Files.readString(file.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}