package thirdClass;

import org.junit.Before;
import org.junit.Test;
import thirdClass.utils.CarConstant;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestParkingLot {
    private ParkingLot parkingLot;
    private Car car;
    private Car carTwo;
    private ArrayList<Car> cars;
    private ArrayList<ParkingLot> parkingLots;
    private Car carThree;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1000);
        car = new Car(001, CarConstant.ST_WAIT);
        carTwo = new Car(002, CarConstant.ST_WAIT);
        cars = new ArrayList<Car>();
        parkingLots = new ArrayList<ParkingLot>();
        cars.add(car);
        cars.add(carTwo);
        parkingLots.add(parkingLot);
        carThree = new Car(003, CarConstant.ST_WAIT);
    }

    @Test
    public void shouldChangeStatusOfParkingLotWhenNewCarParked() throws Exception {
        parkingLot.parkCar(car);

        assertThat(parkingLot.getCarNumberNow(), is(1));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(999));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(car.getParkingId(), is(1));
    }

    @Test
    public void shouldSetStatusTwiceWhenTwoCarsParked() throws Exception {
        parkingLot.parkCar(car);
        parkingLot.parkCar(carTwo);

        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(car.getParkingId(), is(1));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getParkingId(), is(2));
    }

    @Test
    public void shouldSetStatusToLeaveWhenCarLeaveParkingLot() throws Exception {
        parkingLot.parkCar(car);
        parkingLot.carLeave(car);

        assertThat(parkingLot.getCarNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(1000));
        assertThat(car.getStatus(), is(CarConstant.ST_LEFT));
    }

    @Test
    public void shouldNotParkMoreCarWhenParkingLotIsFull() throws Exception {
        ParkingLot parkingLotSmall = new ParkingLot(1);

        parkingLotSmall.parkCar(car);
        parkingLotSmall.parkCar(carTwo);

        assertThat(parkingLotSmall.getCarNumberNow(), is(1));
        assertThat(parkingLotSmall.getLeftPlaceNumberNow(), is(0));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_WAIT));
    }

    @Test
    public void shouldParkCarByWorker() throws Exception {
        NormalParkWorker parkWorker = new NormalParkWorker(CarConstant.ST_WK_FREE);

        parkWorker.setParkingLots(parkingLots);
        parkWorker.parkCarByWorker(cars);

        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
    }

    @Test
    public void shouldWaitIfWorkerIsBusy() throws Exception {
        NormalParkWorker parkWorker = new NormalParkWorker(CarConstant.ST_WK_BUSY);

        parkWorker.setParkingLots(parkingLots);
        parkWorker.parkCarByWorker(cars);

        assertThat(parkingLot.getCarNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(1000));
        assertThat(car.getStatus(), is(CarConstant.ST_WAIT));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_WAIT));
    }

    @Test
    public void shouldParkToParkingLotTwoIfLotOneIsFull() throws Exception {
        cars.add(carThree);
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLotSmall = new ParkingLot(1);
        parkingLots.add(parkingLotSmall);
        parkingLots.add(parkingLot);
        NormalParkWorker parkWorker = new NormalParkWorker(CarConstant.ST_WK_FREE);

        parkWorker.setParkingLots(parkingLots);
        parkWorker.parkCarByWorker(cars);

        assertThat(parkingLotSmall.getCarNumberNow(), is(1));
        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLotSmall.getLeftPlaceNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carThree.getStatus(), is(CarConstant.ST_PARKED));
    }
}
