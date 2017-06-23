package Kehrwochenprojekt.Datamodels;

import android.media.Image;
import java.util.List;
import org.json.JSONObject;
import Kehrwochenprojekt.Datamodels.Processingtime;
/**
 * Created by Alex on 22.06.2017.
 */

public class Task extends KehrwochenData implements Evaluatable {

    private String _id;
    private String name;
    private List<Image> images;
    private TaskEvaluationResult taEvaRes;
    private String guideline;
    private Processingtime pTime;

    public Task(String name, Processingtime pTime){
        this.name = name;
        this.pTime = pTime;
    }


    public Object toObject(JSONObject jo){
        return null;
    }

    public JSONObject toJSON(Object o){
        return null;
    }

    public TaskEvaluationResult getResult(){
        return taEvaRes;
    }

    public List<Image> getImages(){
        return images;
    }

    public String getID(){
        return _id;
    }

    public String getName(){
        return name;
    }

    public void setID(String newID){
        _id = newID;
    }

    public void setName(String newname){
        name = newname;
    }

    public boolean addImage(Image i){
        return images.add(i);
    }

    public boolean removeImage(Image i){
        return images.remove(i);
    }



    @Override
    public void evaluate(EvaluationResult result, Evaluator eva){

    }



}
