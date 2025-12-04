package ua.repository;
import ua.sports.Team;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
public class TeamRepository extends GenericRepository<Team>{
    private final Logger log=LogUtil.createLogger(TeamRepository.class);
    public TeamRepository(){
        super(Team::name);
    }
    public List<Team> findBySport(String sport){
        List<Team> result=stream()
                .filter(t->t.sport().equalsIgnoreCase(sport))
                .collect(Collectors.toList());
        log.info("findBySport('"+sport+"') -> "+result.size());
        return result;
    }
    public long totalPlayersCount(){
        long count=stream()
                .flatMap(t->t.players().stream())
                .count();
        log.info("totalPlayersCount() -> "+count);
        return count;
    }
}