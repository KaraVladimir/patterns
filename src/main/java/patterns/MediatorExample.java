package patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kara.vladimir2@gmail.com.
 */
interface User {
    void sendBroadcastMsg(String msg);
    void sendMsg(User addressee,String msg);
    void recipeMsg(String msg);
    String getName();
    void login(Chat chat);
    void logout(Chat chat);
}

interface Chat {
    void sendBroadcastMsg(User currUser,String msg);
    void sendMsg(User currUser,User addressee,String msg);
    boolean addUser(User e);
    boolean removeUser(User e);
}
class ChatImpl implements Chat {
    Set<User> users = new HashSet<>();
    public void sendBroadcastMsg(User currUser, String msg) {
        if (users.contains(currUser)) {
            for (User user : users) {
                user.recipeMsg(currUser.getName()+":"+msg);
            }
        }
    }
    public void sendMsg(User currUser, User addressee, String msg) {
        if ((users.contains(currUser)) && (users.contains(addressee))) {
            addressee.recipeMsg(currUser.getName()+" to "+addressee.getName()+":"+msg);
        }
    }
    public boolean addUser(User e) {
        return users.add(e);
    }
    public boolean removeUser(User e) {
        return users.remove(e);
    }
}

abstract class AbstractUser implements User {
    String name;
    Chat chat;
    boolean isAdmin;
    public AbstractUser(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }
    public void sendBroadcastMsg(String msg) {
        if (isAdmin)chat.sendBroadcastMsg(this, msg);
    }
    public void sendMsg(User addressee, String msg) {
        chat.sendMsg(this,addressee,msg);
    }
    public void recipeMsg(String msg) {
        System.out.println(msg);
    }
    public String getName() {return name;}
    public void login(Chat chat) {
        this.chat = chat;
        chat.addUser(this);
    }
    public void logout(Chat chat) {
        this.chat = null;
        chat.removeUser(this);
    }
}

class Admin extends AbstractUser {
    public Admin(String name) {
        super(name, true);
    }
}

class UsUser extends AbstractUser {
    public UsUser(String name) {
        super(name, false);
    }
}

public class MediatorExample {
    public static void main(String[] args) {
        Chat chat = new ChatImpl();
        User userUs1 = new UsUser("us1");
        User userUs2 = new UsUser("us2");
        User userAdmin = new Admin("admin");
        userUs1.login(chat);
        userUs2.login(chat);
        userAdmin.login(chat);
        userUs1.sendMsg(userUs2,"hey");
        userUs2.sendBroadcastMsg("User");
        userAdmin.sendBroadcastMsg("Admin");
    }
}
