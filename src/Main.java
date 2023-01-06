import model.Event;
import utils.Simulation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Event> data = Simulation.builder()
                .days(60)
                .step(10)
                .carAmount(200)
                .addParking(20)
                .build()
                .run();

        System.out.println(data.size());
        data.forEach(System.out::println);
    }
}