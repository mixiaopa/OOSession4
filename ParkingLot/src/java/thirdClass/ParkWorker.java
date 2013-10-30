package thirdClass;

import java.util.ArrayList;
import java.util.List;

public interface ParkWorker {
    public void parkCarByWorker(List<Car> cars);
    public void setParkingLots(ArrayList<ParkingLot> parkingLots);
    public void setWorkerStatus(String status);
}
