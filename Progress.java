public class Progress {

    protected double courseProgress;
    protected Course course;
    protected double moduleProgress;
    protected Module module;

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
