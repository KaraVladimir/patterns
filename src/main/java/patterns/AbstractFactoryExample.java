package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */


interface SingleConnection {
    void connect();
}

interface PoolConnection {
    void poolConnect();
}

class MysqlSingleConnection implements SingleConnection{
    public void connect() {System.out.println(this.getClass().getSimpleName());}
}
class MysqlPoolConnection implements PoolConnection{
    public void poolConnect() {System.out.println(this.getClass().getSimpleName());}
}
class DerbyConnection implements SingleConnection{
    public void connect() {System.out.println(this.getClass().getSimpleName());}
}
class DerbyPoolConnection implements PoolConnection{
    public void poolConnect() {System.out.println(this.getClass().getSimpleName());}
}

interface AbstractFactory {
    SingleConnection connectSingle();
    PoolConnection connectPool();
}

class MysqlFabric implements AbstractFactory{
    public SingleConnection connectSingle() {return new MysqlSingleConnection();}
    public PoolConnection connectPool() {return new MysqlPoolConnection();}
}

class DerbyFabric implements AbstractFactory {
    public SingleConnection connectSingle() {return new DerbyConnection();}
    public PoolConnection connectPool() {return new DerbyPoolConnection();}
}

enum Type {MySql,Derby}

public class AbstractFactoryExample {

    public static void main(String[] args) {
        AbstractFactoryExample example = new AbstractFactoryExample();
        AbstractFactory factory = example.createFactory(Type.Derby);
        factory.connectPool().poolConnect();
        factory.connectSingle().connect();
    }

    public AbstractFactory createFactory(Type type) {
        switch (type) {
            case MySql:
                return new MysqlFabric();
            case Derby:
                return new DerbyFabric();
            default:
                throw new RuntimeException();
        }
    }
}
