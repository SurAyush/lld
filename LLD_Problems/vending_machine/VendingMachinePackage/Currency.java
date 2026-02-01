package VendingMachinePackage;
public enum Currency{
    ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100), TWO_HUNDRED(200), FIVE_HUNDRED(500) ;
    
    private int val;

    Currency(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}