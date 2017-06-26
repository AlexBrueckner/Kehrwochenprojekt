package Kehrwochenprojekt.Utility.ExclusionStrategies;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Kehrwochenprojekt.Datamodels.Flat;
import Kehrwochenprojekt.Datamodels.User;

/**
 * Created by Alex on 25.06.2017.
 */

public final class FlatPost extends KehrwochenDataRequest {
    private static final List<String> excludeFields = new ArrayList<String>();
    //I only need this ONCE PER TYPE! Hence the static block.
    static{

        excludeFields.add("residents");
    }
    private static final Gson mySerializer = ExclusionStrategyFactory.build(excludeFields,null);


    public FlatPost(){
        super();
    }

    public static String getRequest(Flat f){
        if (f != null){
            return mySerializer.toJson(f);
        }
        else{
            throw new IllegalArgumentException("Expected valid user object!");
        }
    }


}
