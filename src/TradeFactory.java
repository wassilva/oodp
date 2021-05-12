import java.util.Arrays;

/*
Classe do pattern Factory, utilizado para manter em uma classe única
a complexidade na forma em que criamos os objetos, de acordo com a
entrada fornecida via console.
 */
public class TradeFactory {

    public static Trade getTrade(String input){
        Company companyA = new Company("a");
        Company companyB = new Company("b");
        Company companyC = new Company("c");

        if(input.equals("a")){
            return new CompanyDepotTrade(companyA, Arrays.asList(companyB, companyC));
        }else if (input.equals("b")){
            return new CompanyDepotTrade(companyB, Arrays.asList(companyA, companyC));
        }else {
            return new CompanyDepotTrade(companyC, Arrays.asList(companyA, companyB));
        }
    }

}
