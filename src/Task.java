package src;

public class Task {
    private final String name;
    private final Date dead_line;

    public Task(String name, Date deadLine){
        this.name = name;
        this.dead_line = deadLine;
    }

    public Task(String name){
        this.name = name;
        this.dead_line = null;
    }

    public String getName(){
        return this.name;
    }

    public Date getDeadLine() {
        return this.dead_line;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + this.name + '\'' +
                ", dead line=" + this.dead_line.toString() +
                '}';
    }
}
