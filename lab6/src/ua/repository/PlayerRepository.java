package ua.repository;
import ua.sports.Player;
import ua.sports.PlayerPosition;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
public class PlayerRepository extends GenericRepository<Player>{
    private final Logger log=LogUtil.createLogger(PlayerRepository.class);
    public PlayerRepository(){
        super(p->String.valueOf(p.getNumber()));
    }
    public List<Player> findByPosition(PlayerPosition position){
        List<Player> result=stream()
                .filter(p->p.getPosition()==position)
                .collect(Collectors.toList());
        log.info("findByPosition("+position+") -> "+result.size());
        return result;
    }
    public List<Player> findByNumberRange(int min,int max){
        List<Player> result=stream()
                .filter(p->p.getNumber()>=min && p.getNumber()<=max)
                .collect(Collectors.toList());
        log.info("findByNumberRange("+min+","+max+") -> "+result.size());
        return result;
    }
}