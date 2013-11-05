package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.List;

public class Manager extends SmartParkWorker {
    private List<ParkWorker> parkWorkers;

    public Manager() {
        super();
    }

    public void setParkWorker(List<ParkWorker> parkWorkers) {
        this.parkWorkers = parkWorkers;
    }

    public void parkCar(List<Car> cars) {
        for (Car car : cars) {
            if (!car.getStatus().equals(CarConstant.ST_PARKED)) {
                for (ParkWorker parkWorker : parkWorkers) {
                    parkWorker.parkCarByWorker(cars);
                }
            }
        }
    }
}
