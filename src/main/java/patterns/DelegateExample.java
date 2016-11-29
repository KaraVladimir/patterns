package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
interface Profession {
    void doProfessionalStuff();
}

class Singer implements Profession {
    public void doProfessionalStuff() {System.out.println("sing");}
}
class Writer implements Profession {
    public void doProfessionalStuff() {System.out.println("write");}
}

interface Person {
    void work();
}

class Human implements Person {
    Profession currentProfession;
    @Override
    public void work() {
        currentProfession.doProfessionalStuff();
    }
    public void setCurrentProfession(Profession currentProfession) {
        this.currentProfession = currentProfession;
    }
}


public class DelegateExample {
    public static void main(String[] args) {
        Human human = new Human();
        human.setCurrentProfession(new Singer());
        human.work();
        human.setCurrentProfession(new Writer());
        human.work();
    }
}
