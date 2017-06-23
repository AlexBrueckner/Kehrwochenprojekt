package Kehrwochenprojekt.Utility;

import Kehrwochenprojekt.Datamodels.JSONConvertible;

/**
 * Created by Alex on 22.06.2017.
 */

public class RequestFactory extends KehrwochenUtility {

    public RequestFactory(){

    }

    public KehrwochenRequest createRequest(String apiURL, String apiEndpoint, RequestType type, String payloadType,
                                           JSONConvertible payload){
            return new KehrwochenRequest(apiURL,apiEndpoint,type,payloadType,payload);
    }


}
