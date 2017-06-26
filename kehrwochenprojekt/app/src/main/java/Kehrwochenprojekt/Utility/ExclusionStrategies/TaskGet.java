package Kehrwochenprojekt.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Kehrwochenprojekt.Datamodels.Flat;
import Kehrwochenprojekt.Datamodels.Task;
import Kehrwochenprojekt.Datamodels.User;

/**
 * Created by Alex on 25.06.2017.
 */

public final class TaskGet extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("name");
        excludeFields.add("creationDate");
        excludeFields.add("deadline");
        excludeFields.add("state");
        excludeFields.add("comments");
        excludeFields.add("guideline");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public TaskGet(){
        super();
    }

    public static String getRequest(Task t){
        if (t != null){
            return mySerializer.toJson(t);
        }
        else{
            throw new IllegalArgumentException("Expected valid Task object!");
        }
    }


}
