import java.util.Arrays;

public class TradeFactory {


    public static Trade getTrade(String input){
        Company companyA = new Company("a");
        Company companyB = new Company("b");
        Company companyC = new Company("c");

        if(input.equals("a")){
            return new Trade(companyA, Arrays.asList(companyB, companyC));
        }else if (input.equals("b")){
            return new Trade(companyB, Arrays.asList(companyA, companyC));
        }else {
            return new Trade(companyC, Arrays.asList(companyA, companyB));
        }
    }

}
