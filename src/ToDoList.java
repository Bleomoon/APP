package src;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> tasks;
    
    public ToDoList(){
        tasks = new ArrayList<Task>();
    }
    
    public int addTask(Task t){
        tasks.add(t);
        return tasks.size()-1;
    }
    public boolean isContent(String s){
        boolean b = false;
        for(Task t : tasks){
            b = b || (s == t.getName());
        }
        return b;
    }
    
    public Task getTask(int idTask){
        try {
            return tasks.get(idTask);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
    
    public boolean swap(int id_task){
        try{
            Task t = tasks.get(id_task);
            tasks.remove(t);
            tasks.add(0, t);
            return true;
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
        
    }
    
    public int getSize(){
        return tasks.size();
    }
    
    public boolean deleteTask(int id_task){
        try{
            Task t = tasks.get(id_task);
            tasks.remove(t);
            return true;
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }
    
}
