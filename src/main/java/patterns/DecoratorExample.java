package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
interface Painting {
    void hang();
}

class Picture implements Painting{
    public void hang() {
        System.out.println("Picture");
    }
}

abstract class DecoratorForPainting implements Painting{
    Painting painting;
    public DecoratorForPainting(Painting painting) {
        this.painting = painting;
    }
    public void hang() {
        painting.hang();
    }
}

class Frame extends DecoratorForPainting {
    public Frame(Painting painting) {
        super(painting);
    }
    @Override
    public void hang() {
        super.hang();
        System.out.println("    with frame");
    }
}

class BackLight extends DecoratorForPainting {
    public BackLight(Painting painting) {
        super(painting);
    }
    @Override
    public void hang() {
        super.hang();
        System.out.println("    with backlight");
    }
}

public class DecoratorExample {
    public static void main(String[] args) {
        new BackLight(new Frame(new Picture())).hang();
        new Frame(new Picture()).hang();
    }
}
