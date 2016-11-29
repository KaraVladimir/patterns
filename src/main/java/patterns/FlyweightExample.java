package patterns;

import java.util.*;

/**
 * @author kara.vladimir2@gmail.com.
 */
class Bell {
    private Integer note;
    private String string;
    public Bell(Integer note) {
        this.note = note;
    }
    public Integer getNote() {return note;}
}

class BellKeeper {
    private static Map<Integer, Bell> bellMap = new HashMap<Integer, Bell>();

    public static Bell getBell(Integer i) {
        Bell bell = bellMap.get(i);
        if (bell == null) {
            bell = new Bell(i);
            bellMap.put(i, bell);
        }
        return bell;
    }
}

public class FlyweightExample {
    public static void main(String[] args) {
        Long memFreeStart = Runtime.getRuntime().freeMemory();
        int size = 500000;
        int bound = 50;
        List<Bell> bells = new ArrayList<Bell>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int j = random.nextInt(bound);
            bells.add(
                    getNewBell(j) //average memory 18 500 000
//                    getNewBellFromKeeper(j) //average memory 6 000 000
            );
        }
        Long memFreeEnd = Runtime.getRuntime().freeMemory();
        System.out.println(memFreeStart-memFreeEnd);
    }

    private static Bell getNewBellFromKeeper(int j) {
        return BellKeeper.getBell(j);
    }

    private static Bell getNewBell(int j) {
        return new Bell(j);
    }

}
