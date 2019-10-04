package by.itacademy.jd2.event;

import java.util.Date;
import java.util.Map;

public class Event {
    private String name;
    private Date startTime;
    private Date endTime;
    private Map<String,Double> factors;
    private String result;

    public Event() {
    }

    public Event(String name, Date startTime, Date endTime, Map<String, Double> factors) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.factors = factors;
        this.result = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Map<String, Double> getFactors() {
        return factors;
    }

    public void setFactors(Map<String, Double> factors) {
        this.factors = factors;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
