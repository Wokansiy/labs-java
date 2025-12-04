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
        switch(r){
            case HEAD_COACH: return "Head coach";
            case ASSISTANT_COACH: return "Assistant coach";
            case FITNESS_COACH: return "Fitness coach";
            case GOALKEEPING_COACH: return "Goalkeeping coach";
        }
        return "";
    }
}