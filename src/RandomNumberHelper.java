import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomNumberHelper {

    public static int randomIntNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static BigDecimal randomBigDecimal(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

}
