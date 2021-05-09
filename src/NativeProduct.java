
public class NativeProduct implements Product{

    private String name;

    public NativeProduct(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
