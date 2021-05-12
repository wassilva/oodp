import java.util.*;

//Classe que representa um controlador de transação.
//Foi utilizado o padrão singleton pois precisavamos ter um objeto único que controle todas as transações durante o funcionamento do programa.
public class TransactionManagerSingleton {

    private static TransactionManagerSingleton instance;

    private Map<String, List<Transaction>> transactions = new HashMap<String, List<Transaction>>();


    //Construtor privado para que não seja possível instancias o objeto a não ser chamando o getInstance()
    private TransactionManagerSingleton(){}

    //Método estatico que garante que apenas uma instancia existira durante o funcionamento do programa.
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
