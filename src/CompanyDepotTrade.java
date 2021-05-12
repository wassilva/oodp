import java.util.List;

//Classe que operacionaliza as transações entre os depósitos
public class CompanyDepotTrade extends Trade {

    private Company traderCompany;
    private List<Company> clientsCompany;
    private List<Transaction> transactions;

    public CompanyDepotTrade(Company traderCompany, List<Company> clientsCompany) {
        this.traderCompany = traderCompany;
        this.clientsCompany = clientsCompany;
    }

    public void execute(){
//        Percorremos por todos os depositos da empresa escolhida via console,
//        para cada deposito precisamos tentar com todos os outros depositos das outras empresas.
        for (Depot depotTraderCompany : traderCompany.getDepots()) {
            try {
                //Para cada empresa vamos percorrer por todos depositos dela e tentar fazer a venda (linha 24)
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