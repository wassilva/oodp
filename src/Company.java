import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Company {

    private final int MAX_DEPOTS_QUANTITY = 50;

    private final int MIN_NATIVE_PRODUCT_QUANTITY = 15;
    private final int MAX_NATIVE_PRODUCT_QUANTITY = 50;

    private final int MIN_EXTERNAL_PRODUCT_QUANTITY = 3;
    private final int MAX_EXTERNAL_PRODUCT_QUANTITY = 40;

    private String name;
    private List<Depot> depots;

    public Company(String name) {
        this.name = name;
        this.generateDepots();
    }

    private void generateDepots(){
        this.depots = new ArrayList<Depot>();
        for(int i=0; i<MAX_DEPOTS_QUANTITY; i++){
            int index = i;

            String depotName = "Depot " + i;

            AtomicReference<Integer> indexExternalProduct = new AtomicReference<>(-1);
            AtomicReference<Integer> indexNativeProduct = new AtomicReference<>(-1);

            List<ExternalProduct> externalProducts = Stream.generate(() -> {
                        indexExternalProduct.getAndSet(indexExternalProduct.get() + 1);
                        return new ExternalProduct("Product " + indexExternalProduct.toString() + " of depot " + depotName + " of company " + name);
                    })
                    .limit(RandomNumberHelper.randomIntNumber(MIN_EXTERNAL_PRODUCT_QUANTITY, MAX_EXTERNAL_PRODUCT_QUANTITY))
                    .collect(Collectors.toList());

            List<NativeProduct> nativeProducts = Stream.generate(() -> {
                        indexNativeProduct.getAndSet(indexNativeProduct.get() + 1);
                        return new NativeProduct("Product " + indexNativeProduct.toString() + " of depot " + depotName + " of company " + name);
                    })
                    .limit(RandomNumberHelper.randomIntNumber(MIN_NATIVE_PRODUCT_QUANTITY, MAX_NATIVE_PRODUCT_QUANTITY))
                    .collect(Collectors.toList());

            this.depots.add(new Depot(depotName + " of Company " + this.name, nativeProducts, externalProducts, this));
        }
    }

    public String getName() {
        return name;
    }

    public List<Depot> getDepots() {
        return depots;
    }
}
