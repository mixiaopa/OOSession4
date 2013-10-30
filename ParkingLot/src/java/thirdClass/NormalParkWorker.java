package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

public class NormalParkWorker implements ParkWorker {
    private String workerStatus;
    private List<ParkingLot> parkingLots;

    public NormalParkWorker(String status) {
        this.workerStatus = status;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    @Override
    public void parkCarByWorker(List<Car> cars) {
        int countCar = 0;
        int countLot = 0;
        for (; countCar < cars.size(); countCar++) {
            Car car = cars.get(countCar);
            if (getWorkerStatus().equals(CarConstant.ST_WK_FREE)) {
                setWorkerStatus(CarConstant.ST_WK_BUSY);
                ParkingLot parkingLot = parkingLots.get(countLot);
                parkingLot.parkCar(car);
                if (parkingLot.getLeftPlaceNumberNow() == 0) {
                    countLot++;
                }
                setWorkerStatus(CarConstant.ST_WK_FREE);
            }
        }
    }

    @Override
    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

}

