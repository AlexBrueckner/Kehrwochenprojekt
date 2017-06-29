package Kehrwochenprojekt.Datamodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Task
 * <p>
 *
 *
 */
public class Task {

    //TODO: Implement parsing of Dates
    @SerializedName("taskId")
    @Expose
    private String taskId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("creationDate")
    @Expose
    private Date creationDate;
    @SerializedName("deadline")
    @Expose
    private Date deadline;
    @SerializedName("state")
    @Expose
    private Task.State state = Task.State.fromValue("RED");
    @SerializedName("comments")
    @Expose
    private List<Object> comments = null;
    @SerializedName("guideline")
    @Expose
    private String guideline;


    public Task(){
        comments = new ArrayList<Object>();
    }

    public void addComment(String s){
        comments.add(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Object getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Task.State getState() {
        return state;
    }

    public void setState(Task.State state) {
        this.state = state;
    }

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getTaskId(){
        return taskId;
    }

    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    public enum State {

        @SerializedName("RED")
        RED("RED"),
        @SerializedName("YELLOW")
        YELLOW("YELLOW"),
        @SerializedName("GREEN")
        GREEN("GREEN");
        private final String value;
        private final static Map<String, Task.State> CONSTANTS = new HashMap<String, Task.State>();

        static {
            for (Task.State c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private State(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Task.State fromValue(String value) {
            Task.State constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}