import java.util.*;

public class Comment {
    protected UUID author;
    protected String content;
    protected Date date;
    protected ArrayList<Comment> comments;
    public Comment(UUID author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}
