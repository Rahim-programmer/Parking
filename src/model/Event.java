package model;

import java.time.LocalDateTime;

public class Event {
    private LocalDateTime time;
    private String licensePlate;
    private Type type;

    public Event(Type type, LocalDateTime time, Car car) {
        this.type = type;
        this.time = time;
        this.licensePlate = car.getLicensePlate();
    }

    public static Event registerEnter(Car car, LocalDateTime time) {
        return new Event(Type.ENTERED, time, car);
    }

    public static Event registerExit(Car car, LocalDateTime time) {
        return new Event(Type.EXITED, time, car);
    }

    @Override
    public String toString() {
        return "Event{" +
                "time=" + time +
                ", licensePlate='" + licensePlate + '\'' +
                ", type=" + type +
                '}';
    }

    public enum Type{
        ENTERED, EXITED
    }
}
