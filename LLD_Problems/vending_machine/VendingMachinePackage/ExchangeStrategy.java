package VendingMachinePackage;
import java.util.Map;

public interface ExchangeStrategy{
    boolean isPossible(CoinInventory cv, int amount);
    Map<Currency, Integer> exchange(CoinInventory cv, int amount);
}
