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
    public boolean removeByIdentity(String id){
        T removed=storage.remove(id);
        if(removed!=null){
            log.info("Removed entity with id "+id);
            return true;
        }else{
            log.warning("No entity found with id "+id);
            return false;
        }
    }
    public java.util.List<T> getAll(){
        return new ArrayList<>(storage.values());
    }
    public T findByIdentity(String id){
        T v=storage.get(id);
        log.info("findByIdentity("+id+") -> "+(v!=null));
        return v;
    }
    public java.util.List<T> sortByIdentity(String order){
        java.util.List<Map.Entry<String,T>> entries=new ArrayList<>(storage.entrySet());
        entries.sort((e1,e2)->e1.getKey().compareToIgnoreCase(e2.getKey()));
        if("desc".equalsIgnoreCase(order)){
            java.util.Collections.reverse(entries);
        }
        java.util.List<T> result=new ArrayList<>();
        for(Map.Entry<String,T> e:entries){
            result.add(e.getValue());
        }
        log.info("sortByIdentity("+order+") executed, size="+result.size());
        return result;
    }
}