package thirdClass;

import org.junit.Before;
import org.junit.Test;
import thirdClass.utils.CarConstant;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestPercentageParkWorker {
    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;
    private ArrayList<ParkingLot> parkingLots;

    @Before
    public void setUp() throws Exception {
        parkingLotOne = new ParkingLot(10);
        parkingLotTwo = new ParkingLot(2);
        parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
    }

    @Test
    public void shouldParkToLotTwoWhenTwosUsageSmaller() throws Exception {
        Car carOne = new Car(001, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(carOne);

        Car carTwo = new Car(001, CarConstant.ST_WAIT);
        parkingLotOne.parkCar(carTwo);

        PercentageParkWorker percentageParkWorker = new PercentageParkWorker(CarConstant.ST_WK_FREE);

        percentageParkWorker.parkCarByWorker(cars, parkingLots);

        assertThat(parkingLotOne.getCarNumberNow(), is(1));
        assertThat(parkingLotOne.getLeftPlaceNumberNow(), is(9));
        assertThat(parkingLotTwo.getCarNumberNow(), is(1));
        assertThat(parkingLotTwo.getLeftPlaceNumberNow(), is(1));
        assertThat(carOne.getStatus(), is(CarConstant.ST_PARKED));
    }

    @Test
    public void shouldParkToLotOneWhenOnesUsageSmaller() throws Exception {
        Car carOne = new Car(001, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(carOne);

        Car carTwo = new Car(001, CarConstant.ST_WAIT);
        parkingLotOne.parkCar(carTwo);

        Car carThree = new Car(001, CarConstant.ST_WAIT);
        parkingLotTwo.parkCar(carThree);

        PercentageParkWorker percentageParkWorker = new PercentageParkWorker(CarConstant.ST_WK_FREE);

        percentageParkWorker.parkCarByWorker(cars, parkingLots);

        assertThat(parkingLotOne.getCarNumberNow(), is(2));
        assertThat(parkingLotOne.getLeftPlaceNumberNow(), is(8));
        assertThat(parkingLotTwo.getCarNumberNow(), is(1));
        assertThat(parkingLotTwo.getLeftPlaceNumberNow(), is(1));
        assertThat(carOne.getStatus(), is(CarConstant.ST_PARKED));
    }
}
