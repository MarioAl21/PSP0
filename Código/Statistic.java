import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Statistic
{
  public static String toRondDecimals(double number)
  {
    return new DecimalFormat("###.##").format(number);
  }  
} // Class close



