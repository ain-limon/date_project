import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Collections;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        if (!isValidDate()) {
            this.day = 1;
            this.month = 1;
            this.year = 1970;
        }
    }

    public boolean isValidDate() {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int maxDays = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                maxDays = 29;
            } else {
                maxDays = 28;
            }
        }
        return day <= maxDays;
    }

    public void updateDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        if (!isValidDate()) {
        }
    }

    public String getDayOfWeek() {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[dayOfWeek - 1];
    }

    public int calculateDifference(Date otherDate) {
        Calendar thisCalendar = new GregorianCalendar(year, month - 1, day);
        Calendar otherCalendar = new GregorianCalendar(otherDate.year, otherDate.month - 1, otherDate.day);
        long diffInMillis = Math.abs(thisCalendar.getTimeInMillis() - otherCalendar.getTimeInMillis());
        return (int) (diffInMillis / (24 * 60 * 60 * 1000));
    }

    public void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year);
    }

    @Override
    public int compareTo(Date otherDate) {
        if (year != otherDate.year) {
            return year - otherDate.year;
        }
        if (month != otherDate.month) {
            return month - otherDate.month;
        }
        return day - otherDate.day;
    }
}

public class Main {
    public static void main(String[] args) {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(29, 2, 2024);
        Date date3 = new Date(30, 2, 2023);

        date1.printDate();
        date2.printDate();
        date3.printDate();

        date1.updateDate(15, 3, 2023);
        date1.printDate();

        System.out.println(date2.getDayOfWeek());

        System.out.println(date1.calculateDifference(date2) + " days");

        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(new Date(10, 5, 2022));
        dateList.add(new Date(1, 1, 2023));
        dateList.add(new Date(29, 2, 2024));
        dateList.add(new Date(15, 3, 2023));

        Collections.sort(dateList);
        for (Date date : dateList) {
            date.printDate();
        }
    }
}