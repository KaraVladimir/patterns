package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
enum NotLazySingleton {
    INSTANCE;
    public void operation() {System.out.println("Something do");}
}

class LazySingletonWithSynchro {
    private static LazySingletonWithSynchro instance;
    private LazySingletonWithSynchro() {}
    public static LazySingletonWithSynchro getInstance() {
        if (instance == null) {
            synchronized (LazySingletonWithSynchro.class) {
                if (instance == null) {
                    instance = new LazySingletonWithSynchro();
                }
            }
        }
        return instance;
    }
    public void operation() {System.out.println("Something do");}
}

public class SingletonExample {
    public static void main(String[] args) {
        LazySingletonWithSynchro.getInstance().operation();
        NotLazySingleton.INSTANCE.operation();
    }
}
