package Kehrwochenprojekt.Utility;

import Kehrwochenprojekt.Datamodels.JSONConvertible;

/**
 * Created by Alex on 22.06.2017.
 */

public class RESTClient extends KehrwochenUtility {
    private String apiURL;
    private RequestFactory factory;

    public RESTClient(String apiURL){
        this.apiURL = apiURL;
        factory = new RequestFactory();
    }

    //Actually send stuff
    private JSONConvertible sendRequest(KehrwochenRequest req){
            return null;
    }

    public JSONConvertible sendRequest(String apiEndpoint, RequestType reqType, String payloadType,
                                       JSONConvertible payload){

        return sendRequest(factory.createRequest(apiURL,apiEndpoint,reqType,payloadType,payload));


    }
}
