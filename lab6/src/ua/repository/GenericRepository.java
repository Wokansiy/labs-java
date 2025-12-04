package ua.repository;
import ua.repository.IdentityExtractor;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
    public List<T> getAll(){
        return new ArrayList<>(storage.values());
    }
    public Stream<T> stream(){
        return storage.values().stream();
    }
    public Stream<T> parallelStream(){
        return storage.values().parallelStream();
    }
    public T findByIdentity(String id){
        T v=storage.get(id);
        log.info("findByIdentity("+id+") -> "+(v!=null));
        return v;
    }
}