public class SalesProduct{

	private String sellerCode;
	private String bankProductCode;
	private String explanation;
	
	public SalesProduct(){}
	
	public SalesProduct(String sellerCode,String bankProductCode,String explanation){
        this.sellerCode = sellerCode;
		this.bankProductCode = bankProductCode;
		this.explanation = explanation;
	}

	public String getExplanation() { 
		return explanation; 
	}  
    public String getSellerCode() { 
		return sellerCode; 
	}
	public String getBankProductCode() { 
		return bankProductCode; 
	}
		
	public String toString(){
        return "Seller Code: "+getSellerCode()+" Bank Product Code: "+getBankProductCode()+" Explanation: "+getExplanation();
    }
}
