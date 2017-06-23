package Kehrwochenprojekt.Datamodels;

/**
 * Created by Alex on 22.06.2017.
 */
import java.util.List;
public abstract class EvaluationResult {
    protected RatingColor colorRating;
    protected List<Comment> comments;

    public EvaluationResult(){

    }

    boolean addComment(Comment c){
        return comments.add(c);
    }

    boolean removeComment(Comment c){
        return comments.remove(c);
    }

    String getSummary(){
        String s = "SUMMARY: ";
        for(Comment c : comments){
            s+= "-" + c.toString() + "-\n";
        }
        return s;
    }
}
