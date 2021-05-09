import java.math.BigDecimal;

public class Transaction {

    private String sellerCompany;
    private String clientCompany;
    private String sellerDepot;
    private String clientDepot;
    private String selledProduct;
    private String tradeProduct;
    private BigDecimal receivedMoney;
    private BigDecimal spentMoney;

    public Transaction(String sellerCompany, String clientCompany, String selledProduct,
                       String tradeProduct, BigDecimal receivedMoney, BigDecimal spentMoney,
                        String sellerDepot, String clientDepot) {
        this.sellerDepot = sellerDepot;
        this.clientDepot = clientDepot;
        this.sellerCompany = sellerCompany;
        this.clientCompany = clientCompany;
        this.selledProduct = selledProduct;
        this.tradeProduct = tradeProduct;
        this.receivedMoney = receivedMoney;
        this.spentMoney = spentMoney;
    }

    public String getSellerCompany() {
        return sellerCompany;
    }

    public void setSellerCompany(String sellerCompany) {
        this.sellerCompany = sellerCompany;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany;
    }

    public String getSelledProduct() {
        return selledProduct;
    }

    public void setSelledProduct(String selledProduct) {
        this.selledProduct = selledProduct;
    }

    public String getTradeProduct() {
        return tradeProduct;
    }

    public void setTradeProduct(String tradeProduct) {
        this.tradeProduct = tradeProduct;
    }

    public BigDecimal getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(BigDecimal receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }

    public String getSellerDepot() {
        return sellerDepot;
    }

    public String getClientDepot() {
        return clientDepot;
    }
}
