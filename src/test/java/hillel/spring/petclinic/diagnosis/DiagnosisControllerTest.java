package hillel.spring.petclinic.diagnosis;

import hillel.spring.TestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static hillel.spring.petclinic.pet.PetControllerTest.fromResource;
import static org.junit.Assert.*;

@TestRunner
@RunWith(SpringRunner.class)
public class DiagnosisControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getDiagnosis() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/diagnosis/{petId}", 234)
                                                        .header(HttpHeaders.AUTHORIZATION, "Basic bWF4OjEyMw==")
                                                        .contentType("application/json")
                                                        .content(fromResource("petclinic/diagnosis/get-diagnosis.json")
                                                        )
        )
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcRestDocumentation.document("get-diagnosis",
                                                        RequestDocumentation.pathParameters(
                                                                RequestDocumentation.parameterWithName("petId")
                                                                                    .description("Pet ID")
                                                        ),
                                                        RequestDocumentation.requestParameters(
                                                                RequestDocumentation.parameterWithName("trythy")
                                                                                    .description("defines whether diagnosis it truthy")
                                                                                    .optional()
                                                        ),
                                                        PayloadDocumentation.responseFields(
                                                                PayloadDocumentation.fieldWithPath("deceaseName")
                                                                                    .description("Decease Name"),
                                                                PayloadDocumentation.fieldWithPath("severe")
                                                                                    .description("Decease severity"),
                                                                PayloadDocumentation.fieldWithPath("severityLevel")
                                                                                    .description("Level")
                                                        ),
                                                        PayloadDocumentation.requestFields(
                                                                PayloadDocumentation.fieldWithPath("symptoms")
                                                                                    .description("The symptoms")
                                                        )
               ));
    }
}