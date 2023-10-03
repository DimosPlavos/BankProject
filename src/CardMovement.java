public class CardMovement{
	private String cardCode;
	private double transactionValue;
	private String explanation;
	

	public CardMovement(String cardCode, double transactionValue,String explanation){
		this.transactionValue = transactionValue;
        this.cardCode = cardCode;
		this.explanation = explanation;
    }

	public String getExplanation() { 
		return explanation; 
	}
    public double getTransactionValue() { 
		return transactionValue; 
	}
    public String getCardCode() { 
		return cardCode; 
	}

	public static void setannual_amount(double transv, CreditCard card){
        card.set_annual_amount(transv);
     }
	
	public String toString(){
        return " Card Code: "+getCardCode()+" Transaction Value: "+String.format("%.2f",getTransactionValue())+" Explanation: "+getExplanation();
    }
}
