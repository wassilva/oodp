import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("select your company (a, b, c)");
        String option = scanner.nextLine();
        option = option.toLowerCase();

        validateInput(option);

        Trade companyDepotTrade = TradeFactory.getTrade(option);
        printCompaniesGeneralView(companyDepotTrade);

        companyDepotTrade.execute();

        printTransactions();
        printCompaniesGeneralView(companyDepotTrade);
        Output.generateFile();
    }

    private static void printTransactions() {
        Map<String, List<Transaction>> transactions = TransactionManagerSingleton.getInstance().getTransactions();
        Output.write(System.lineSeparator());
        Output.write("*****************************");
        Output.write("*** Transactions overview ***");
        Output.write("*****************************");

        transactions.forEach((key,value) -> {
            value.forEach(t -> {
                Output.write(t.getSellerDepot() + " selling " + t.getSelledProduct() + " to " + t.getClientDepot());
                Output.write(t.getSellerDepot() + " +$" + t.getReceivedMoney());
                Output.write(t.getClientDepot() + " -$" + t.getReceivedMoney());

                if(t.getTradeProduct() != null){
                    Output.write("Executing second part of transaction, trading products");
                    Output.write(t.getClientDepot() + " trading " + t.getTradeProduct() + " to " + t.getSellerDepot());
                    Output.write(t.getSellerDepot() + " -$" + t.getSpentMoney());
                    Output.write(t.getClientDepot() + " +$" + t.getSpentMoney());
                }
            });
        });


    }

//    Valida se a empresa escolhida via console está de acordo com o esperado
    private static void validateInput(String option) {
        if (!option.equals("a") && !option.equals("b") && !option.equals("c")){
            throw new ValidationException("Invalid company");
        }
    }

    private static void printCompaniesGeneralView(Trade trade){
        CompanyDepotTrade companyDepotTrade = (CompanyDepotTrade) trade;
        List<Company> clientsCompany = companyDepotTrade.getClientsCompany();
        List<Company> companies = clientsCompany.stream().map(c -> c).collect(Collectors.toList());
        companies.add(companyDepotTrade.getTraderCompany());


        companies.forEach( company -> {
            Output.write("Company: " + company.getName());
            Output.write(" Depots: ");
            company.getDepots().forEach(depot -> {
                Output.write(" -> Depot: " + depot.getName());
                Output.write("    Cash Allowance: $" + depot.getCashAllowance());
                Output.write("    Product Price: $" + depot.getProductPrice());
                Output.write("    Delivery Product Price: $" + depot.getDeliveryProductPrice());
                Output.write("    Quantity Native Products: " + depot.getNativeProducts().size());
                Output.write("    Quantity External Products: " + depot.getExternalProducts().size());
            });
        });

    }


}
