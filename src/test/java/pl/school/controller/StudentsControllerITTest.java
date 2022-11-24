package pl.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import pl.school.model.dto.student.StudentDto;
import pl.school.model.entity.Teacher;
import pl.school.model.enums.FieldEnum;

import javax.persistence.SecondaryTable;
import java.util.HashSet;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.school.util.TestUtils.toJson;

@SpringBootTest
@AutoConfigureMockMvc

public class StudentsControllerITTest {

    @Autowired
    private MockMvc mockMvc;
    @SqlGroup({
            @Sql(scripts = "/sql/controller/school.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "/sql/controller/school-cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    public void whenGetAllStudents_thenReturnStudents() throws Exception {
        //when && then
        mockMvc.perform(get("/student/get/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(3)));
    }
    @Test
    public void whenPostRequestForStudentSave_thenCorrectResponse() throws Exception {
        //given
        var student = StudentDto
                .builder()
                .name("John")
                .surname("Wick")
                .email("cop@com.pl")
                .age(34)
                .field(FieldEnum.BIOLOGY)
                .teachers(new HashSet<>())
                .build();
        //when && then
        mockMvc.perform(post("/student/post").content(toJson(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("John")))
                .andExpect(jsonPath("$.surname", equalTo("Wick")))
                .andExpect(jsonPath("$.email", equalTo("cop@com.pl")))
                .andExpect(jsonPath("$.age", equalTo(34)))
                .andExpect(jsonPath("$.field", equalTo("BIOLOGY")))
                .andExpect(jsonPath("$.teachers").isEmpty());
    }
}
