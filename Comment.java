import java.util.*;

/**
A class to represent a comment in a course or module.
*/
public class Comment {
    protected UUID author;
    protected String content;
    protected Date date;
    protected ArrayList<Comment> comments;

    /**
 * Constructor for Comment class.
 * @param author the author of the comment.
 * @param content the content of the comment.
 * @param date the date of the comment.
 */
    public Comment(UUID author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    /**
     * Adds a new comment to the comments list.
     * @param comment the comment to be added.
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Adds a list of comments to the comments list.
     * @param comments the comments to be added.
     */
    public void addComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}
