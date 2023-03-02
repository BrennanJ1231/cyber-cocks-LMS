import java.sql.Date;

import java.util.*;
public class Comment {
    protected User author;
    protected String content;
    protected Date date;
    protected ArrayList<Comment> comments;
    public Comment(User author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
