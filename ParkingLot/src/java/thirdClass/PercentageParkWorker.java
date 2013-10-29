package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;

public class PercentageParkWorker implements ParkWorker {
    private String workerStatus;

    public PercentageParkWorker(String status) {
        this.workerStatus = status;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

    public void parkCarByWorker(ArrayList<Car> cars, ArrayList<ParkingLot> parkingLots) {
        for (Car car:cars) {
            if (getWorkerStatus().equals(CarConstant.ST_WK_FREE)) {
                setWorkerStatus(CarConstant.ST_WK_BUSY);
                ParkingLot comparedLot = parkingLots.get(0);
                for (ParkingLot lot: parkingLots) {
                    double percentageForCompared = (double) comparedLot.getLeftPlaceNumberNow() / (double) comparedLot.getMaxNumberOfCar();
                    double percentageForLoop = (double) lot.getLeftPlaceNumberNow() / (double) lot.getMaxNumberOfCar();
                    if (percentageForCompared <= percentageForLoop) {
                        comparedLot = lot;
                    }
                }
                comparedLot.parkCar(car);

                setWorkerStatus(CarConstant.ST_WK_FREE);
            }
        }
    }
}
