package src; 

import java.io.Serializable;

public class Date implements Serializable {
    private int month;
    private int day;
    private int year;
	private static final long serialVersionUID = 1L;
	
    public Date(int year, int day, int month) 
    {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public String toString()
    {
        return day + " " + month + " " + year;
    }
}  