package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
interface Operation {
    void start();
    void exec();
    void stop();
}

class UsualOperation implements Operation{
    public void start(){System.out.println("usual start");}
    public void exec(){System.out.println("usual exec");}
    public void stop(){System.out.println("usual stop");}
}

class SmthngWhatIWant {
    public void flyUp(){System.out.println("fly up");}
    public void fly(){System.out.println("fly");}
    public void land(){System.out.println("land");}
}

class PlaneOperation implements Operation{
    SmthngWhatIWant smthng = new SmthngWhatIWant();
    public void start(){smthng.flyUp();}
    public void exec(){smthng.fly();}
    public void stop(){smthng.land();}
}
public class AdapterExample {
    public static void main(String[] args) {
        Operation operation = new PlaneOperation();
        operation.start();
        operation.exec();
        operation.stop();
    }
}
