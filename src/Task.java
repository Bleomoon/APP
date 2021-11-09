package src;

import java.io.Serializable;

public class Task implements Serializable {
    private final String name;
    private final Date dead_line;
	private static final long serialVersionUID = 1L;

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
		if(this.getDeadLine() != null)
		{
			return "Task: {name='" + this.name + "', dead line='" + this.dead_line + "'}";
		}
		else
		{
			return "Task: {name='" + this.name + "'}";
		}
    }
}
