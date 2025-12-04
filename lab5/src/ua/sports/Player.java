package ua.sports;
import ua.util.ValidationHelper;
import java.util.Comparator;
import java.util.Objects;
public class Player extends Person implements Comparable<Player>{
    private PlayerPosition position;
    private int number;
    public static final Comparator<Player> BY_NUMBER_ASC=Comparator.comparingInt(Player::getNumber);
    public static final Comparator<Player> BY_POSITION_THEN_NUMBER=
            Comparator.comparing(Player::getPosition).thenComparingInt(Player::getNumber);
    public Player(String f,String l,PlayerPosition p,int n){
        super(f,l);
        setPosition(p);
        setNumber(n);
    }
    public static Player of(String f,String l,PlayerPosition p,int n){return new Player(f,l,p,n);}
    public PlayerPosition getPosition(){return position;}
    public void setPosition(PlayerPosition p){this.position=ValidationHelper.requireNonNull(p,"position");}
    public int getNumber(){return number;}
    public void setNumber(int n){this.number=ValidationHelper.requireInRange(n,1,99,"number");}
    public int compareTo(Player o){
        return Integer.compare(this.number,o.number);
    }
    public String toString(){return "Player{name='"+getFullNameProtected()+"', position="+position+", number="+number+"}";}
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Player x)) return false;
        if(!super.equals(o)) return false;
        return number==x.number&&position==x.position;
    }
    public int hashCode(){return Objects.hash(super.hashCode(),position,number);}
}