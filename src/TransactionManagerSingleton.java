import java.util.*;

public class TransactionManagerSingleton {

    private static TransactionManagerSingleton instance;

    private Map<String, List<Transaction>> transactions = new HashMap<String, List<Transaction>>();


    private TransactionManagerSingleton(){}

    public static TransactionManagerSingleton getInstance(){
        if(instance == null){
            instance = new TransactionManagerSingleton();
        }
        return instance;
    }


    public void addTransaction(Transaction transaction){
        String sellerCompany = transaction.getSellerCompany();
        if(this.transactions.get(sellerCompany) == null){
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);
            this.transactions.put(sellerCompany, transactions);
        }else {
            List<Transaction> transactions = this.transactions.get(sellerCompany);
            transactions.add(transaction);
            this.transactions.put(sellerCompany, transactions);
        }
    }

    public Map<String, List<Transaction>> getTransactions(){
        return this.transactions;
    }

}
