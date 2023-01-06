package model;

import java.time.LocalDateTime;
import java.util.*;

public class Parking {
    private int slot;
    private Set<Car> parkedCars = new HashSet<>();
    private Map<Car, List<Event>> events = new HashMap<>();

    public Map<Car, List<Event>> getEvents() {
        return events;
    }

    private Parking(int slot) {
        this.slot = slot;
    }

    public static Parking make(int maxParkingSlots) {
        return new Parking(maxParkingSlots);
    }

    private boolean hasVacantSlot(){
        return parkedCars.size() != slot;
    }

    public boolean park(Car car, LocalDateTime time) {
        if (!hasVacantSlot()) {
            return false;
        }
        if (parkedCars.contains(car)) {
            return false;
        }

        parkedCars.add(car);

        List<Event> carEvents = events.get(car);
        if(carEvents == null){
            carEvents = new ArrayList<>();
            events.put(car, carEvents);
        } else {
            carEvents.add(Event.registerEnter(car, time));
        }
        return true;
    }

    public void release(Car car, LocalDateTime time) {
        if (parkedCars.contains(car)) {
            events.get(car).add(Event.registerExit(car, time));
            parkedCars.remove(car);
        }
    }
}
