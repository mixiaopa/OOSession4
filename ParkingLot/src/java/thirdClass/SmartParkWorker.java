package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;

public class SmartParkWorker implements ParkWorker {
    private String workerStatus;

    public SmartParkWorker(String status) {
        this.workerStatus = status;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

    public void parkCarByWorker(ArrayList<Car> cars, ArrayList<ParkingLot> parkingLots) {
        int countCar = 0;
        for (; countCar < cars.size(); countCar++) {
            Car car = cars.get(countCar);
            if (getWorkerStatus().equals(CarConstant.ST_WK_FREE)) {
                setWorkerStatus(CarConstant.ST_WK_BUSY);
                ParkingLot comparedLot = parkingLots.get(0);
                for (ParkingLot lot: parkingLots) {
                    if (comparedLot.getLeftPlaceNumberNow() <= lot.getLeftPlaceNumberNow() ) {
                        comparedLot = lot;
                    }
                }
                comparedLot.parkCar(car);

                setWorkerStatus(CarConstant.ST_WK_FREE);
            }
        }
    }
}
