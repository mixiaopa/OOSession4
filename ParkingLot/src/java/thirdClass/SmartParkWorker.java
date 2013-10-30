package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

public class SmartParkWorker implements ParkWorker {
    private String workerStatus;
    private ArrayList<ParkingLot> parkingLots;

    public String getWorkerStatus() {
        return workerStatus;
    }

    @Override
    public void parkCarByWorker(List<Car> cars) {
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

    @Override
    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

}
