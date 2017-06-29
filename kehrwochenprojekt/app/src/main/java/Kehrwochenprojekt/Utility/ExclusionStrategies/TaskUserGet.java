package Kehrwochenprojekt.Utility.ExclusionStrategies;

import Kehrwochenprojekt.Basics.Kehrwochenobject;
import Kehrwochenprojekt.Datamodels.Task;
import Kehrwochenprojekt.Datamodels.User;

/**
 * Created by Alex on 26.06.2017.
 */

public class TaskUserGet extends KehrwochenDataRequest{


    public TaskUserGet(){
        super();
    }

    public static String getRequest(User u){

        if(u == null || u.getUserName().length() <= 0){
            throw new IllegalArgumentException("Invalid user specified, check arguments");
        }

        return "?userName="+u.getUserName();

    }











}
