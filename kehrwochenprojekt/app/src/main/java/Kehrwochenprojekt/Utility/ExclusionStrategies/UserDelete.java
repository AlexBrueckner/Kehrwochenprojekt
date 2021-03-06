package Kehrwochenprojekt.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Kehrwochenprojekt.Datamodels.User;

/**
 * Created by Alex on 25.06.2017.
 */

public final class UserDelete extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{
        excludeFields.add("tasks");
        excludeFields.add("password");
        excludeFields.add("foreName");
        excludeFields.add("surName");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public UserDelete(){
        super();
    }

    public static String getRequest(User u){
        if (u != null){
            return mySerializer.toJson(u);
        }
        else{
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
