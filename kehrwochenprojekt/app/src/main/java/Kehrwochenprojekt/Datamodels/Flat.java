package Kehrwochenprojekt.Datamodels;
import java.util.List;
import java.util.HashSet;
import org.json.JSONObject;
/**
 * Created by Alex on 22.06.2017.
 */

public class Flat extends KehrwochenData {

    private String _flatID;
    private String name;
    private List<String> penalties;
    private List<User> residents;


    public Flat(String _flatID, String name){

    }

    public Flat(String _flatID, String name, List<String> penalties){

    }

    boolean addResident(User res){
        return residents.add(res);
    }

    boolean removeResident(User res){
        return residents.remove(res);
    }

    HashSet<User> getAllResidents(){
        HashSet<User> residentSet = new HashSet<User>();
        for(User res : residents){
            residentSet.add(res);
        }
        return residentSet;
    }

    @Override
    public JSONObject toJSON(Object o){
        return null;
    }

    @Override
    public Object toObject(JSONObject jo){
        return null;
    }

}
