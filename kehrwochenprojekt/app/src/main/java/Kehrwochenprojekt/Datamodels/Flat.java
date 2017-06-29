package Kehrwochenprojekt.Datamodels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Flat
 * <p>
 *
 *
 */
public class Flat {
    @SerializedName("flatID")
    @Expose
    private String flatId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("residents")
    @Expose
    private List<Object> residents = null;
    @SerializedName("penalty")
    @Expose
    private String penalty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getResidents() {
        return residents;
    }

    public void setResidents(List<Object> residents) {
        this.residents = residents;
    }

    public String getPenalty() {
        return penalty;
    }

    public String getCreator(){
        return creator;
    }

    public void setID(String id){
        this.flatId = id;
    }

    public String getID(){
        return flatId;
    }

    public void setCreator(String creator){
        if(creator != null && creator.length() > 0){
            this.creator = creator;
        }
        else{
            throw new IllegalArgumentException("Expected valid username");
        }
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

}