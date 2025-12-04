package ua.app;
import ua.university.Student;
import ua.university.Group;
import ua.repository.GenericRepository;
import ua.service.SerializationService;
import ua.util.LogUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;
public class Main{
    private static final Logger LOG=LogUtil.createLogger(Main.class);
    public static void main(String[]args){
        Properties props=new Properties();
        try(FileInputStream in=new FileInputStream("config.properties")){
            props.load(in);
            LOG.info("config.properties loaded");
        }catch(IOException e){
            LOG.severe("Failed to load config.properties: "+e.getMessage());
            return;
        }
        String jsonPathStr=props.getProperty("students.json.path","students.json");
        String yamlPathStr=props.getProperty("students.yaml.path","students.yaml");
        int count=Integer.parseInt(props.getProperty("test.students.count","3"));
        Path jsonPath=Paths.get(jsonPathStr);
        Path yamlPath=Paths.get(yamlPathStr);

        GenericRepository<Student> studentRepo=new GenericRepository<>(s->String.valueOf(s.getId()));
        for(int i=1;i<=count;i++){
            // Перші троє — реальні імена, далі — тестові
            if(i==1){
                studentRepo.add(new Student(1,"Vlad","Marusiak",93.5));
            }else if(i==2){
                studentRepo.add(new Student(2,"Vasyl","Kosovan",88.0));
            }else if(i==3){
                studentRepo.add(new Student(3,"Ruslan","Biloskurskyi",91.2));
            }else{
                studentRepo.add(new Student(i,"Test"+i,"Student",70.0+i));
            }
        }

        SerializationService service=new SerializationService();

        // JSON
        service.writeJson(studentRepo.getAll(),Student.class,jsonPath);
        java.util.List<Student> jsonRestored=service.readJson(Student.class,jsonPath);

        // YAML
        service.writeYaml(studentRepo.getAll(),Student.class,yamlPath);
        java.util.List<Student> yamlRestored=service.readYaml(Student.class,yamlPath);

        LOG.info("Original size="+studentRepo.getAll().size()
                +", JSON restored="+jsonRestored.size()
                +", YAML restored="+yamlRestored.size());

        LOG.info("Original vs JSON equal: "+studentRepo.getAll().equals(jsonRestored));
        LOG.info("Original vs YAML equal: "+studentRepo.getAll().equals(yamlRestored));

        // Демонстрація обробки помилок
        try{
            Path wrongPath=Paths.get("non-existing-folder/non-existing-file.json");
            service.readJson(Student.class,wrongPath);
        }catch(RuntimeException e){
            LOG.warning("Expected error when reading from missing file: "+e.getMessage());
        }
    }
}
