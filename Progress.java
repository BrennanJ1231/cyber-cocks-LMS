
/**
The Progress class represents the progress of a student in a course and its modules.
It has properties for the progress of the current course and module, as well as the course and module objects.
*/
public class Progress {

    protected double courseProgress;
    protected Course course;
    protected double moduleProgress;
    protected Module module;
    
    /**
    Constructs a new Progress object with default values of 0 for courseProgress and moduleProgress.
    */
    public Progress() {
        courseProgress = 0;
        moduleProgress = 0;
    }
    
    /**
    Calculates the progress of the given module.
    @param module The module for which to calculate the progress.
    @return The percentage of assignments completed in the module.
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
    
    /**
    Calculates the progress of the given course based on the progress of its modules.
    @param course The course for which to calculate the progress.
    @return The percentage of modules completed in the course.
    */
    public double calculateCourseProgress(Course course) {
        double numModules = course.modules.size();
        double completed = 0;
        for(int i = 0; i< course.modules.size(); i ++) {
            if(calculateModuleProgress(course.modules.get(i)) == 100) {
                completed++;
            }
        }
        return completed/numModules;
    }
}





















