import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Classe que representa um deposito com todas suas informações e comportamentos.
public class Depot {

    private String name;
    private List<NativeProduct> nativeProducts;
    private List<ExternalProduct> externalProducts;
    private BigDecimal cashAllowance;
    private BigDecimal productPrice;
    private BigDecimal deliveryProductPrice;
    private Company company;


    public Depot(String name, List<NativeProduct> nativeProducts, List<ExternalProduct> externalProducts, Company company) {
        validateProductQuantity(nativeProducts, externalProducts);
        this.name = name;
        this.nativeProducts = nativeProducts;
        this.externalProducts = externalProducts;
        this.cashAllowance = RandomNumberHelper.randomBigDecimal(BigDecimal.valueOf(50L), BigDecimal.valueOf(100L));
        this.productPrice = RandomNumberHelper.randomBigDecimal(BigDecimal.valueOf(1L), BigDecimal.valueOf(10L));
        this.deliveryProductPrice = RandomNumberHelper.randomBigDecimal(BigDecimal.valueOf(1L), BigDecimal.valueOf(10L));
        this.company = company;
    }

//    Método que valida os limites de estoque do depósito
    private void validateProductQuantity(List<NativeProduct> nativeProducts, List<ExternalProduct> externalProducts){
        if(nativeProducts.size() < 15 || nativeProducts.size() > 50){
            throw new ValidationException("Invalid quantity for native products.");
        }else if(externalProducts.size() < 3 || externalProducts.size() > 40 ){
            throw new ValidationException("Invalid quantity for external products.");
        }
    }

    // Método responsável por fazer a venda de um produto e receber o produto de volta.
    public void sellNativeProductTo(Depot depotClientCompany) {
        if(this.nativeProducts.size() <= 15) throw new SelfDepotTransactionNotAllowedException("Native product inventory has reached its limit: " + this.nativeProducts.size());

//        Aqui foi criado uma lista temporaria para que fosse possível manipular os objetos
//        de produtos dentro do laço. Caso usassemos a propria lista não poderiamos remover o produto
//        no momento em que ele foi vendido
        List<NativeProduct> tempListNativeProducts = new ArrayList<NativeProduct>();
        tempListNativeProducts.addAll(nativeProducts);

        tempListNativeProducts.forEach(np -> {
            if(nativeProducts.size() <= 15) throw new SelfDepotTransactionNotAllowedException("Native product inventory has reached its limit: " + this.nativeProducts.size());

            //pega o último produto
            final Product nativeProduct = nativeProducts.get(nativeProducts.size() - 1);

            //vende o produto para o outro depósito
            depotClientCompany.receiveExternalProduct(nativeProduct, this.productPrice);

            //recebe o dinheiro da venda
            this.cashAllowance = cashAllowance.add(productPrice);
            nativeProducts.remove(nativeProducts.size() - 1);

            //Cria transação
            Transaction transaction = new Transaction(this.company.getName(), depotClientCompany.getCompany().getName(),
                                                        np.getName(), null, this.productPrice, null,
                                                        this.name, depotClientCompany.getName());

            TransactionManagerSingleton transactionManager = TransactionManagerSingleton.getInstance();

//            Segunda parte da transação, valida se o deposito que fez a venda pode receber produtos externos
            if(externalProducts.size() < 40){
                //Pega o produto de troca do deposito
                final Product tradeProductAfterBuy = depotClientCompany.getTradeProductAfterBuy();
                this.receiveExternalProduct(tradeProductAfterBuy, depotClientCompany.getProductPrice());

                transaction.setTradeProduct(tradeProductAfterBuy.getName());
                transaction.setSpentMoney(depotClientCompany.getProductPrice());
            }else {
                transactionManager.addTransaction(transaction);
                throw new SelfDepotTransactionNotAllowedException("Second part of transaction failed, external products: " + externalProducts.size() + " units");
            }
            transactionManager.addTransaction(transaction);
        });

    }

    // Esse método é invocado após uma compra, ao comprar um produto de um outro depósito é necessário realizar a troca.
    private Product getTradeProductAfterBuy() {
        if(nativeProducts.size() <= 15){
            throw new TradeProductNotAllowedException("Second part of transaction failed, , depot " + name + " inventory has reached its limit of native products.");
        }
        final Product nativeProduct = this.nativeProducts.get(nativeProducts.size() - 1);
        this.nativeProducts.remove(nativeProducts.size() - 1);
        this.cashAllowance = cashAllowance.add(this.productPrice);

        return nativeProduct;
    }

    //Recebe um produto externo e o adiciona ao depósito
    public void receiveExternalProduct(Product externalProduct, BigDecimal price){
        if(externalProducts.size() >= 40) throw new TransactionNotAllowed("Not allowed add external product to : " + name + " external products inventory with " + this.externalProducts.size() + " units.");
        // deve tirar do cashallowance o valor do externalProduct
        this.cashAllowance = cashAllowance.subtract(price);

        this.externalProducts.add(new ExternalProduct(externalProduct));
    }

    public String getName() {
        return name;
    }

    public List<NativeProduct> getNativeProducts() {
        return nativeProducts;
    }

    public List<ExternalProduct> getExternalProducts() {
        return externalProducts;
    }

    public BigDecimal getCashAllowance() {
        return cashAllowance;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public BigDecimal getDeliveryProductPrice() {
        return deliveryProductPrice;
    }

    public Company getCompany() {
        return company;
    }
}
