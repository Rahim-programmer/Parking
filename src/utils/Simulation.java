package utils;

import model.Car;
import model.Event;
import model.Parking;

import java.time.LocalDateTime;
import java.util.*;

public class Simulation {
    private int days;
    private int step;
    private List<Parking> parking;
    private Set<Car> cars;

    public static Builder builder(){
        return new Builder();
    }

    private Simulation(Builder builder) {
        this.days = builder.days;
        this.step = builder.stepInMinutes;
        this.cars = initCars(builder.carAmount);
        this.parking = builder.parking;
    }

    private Set<Car> initCars(int carAmount) {
        Set<Car> set = new HashSet<>();
        for (int i = carAmount; i > 0; i--) {
            Car car = new Car();
            set.add(car);
        }

        return Collections.unmodifiableSet(set);
    }

    public List<Event> run(){
        LocalDateTime current = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime bound = current.plusDays(days);

        while (current.isBefore(bound)) {
            current = current.plusMinutes(step);
            oneStep(current);
        }

        List<Event> list = new ArrayList<>();
        for (var p : parking) {
            for (var events : p.getEvents().values()) {
                list.addAll(events);
            }
        }

        return Collections.unmodifiableList(list);
    }

    private void oneStep(LocalDateTime time){
        for (var car : cars) {
            if (car.willChangeState()){
                car.changeState(car, time, getRandomParking());
            }
        }
    }

    private Parking getRandomParking(){
        Collections.shuffle(parking);
        return parking.get(0);
    }


    public static final class Builder{
        private int days = 30;
        private int stepInMinutes = 5;
        private int carAmount = 50;
        private List<Parking> parking = new ArrayList<>();

        public Builder days(int days) {
            this.days = days;
            return this;
        }

        public Builder step(int stepInMinutes){
            this.stepInMinutes = stepInMinutes;
            return this;
        }

        public Builder carAmount(int carAmount) {
            this.carAmount = carAmount;
            return this;
        }

        public Builder addParking(int maxParkingSlot){
            parking.add(Parking.make(maxParkingSlot));
            return this;
        }

        public Simulation build(){
            return new Simulation(this);
        }
    }
}
