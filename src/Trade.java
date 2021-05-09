import java.util.List;

public class Trade {

    private Company traderCompany;
    private List<Company> clientsCompany;
    private List<Transaction> transactions;

    public Trade(Company traderCompany, List<Company> clientsCompany) {
        this.traderCompany = traderCompany;
        this.clientsCompany = clientsCompany;
    }

    public void execute(){

        for (Depot depotTraderCompany : traderCompany.getDepots()) {
            try {

                for(Company company : clientsCompany){
                    for (Depot depotClientCompany: company.getDepots()) {
                        try {
                            depotTraderCompany.sellNativeProductTo(depotClientCompany);
                        }catch (SelfDepotTransactionNotAllowedException ex){
                            throw ex;
                        }catch (TradeProductNotAllowedException e){
                            continue;
                        }catch (TransactionNotAllowed e){
                            continue;
                        }
                    }
                }
            }catch (SelfDepotTransactionNotAllowedException e){
                continue;
            }
        }
    }

    public Company getTraderCompany() {
        return traderCompany;
    }

    public List<Company> getClientsCompany() {
        return clientsCompany;
    }
}