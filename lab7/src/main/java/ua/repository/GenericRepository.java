package ua.repository;
import ua.repository.IdentityExtractor;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class GenericRepository<T>{
    private final Map<String,T> storage=new LinkedHashMap<>();
    private final IdentityExtractor<T> extractor;
    private final Logger log;
    public GenericRepository(IdentityExtractor<T> extractor){
        this.extractor=extractor;
        this.log=LogUtil.createLogger(GenericRepository.class);
    }
    public void add(T value){
        String id=extractor.extractIdentity(value);
        if(storage.containsKey(id)){
            log.warning("Duplicate identity "+id+", overwriting");
        }
        storage.put(id,value);
        log.info("Added entity with id "+id);
    }
    public java.util.List<T> getAll(){
        return new ArrayList<>(storage.values());
    }
    public void clear(){
        storage.clear();
    }
}
