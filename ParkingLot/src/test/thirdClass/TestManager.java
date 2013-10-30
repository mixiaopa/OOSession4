package thirdClass;

import org.junit.Before;
import org.junit.Test;
import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestManager {

    private Car car;
    private ParkingLot parkingLot;
    private ArrayList<ParkingLot> parkingLots;
    private ArrayList<Car> cars;
    private List<ParkWorker> parkWorkers;
    private Manager manager;
    private ParkWorker parkWorker;

    @Before
    public void setUp() throws Exception {
        car = new Car(001, CarConstant.ST_WAIT);
        parkingLot = new ParkingLot(2);
        parkingLots = new ArrayList<ParkingLot>();
        cars = new ArrayList<Car>();
        parkWorkers = new ArrayList<ParkWorker>();

        parkingLots.add(parkingLot);
        cars.add(car);
        parkWorker = new SmartParkWorker();
        parkWorker.setParkingLots(parkingLots);

        parkWorkers.add(parkWorker);
        manager = new Manager();
        parkWorkers.add(manager);
        manager.setParkingLots(parkingLots);
        manager.setParkWorker(parkWorkers);
    }

    @Test
    public void shouldParkCar() throws Exception {
        parkWorker.setWorkerStatus(CarConstant.ST_WK_BUSY);
        manager.setWorkerStatus(CarConstant.ST_WK_FREE);

        manager.parkCar(cars);

        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(parkingLot.getCarNumberNow(), is(1));
    }

    @Test
    public void shouldParkCarByParkingWorker() throws Exception {
        parkWorker.setWorkerStatus(CarConstant.ST_WK_FREE);
        manager.setWorkerStatus(CarConstant.ST_WK_BUSY);

        manager.parkCar(cars);

        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(parkingLot.getCarNumberNow(), is(1));
    }
}
