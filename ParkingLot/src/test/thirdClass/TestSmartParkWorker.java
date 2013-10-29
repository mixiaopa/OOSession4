package thirdClass;

import org.junit.Before;

public class TestSmartParkWorker {

    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1000);
        new SmartParkWorker()
    }
}
