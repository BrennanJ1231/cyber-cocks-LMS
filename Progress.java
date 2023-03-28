/**

The Progress class represents the progress of a user in a course or module and calculate the progress.
*/
public class Progress {

    protected double courseProgress;
    protected Course course;
    protected double moduleProgress;
    protected Module module;
    
    /** 
     * This methid calculates the progress of the user
    @param module The module object to calculate the progress of
    @return The progress of the module as a decimal value 
    */
    public double calculateModuleProgress(Module module){
        double numAssignment = module.test.size();
        double completed = 0;
        for(int i = 0; i < module.test.size(); i++) {
            if(module.test.get(i).completed) {
                completed++;
            }
        }
        return completed/numAssignment;
}
}
