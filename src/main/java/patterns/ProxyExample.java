package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
interface Chat1 {
    void sendMessage(String addressee, String message);
    void sendBroadcastMessage(String message);
}

class Realisation implements Chat1{
    @Override
    public void sendMessage(String addressee, String message) {
        System.out.println("usual msg");
    }
    @Override
    public void sendBroadcastMessage(String message) {
        System.out.println("bc msg");
    }
}

class ProxyForSecurity implements Chat1 {
    Chat1 chat1;
    boolean isBroadcast;

    public ProxyForSecurity(boolean isBroadcast,Chat1 chat1) {
        this.chat1 = chat1;
        this.isBroadcast = isBroadcast;
    }

    @Override
    public void sendMessage(String addressee, String message) {
        chat1.sendMessage(addressee,message);
    }
    @Override
    public void sendBroadcastMessage(String message) {
        if (isBroadcast) {
            chat1.sendBroadcastMessage(message);
        } else {
            System.out.println("deny");
        }
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        Chat1 chat1 = new Realisation();
        chat1.sendBroadcastMessage("asd");
        Chat1 chat11 = new ProxyForSecurity(false,chat1);
        chat11.sendBroadcastMessage("asd");
        Chat1 chat12 = new ProxyForSecurity(true,chat1);
        chat12.sendBroadcastMessage("asd");
    }
}
