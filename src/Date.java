package src; 

public class Date {
    private int month;
    private int day;
    private int year;

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