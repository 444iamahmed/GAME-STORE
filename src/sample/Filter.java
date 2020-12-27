package sample;

public class Filter {
    private TimePeriod timePeriod;


    private String order;

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    Filter()
    {
        timePeriod = TimePeriod.ALL_TIME;
        order = "asc";
    }
    public void setOrder(String ob) {
        order = ob;
    }
    public String getOrder() {
        return order;
    }


}
