package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
class CollFirst extends MyCollection {
    public CollFirst(SortOrder sortOrder) {super(sortOrder);}
    @Override
    void sort() {System.out.println("first sort "+sortOrder.defineSortOrder());        }
}
class CollSecond extends MyCollection{
    public CollSecond(SortOrder sortOrder) {super(sortOrder);}
    @Override
    void sort() {System.out.println("second sort"+sortOrder.defineSortOrder());    }
}

abstract class MyCollection {
    SortOrder sortOrder;
    public MyCollection(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
    abstract void sort();
}

interface SortOrder {
    String defineSortOrder();
}

class NaturalOrder implements SortOrder{
    public String defineSortOrder() {return "sort natural";}
}
class ReverseOrder implements SortOrder{
    public String defineSortOrder() {return "sort reversed";}
}



public class BridgeExample {
    public static void main(String[] args) {
        MyCollection myCollection1 = new CollFirst(new ReverseOrder());
        myCollection1.sort();
        MyCollection myCollection2 = new CollSecond(new ReverseOrder());
        myCollection2.sort();
    }

}
