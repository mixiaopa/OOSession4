package thirdClass;

import thirdClass.utils.CarConstant;

import java.util.ArrayList;
import java.util.List;

public class SuperManager extends Manager {

    private List<Manager> managers;

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public void parkCarByManager(ArrayList<Car> cars) {
        for (Car car : cars) {
            if (!car.getStatus().equals(CarConstant.ST_PARKED)) {
                for (Manager manager : managers) {
                    manager.parkCar(cars);
                }
            }
        }

    }
}
