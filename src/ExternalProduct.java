
public class ExternalProduct implements Product{

    private String name;

    public ExternalProduct(String name) {
        this.name = name;
    }

    public ExternalProduct(Product externalProduct) {
        this.name = externalProduct.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}
