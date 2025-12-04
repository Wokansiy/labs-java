package ua.sports;
public final class RoleDescriptions{
    private RoleDescriptions(){}
    public static String describePlayerPosition(PlayerPosition p){
        return switch(p){
            case GOALKEEPER -> "Goalkeeper";
            case DEFENDER -> "Defender";
            case MIDFIELDER -> "Midfielder";
            case FORWARD -> "Forward";
        };
    }
    public static String describeCoachRole(CoachRole r){
        return switch(r){
            case HEAD_COACH -> "Head coach";
            case ASSISTANT_COACH -> "Assistant coach";
            case FITNESS_COACH -> "Fitness coach";
            case GOALKEEPING_COACH -> "Goalkeeping coach";
        };
    }
}