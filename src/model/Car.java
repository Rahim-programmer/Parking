package model;

import utils.LicensePlate;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {
    private String licensePlate;
    private CarState state;
    private Parking parking;

    public Car() {
        licensePlate = LicensePlate.issue();
        state = CarState.DRIVING;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void drive(LocalDateTime time){
        parking.release(this, time);
        state = CarState.DRIVING;
        System.out.printf("%s: %s -> DRIVE%n", time, this.licensePlate);
    }

    public void park(Parking parking, LocalDateTime time) {
        this.parking = parking;
        if (parking.park(this, time)) {
            state = CarState.PARKED;
            System.out.printf("%s: %s -> PARKED%n", time, this.licensePlate);
        }
    }

    public void changeState(Car car, LocalDateTime time, Parking randomParking) {
        if (isParked()) {
            car.drive(time);
        } else {
            car.park(randomParking, time);
        }
    }

    private boolean isParked(){
        return state == CarState.PARKED;
    }

    public boolean willChangeState(){
        return state.willChangeState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}
