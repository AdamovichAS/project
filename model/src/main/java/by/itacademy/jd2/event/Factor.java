package by.itacademy.jd2.event;

public class Factor {
    private Long id;
    private String name;
    private double value;
    private Long eventID;

    public Factor(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public Factor(Long id, String name, double value, Long eventID) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.eventID = eventID;
    }

    public Factor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }
}
