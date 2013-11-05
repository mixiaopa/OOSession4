package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

public class SmartParkWorker implements ParkWorker {
    private String workerStatus = CarConstant.ST_WK_FREE;
    private ArrayList<ParkingLot> parkingLots;

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

    @Override
    public void parkCarByWorker(List<Car> cars) {
        for (Car car : cars) {
            if (getWorkerStatus().equals(CarConstant.ST_WK_FREE) && car.getStatus().equals(CarConstant.ST_WAIT)) {
                setWorkerStatus(CarConstant.ST_WK_BUSY);
                ParkingLot comparedLot = parkingLots.get(0);
                for (ParkingLot lot : parkingLots) {
                    if (comparedLot.getLeftPlaceNumberNow() <= lot.getLeftPlaceNumberNow()) {
                        comparedLot = lot;
                    }
                }
                comparedLot.parkCar(car);

                setWorkerStatus(CarConstant.ST_WK_FREE);
            }
        }
    }

    @Override
    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

}
