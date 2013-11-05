package thirdClass;

import org.junit.Before;
import org.junit.Test;
import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestSuperManager {

    private Car car;
    private ParkingLot parkingLot;
    private ArrayList<ParkingLot> parkingLots;
    private ArrayList<Car> cars;
    private List<Manager> managers;
    private List<ParkWorker> parkWorkers;
    private ParkWorker parkWorker;
    private SuperManager superManager;
    private Manager manager;

    @Before
    public void setUp() throws Exception {
        car = new Car(001, CarConstant.ST_WAIT);
        parkingLot = new ParkingLot(2);
        parkingLots = new ArrayList<ParkingLot>();
        cars = new ArrayList<Car>();

        parkingLots.add(parkingLot);
        cars.add(car);

        parkWorker = new SmartParkWorker();
        parkWorker.setParkingLots(parkingLots);

        parkWorkers = new ArrayList<ParkWorker>();
        parkWorkers.add(parkWorker);

        manager = new Manager();
        parkWorkers.add(manager);
        manager.setParkingLots(parkingLots);
        manager.setParkWorker(parkWorkers);

        managers = new ArrayList<Manager>();
        managers.add(manager);
        manager.setParkingLots(parkingLots);

        superManager = new SuperManager();
        managers.add(superManager);
        superManager.setParkingLots(parkingLots);
        superManager.setManagers(managers);
    }

    @Test
    public void shouldAskManagerParkCar() throws Exception {
        manager.setWorkerStatus(CarConstant.ST_WK_BUSY);
        superManager.setWorkerStatus(CarConstant.ST_WK_FREE);

        superManager.parkCarByManager(cars);

        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(parkingLot.getCarNumberNow(), is(1));
    }

    @Test
    public void shouldParkCarByParkingWorker() throws Exception {
        manager.setWorkerStatus(CarConstant.ST_WK_FREE);
        superManager.setWorkerStatus(CarConstant.ST_WK_BUSY);

        superManager.parkCarByManager(cars);

        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(parkingLot.getCarNumberNow(), is(1));
    }
}
