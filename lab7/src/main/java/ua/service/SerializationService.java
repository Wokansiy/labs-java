package ua.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ua.exception.DataSerializationException;
import ua.util.LogUtil;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
public class SerializationService{
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final Logger log=LogUtil.createLogger(SerializationService.class);
    public SerializationService(){
        this.jsonMapper=new ObjectMapper();
        this.yamlMapper=new ObjectMapper(new YAMLFactory());
    }
    public <T> void writeJson(Collection<T> data, Class<T> clazz, Path path){
        try{
            log.info("Writing JSON to "+path);
            jsonMapper.writerFor(jsonMapper.getTypeFactory()
                    .constructCollectionType(List.class,clazz))
                    .writeValue(path.toFile(),data);
            log.info("JSON written successfully");
        }catch(JsonProcessingException e){
            log.severe("JSON processing error: "+e.getMessage());
            throw new DataSerializationException("JSON processing error",e);
        }catch(IOException e){
            log.severe("I/O error writing JSON: "+e.getMessage());
            throw new DataSerializationException("I/O error writing JSON",e);
        }
    }
    public <T> List<T> readJson(Class<T> clazz, Path path){
        try{
            log.info("Reading JSON from "+path);
            List<T> result=jsonMapper.readValue(path.toFile(),
                    jsonMapper.getTypeFactory().constructCollectionType(List.class,clazz));
            log.info("JSON read successfully, size="+result.size());
            return result;
        }catch(JsonProcessingException e){
            log.severe("JSON processing error: "+e.getMessage());
            throw new DataSerializationException("JSON processing error",e);
        }catch(IOException e){
            log.severe("I/O error reading JSON: "+e.getMessage());
            throw new DataSerializationException("I/O error reading JSON",e);
        }
    }
    public <T> void writeYaml(Collection<T> data, Class<T> clazz, Path path){
        try{
            log.info("Writing YAML to "+path);
            yamlMapper.writerFor(yamlMapper.getTypeFactory()
                    .constructCollectionType(List.class,clazz))
                    .writeValue(path.toFile(),data);
            log.info("YAML written successfully");
        }catch(JsonProcessingException e){
            log.severe("YAML processing error: "+e.getMessage());
            throw new DataSerializationException("YAML processing error",e);
        }catch(IOException e){
            log.severe("I/O error writing YAML: "+e.getMessage());
            throw new DataSerializationException("I/O error writing YAML",e);
        }
    }
    public <T> List<T> readYaml(Class<T> clazz, Path path){
        try{
            log.info("Reading YAML from "+path);
            List<T> result=yamlMapper.readValue(path.toFile(),
                    yamlMapper.getTypeFactory().constructCollectionType(List.class,clazz));
            log.info("YAML read successfully, size="+result.size());
            return result;
        }catch(JsonProcessingException e){
            log.severe("YAML processing error: "+e.getMessage());
            throw new DataSerializationException("YAML processing error",e);
        }catch(IOException e){
            log.severe("I/O error reading YAML: "+e.getMessage());
            throw new DataSerializationException("I/O error reading YAML",e);
        }
    }
}
