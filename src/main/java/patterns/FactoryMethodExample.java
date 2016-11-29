package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
enum DB {MYSQL,DERBY}

interface SConnection {
    void connect();
}
class MySqlSConnection implements SConnection {
    public void connect() {System.out.println("mysql");}
}
class DerbySConnection implements SConnection {
    public void connect() {System.out.println("derby");}
}

interface ConnCreator {
    SConnection create();
}
class MysqlCreator implements ConnCreator {
    public SConnection create() {return new MySqlSConnection();}
}
class DerbyCreator implements ConnCreator {
    public SConnection create() {return new DerbySConnection();}
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        ConnCreator connCreator = getCreator(DB.DERBY);
        SConnection connection = connCreator.create();
        connection.connect();
    }

    private static ConnCreator getCreator(DB db) {
        switch (db) {
            case DERBY:
                return new DerbyCreator();
            case MYSQL:
                return new MysqlCreator();
            default:
                throw new IllegalArgumentException();
        }

    }
}
