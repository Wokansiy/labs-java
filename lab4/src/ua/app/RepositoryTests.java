package ua.app;
import ua.repository.GenericRepository;
import ua.sports.*;
import ua.util.LogUtil;
import java.util.List;
import java.util.logging.Logger;
public class RepositoryTests{
    private static final Logger LOG=LogUtil.createLogger(RepositoryTests.class);
    public static void main(String[]args){
        testPlayerRepository();
        testCoachRepository();
        testTeamRepository();
    }
    private static void testPlayerRepository(){
        GenericRepository<Player> repo=new GenericRepository<>(p->String.valueOf(p.getNumber()));
        Player p1=new Player("Vlad","Marusiak",PlayerPosition.FORWARD,19);
        Player p2=new Player("Vasyl","Kosovan",PlayerPosition.DEFENDER,40);
        Player dup=new Player("Duplicate","Player",PlayerPosition.FORWARD,19);
        repo.add(p1);
        repo.add(p2);
        repo.add(dup);
        if(repo.getAll().size()==2){
            LOG.info("testPlayerRepository size OK");
        }else{
            LOG.warning("testPlayerRepository size FAIL");
        }
        Player found=repo.findByIdentity("19");
        if(found!=null && "Duplicate".equals(found.getFirstName())){
            LOG.info("testPlayerRepository duplicate overwrite OK");
        }else{
            LOG.warning("testPlayerRepository duplicate overwrite FAIL");
        }
    }
    private static void testCoachRepository(){
        GenericRepository<Coach> repo=new GenericRepository<>(c->c.lastName()+"_"+c.firstName());
        Coach c1=new Coach("Phil","Jackson",CoachRole.HEAD_COACH);
        Coach c2=new Coach("John","Doe",CoachRole.ASSISTANT_COACH);
        repo.add(c1);
        repo.add(c2);
        if(repo.findByIdentity("Jackson_Phil")!=null){
            LOG.info("testCoachRepository find OK");
        }else{
            LOG.warning("testCoachRepository find FAIL");
        }
    }
    private static void testTeamRepository(){
        GenericRepository<Team> repo=new GenericRepository<>(Team::name);
        Coach c1=new Coach("Phil","Jackson",CoachRole.HEAD_COACH);
        Player p1=new Player("Vlad","Marusiak",PlayerPosition.FORWARD,19);
        Team t=new Team("Bulls","Basketball",c1, List.of(p1));
        repo.add(t);
        if(repo.getAll().size()==1 && repo.findByIdentity("Bulls")!=null){
            LOG.info("testTeamRepository OK");
        }else{
            LOG.warning("testTeamRepository FAIL");
        }
    }
}