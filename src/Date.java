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

    public static boolean isBissextile (int month)
    {
        if ((month % 4) == 0))
        {
            if ((month % 100) == 0)
            {
                return (month % 100) == 0;
            }
            else
                return true;
        }
        else 
            return false;
    }

    public static boolean isValidDate(int year, int day, int month)
    {
        boolean ret = true;

        ret = (month > 0 && month < 13);

        if (ret)
        {
            if ( month == 2)
            {
                if (isBissextile(month))
                    ret = (month == 29);
                else
                    ret = (month == 28);
            }
            else if ((month % 2 == 1 && month <= 8) || (month % 2 == 0 && month >= 8))
            {
                ret = (month == 31);
            }
            else
            {
                ret = (month == 30);
            }
        }
        return ret;
    }

    @Override
    public String toString()
    {
        return day + "/" + month + "/" + year;
    }
}  