package Kehrwochenprojekt.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Kehrwochenprojekt.Datamodels.Flat;
import Kehrwochenprojekt.Datamodels.Task;
import Kehrwochenprojekt.Datamodels.User;

/**
 * NOT A STATIC CLASS SINCE ARGUMENTS ARE PRONE TO CHANGE!!!!!
 * Created by Alex on 25.06.2017.
 */

public final class TaskPatch extends KehrwochenDataRequest {

    private Gson gsonGuy;
    public TaskPatch(){
        super();
        fieldsToExclude = new ArrayList<String>();
    }

    public TaskPatch(String taskID, String name, Date deadline, String guideline)
    {

        this();
        if(taskID == null){
            throw new IllegalArgumentException("Invalid Task specified - check arguments!");
        }

        if(deadline == null && guideline == null && name == null){
            throw new IllegalArgumentException("Nothing to patch! Check arguments!");
        }

        if(name == null){
            fieldsToExclude.add("name");
        }

        if(deadline == null){
            fieldsToExclude.add("deadline");
        }

        if(guideline == null){
            fieldsToExclude.add("guideline");
        }



        gsonGuy = ExclusionStrategyFactory.build(fieldsToExclude,null);

    }

    public String getRequest(Task t){
        if (t != null){
            return gsonGuy.toJson(t);
        }
        else{
            throw new IllegalArgumentException("Expected valid task object!");
        }
    }


}
