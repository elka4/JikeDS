package java2novice.dp.factory;
 
public class CurrencyFactory {
 
    public static Currency getCurrencyByCountry(String cnty) throws Exception{
         
        if("IN".equalsIgnoreCase(cnty)){
            return new India();
        } else if("USA".equalsIgnoreCase(cnty)){
            return new USA();
        }
        throw new Exception("Invalid country...");
    }
     
     
    public static void main(String a[]){
        Currency india;
        try {
            india = CurrencyFactory.getCurrencyByCountry("IN");
            System.out.println("Indian currency: "+india.getCurrency());
            System.out.println("Indian currency symbol: "+india.getSymbol());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
