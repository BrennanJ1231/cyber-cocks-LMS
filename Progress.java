public class Progress {

    protected double courseProgress;
    protected Course course;
    protected double moduleProgress;
    protected Module module;

    public double calculateModuleProgress(Module module){
        int numAssignment = module.quizzes.size() + module.test.size();
        int completed = 0;
        for(int i = 0; i < module.quizzes.size(); i++) {
            if(module.quizzes.get(i).completed) {
                completed++;
            }
        }
        for(int i = 0; i < module.test.size(); i++) {
            if(module.test.get(i).completed) {
                completed++;
            }
        }
        return completed/numAssignment;
}
}
