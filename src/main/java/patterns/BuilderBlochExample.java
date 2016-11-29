package patterns;

/**
 * @author kara.vladimir2@gmail.com.
 */
class HugeClass {
    //Required parameters
    private String par1;
    private String par2;
    //optional parameters
    private String par3;
    private String par4;
    private String par5;

    private HugeClass(Builder builder) {
        this.par1 = builder.inPar1;
        this.par2 = builder.inPar2;
        this.par3 = builder.inPar3;
        this.par4 = builder.inPar4;
        this.par5 = builder.inPar5;
    }

    @Override
    public String toString() {
        return "HugeClass{" +
                "par1='" + par1 + '\'' +
                ", par2='" + par2 + '\'' +
                ", par3='" + par3 + '\'' +
                ", par4='" + par4 + '\'' +
                ", par5='" + par5 + '\'' +
                '}';
    }

    public static class Builder {
        private String inPar1;
        private String inPar2;
        private String inPar3 = "def1";
        private String inPar4 = "def2";
        private String inPar5;

        public Builder(String inPar1, String inPar2) {
            this.inPar1 = inPar1;
            this.inPar2 = inPar2;
        }

        public Builder setPar3(String par3) {
            this.inPar3 = par3;
            return this;
        }
        public Builder setPar4(String par4) {
            this.inPar4 = par4;
            return this;
        }
        public Builder setPar5(String par5) {
            this.inPar5 = par5;
            return this;
        }

        public HugeClass build() {
            return new HugeClass(this);
        }
    }
}

public class BuilderBlochExample {
    public static void main(String[] args) {
        HugeClass.Builder builder1 = new HugeClass.Builder("req1", "req2");
        HugeClass hugeClass1 = builder1.setPar4("Hey").build();
        System.out.println(hugeClass1);
        HugeClass.Builder builder2 = new HugeClass.Builder("r1", "r2");
        HugeClass hugeClass2 = builder2.setPar3("opt3").setPar4("opt4").setPar5("opt5").build();
        System.out.println(hugeClass2);
    }
}
