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

public final class FlatGet extends KehrwochenDataRequest {

    public FlatGet() {
        super();
    }

    public String getRequest(User u){
        if(u == null || u.getUserName().length() <= 0){
            throw new IllegalArgumentException("Invalid user specified - check arguments");
        }

        //TODO: Ask the other guy and implement this properly
        return "I like this class and I cannot lie - you other coders gotta shine!";
    }


}
