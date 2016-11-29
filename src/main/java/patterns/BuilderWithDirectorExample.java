package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
enum Sweetener {SUGAR,WITHOUT}
enum Addon{MILK,COGNAC}

class Coffee {
    private Sweetener sweetener;
    private Addon addon;

    public void setSweetener(Sweetener sweetener) {this.sweetener = sweetener;}
    public void setAddon(Addon addon) {this.addon = addon;}

    @Override
    public String toString() {
        return "Coffee{" +
                "sweetener=" + sweetener +
                ", addon=" + addon +
                '}';
    }
}

abstract class CoffeeBuilder {
    Coffee coffee;
    void makeBase(){coffee = new Coffee();}
    abstract void addAddon();
    abstract void addSweetener();
    Coffee getCoffee(){return coffee;}
}

class LatteBuilder extends CoffeeBuilder {
    public void addAddon() {coffee.setAddon(Addon.MILK);}
    public void addSweetener() {coffee.setSweetener(Sweetener.SUGAR);}
}

class CoffeeWithCognacBuilder extends CoffeeBuilder {
    public void addAddon() {coffee.setAddon(Addon.COGNAC);}
    public void addSweetener() {coffee.setSweetener(Sweetener.WITHOUT);}
}

class Waiter {
    CoffeeBuilder builder;
    Coffee makeCoffee() {
        builder.makeBase();
        builder.addAddon();
        builder.addSweetener();
        return builder.getCoffee();
    }
    public void setBuilder(CoffeeBuilder builder) {this.builder = builder;}
}


public class BuilderWithDirectorExample {
    static Waiter waiter = new Waiter();
    public static void main(String[] args) {
        waiter.setBuilder(new LatteBuilder());
        Coffee coffee1 = waiter.makeCoffee();
        System.out.println(coffee1);
        waiter.setBuilder(new CoffeeWithCognacBuilder());
        Coffee coffee2 = waiter.makeCoffee();
        System.out.println(coffee2);
    }
}
