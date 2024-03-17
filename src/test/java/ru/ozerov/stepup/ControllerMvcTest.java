package ru.ozerov.stepup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ozerov.stepup.dto.AccountResponseDto;
import ru.ozerov.stepup.dto.InstanceRequestDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationTest.class)
@ActiveProfiles("test")
public class ControllerMvcTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    @Test
    public void getJsonNotParseableExceptionAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/corporate-settlement-account/create")
                        .content("XXX")
                        .contentType(APPLICATION_JSON)
                        .locale(Locale.forLanguageTag("ru"))
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void happyAccountCreateRequest() throws Exception {
        String requestString = readResourceToString("classpath:requests/createRegister.json");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/corporate-settlement-account/create")
                        .content(requestString)
                        .contentType(APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .locale(Locale.forLanguageTag("ru"))
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String responseString = result.getResponse().getContentAsString();
        assertNotNull(responseString);
        var responseObj = OBJECT_MAPPER.readValue(responseString, AccountResponseDto.class);
        assertNotNull(responseObj);
    }

    @Test
    public void getJsonNotParseableExceptionInstance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/corporate-settlement-instance/create")
                        .content("XXX")
                        .contentType(APPLICATION_JSON)
                        .locale(Locale.forLanguageTag("ru"))
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void happyInstanceCreateRequest() throws Exception {
        String requestString = readResourceToString("classpath:requests/createInstance.json");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/corporate-settlement-instance/create")
                        .content(requestString)
                        .contentType(APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .locale(Locale.forLanguageTag("ru"))
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String responseString = result.getResponse().getContentAsString();
        assertNotNull(responseString);
        var responseObj = OBJECT_MAPPER.readValue(responseString, InstanceRequestDto.class);
        assertNotNull(responseObj);
    }

    private static String readResourceToString(String path) throws IOException {
        Resource resource = loadResource(path);
        return resource.getContentAsString(StandardCharsets.UTF_8);
    }

    public static Resource loadResource(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(path);
    }
}
