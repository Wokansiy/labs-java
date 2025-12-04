package ua.app;
import org.junit.jupiter.api.Test;
import ua.exception.DataSerializationException;
import ua.service.SerializationService;
import ua.university.Student;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class SerializationTests{
    @Test
    void jsonRoundTripShouldPreserveData() throws Exception{
        SerializationService service=new SerializationService();
        List<Student> original=List.of(
                new Student(1,"Vlad","Marusiak",93.5),
                new Student(2,"Vasyl","Kosovan",88.0)
        );
        Path temp=Files.createTempFile("students",".json");
        service.writeJson(original,Student.class,temp);
        List<Student> restored=service.readJson(Student.class,temp);
        assertEquals(original,restored);
        Files.deleteIfExists(temp);
    }
    @Test
    void yamlRoundTripShouldPreserveData() throws Exception{
        SerializationService service=new SerializationService();
        List<Student> original=List.of(
                new Student(1,"Vlad","Marusiak",93.5),
                new Student(2,"Vasyl","Kosovan",88.0)
        );
        Path temp=Files.createTempFile("students",".yaml");
        service.writeYaml(original,Student.class,temp);
        List<Student> restored=service.readYaml(Student.class,temp);
        assertEquals(original,restored);
        Files.deleteIfExists(temp);
    }
    @Test
    void readingNonExistingFileShouldThrowDataSerializationException(){
        SerializationService service=new SerializationService();
        Path path=Paths.get("definitely-does-not-exist-12345.json");
        assertThrows(DataSerializationException.class,
                ()->service.readJson(Student.class,path));
    }
}
