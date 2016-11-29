package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
class Car {
    private String engine = null;
    private String brakes = null;
    private String wheels = null;
    public void setEngine(String engine) {this.engine = engine;}
    public void setBrakes(String brakes) {this.brakes = brakes;}
    public void setWheels(String wheels) {this.wheels = wheels;}
    public boolean isCarReady(){return ((engine!=null)&&(brakes!=null)&&(wheels!=null));}
    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", brakes='" + brakes + '\'' +
                ", wheels='" + wheels + '\'' +
                '}';
    }
}

interface ConveyorWorker {
    void doOperation(Car car);
}

abstract class Worker implements ConveyorWorker {
    private ConveyorWorker nextWorker;
    public ConveyorWorker setNextWorker(ConveyorWorker nextWorker) {
        this.nextWorker = nextWorker;
        return nextWorker;
    }
    public void doOperation(Car car){
        if (!car.isCarReady()) {
            makeSpecificOp(car);
        }
        if ((nextWorker != null) && (!car.isCarReady())) {
            nextWorker.doOperation(car);
        }
    }
    abstract void makeSpecificOp(Car car);
}
class WorkerW extends Worker{
    @Override
    void makeSpecificOp(Car car) {car.setWheels("wheels connect");}
}
class WorkerE extends Worker{
    @Override
    void makeSpecificOp(Car car) {car.setEngine("engine connect");}
}
class WorkerB extends Worker{
    @Override
    void makeSpecificOp(Car car) {car.setBrakes("brakes connect");}
}

public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car);
        Worker worker1 = new WorkerB();
        Worker worker2 = (Worker) worker1.setNextWorker(new WorkerE());
        worker2.setNextWorker(new WorkerW());
        worker1.doOperation(car);
        System.out.println(car);
    }
}
