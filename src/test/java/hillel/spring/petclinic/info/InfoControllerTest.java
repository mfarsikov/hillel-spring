package hillel.spring.petclinic.info;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import hillel.spring.TestRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static hillel.spring.petclinic.pet.PetControllerTest.fromResource;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestRunner
@RunWith(SpringRunner.class)
public class InfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void readFromAnotherService() throws Exception {
        stubFor(get("/doctors")
                        .willReturn(okJson(fromResource("petclinic/info/workers.json"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/workers"))
               .andExpect(status().isOk())
               .andExpect(content().json(fromResource("petclinic/info/workers.json")));

        verify(getRequestedFor(urlPathEqualTo("/doctors")));
    }
}