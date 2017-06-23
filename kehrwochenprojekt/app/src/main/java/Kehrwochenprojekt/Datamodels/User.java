package Kehrwochenprojekt.Datamodels;

import org.json.JSONObject;
import java.util.Map;
/**
 * Created by Alex on 22.06.2017.
 */

public class User extends KehrwochenData implements Evaluator{

    private String _userID;
    private String username;
    private String password;
    private String forename;
    private String surname;
    private Map<String,Task> tasks;

    public User(){

    }

    public User(String _userID, String username, String password, String forename, String surname, Map<String,Task> tasks){

    }

    public boolean addTask(String key, Task task){
        return tasks.put(key,task) != null;
    }

    public boolean removeTask(String key){
        return tasks.remove(key) != null;
    }

    public Task getTask(String key){
        return tasks.get(key);
    }

    public void flushTasks(){
        tasks.clear();
    }

    @Override
    public JSONObject toJSON(Object o){
        return null;
    }

    @Override
    public Object toObject(JSONObject jo){
        return null;
    }

    @Override
    public void evaluateObject(Evaluatable target, EvaluationResult res){

    }

    public String getUserID(){
        return _userID;
    }

    public String getUsername(){
        return username;
    }

    public String getForename(){
        return forename;
    }

    public String getSurname(){
        return surname;
    }

    public String getFullName(){
        return getForename()+ " " + getSurname();
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setForename(String forename){
        this.forename = forename;
    }
}
