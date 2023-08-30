package ru.hogwarts.school;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class HogwartsApplicationFacultyTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private FacultyController facultyController;




    @Test
    public void createFacultyTest() throws Exception{
        JSONObject facultyObject = new JSONObject();
        final String name = "МГУ";
        final String color = "Алый";
        final long id = 1;
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    public void getFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        final String name = "МГУ";
        final String color = "Алый";
        final long id = 1;
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
    @Test
    public void deleteFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        final String name = "МГУ";
        final String color = "Алый";
        final long id = 1;
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculties/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void editFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        final String name = "МГУ";
        final String color = "Алый";
        final long id = 1;
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculties")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());


    }
}
