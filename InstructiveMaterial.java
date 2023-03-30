/**
 * Represents instructive materials for a course or module  
 */
public class InstructiveMaterial {
    protected String name;
    protected String content;

/**
Constructor for InstructiveMaterial class.
@param name the name of the instructive material.
@param content the content of the instructive material.
*/
    public InstructiveMaterial(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
