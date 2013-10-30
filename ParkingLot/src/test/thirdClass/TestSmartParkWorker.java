package thirdClass;

import org.junit.Before;
import org.junit.Test;
import thirdClass.utils.CarConstant;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestSmartParkWorker {

    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;
    private ArrayList<ParkingLot> parkingLots;

    @Before
    public void setUp() throws Exception {
        parkingLotOne = new ParkingLot(1);
        parkingLotTwo = new ParkingLot(2);
        parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
    }

    @Test
    public void shouldParkToParkingLotTwoWhenLotTwoHasMoreEmptySeats() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car);

        SmartParkWorker smartParkWorker = new SmartParkWorker();
        smartParkWorker.setWorkerStatus(CarConstant.ST_WK_FREE);
        smartParkWorker.setParkingLots(parkingLots);

        smartParkWorker.parkCarByWorker(cars);

        assertThat(parkingLotOne.getCarNumberNow(), is(0));
        assertThat(parkingLotOne.getLeftPlaceNumberNow(), is(1));
        assertThat(parkingLotTwo.getCarNumberNow(), is(1));
        assertThat(parkingLotTwo.getLeftPlaceNumberNow(), is(1));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
    }
}
