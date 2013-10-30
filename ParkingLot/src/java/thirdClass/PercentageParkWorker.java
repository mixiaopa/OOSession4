package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

public class PercentageParkWorker implements ParkWorker {
    private String workerStatus;

    public PercentageParkWorker(String status) {
        this.workerStatus = status;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    @Override
    public void parkCarByWorker(List<Car> cars) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        //To change body of implemented methods use File | Settings | File Templates.
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
