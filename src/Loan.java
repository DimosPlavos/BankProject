public class Loan extends BankingProduct {
    private double amount;
    private double annual_rate;
    

    public Loan(String number,String AFM,double amount,double annual_rate){
        super(number,AFM);
        this.amount = amount;
        this.annual_rate = annual_rate;
    }
    public Loan(String code,String number,String AFM,double amount,double annual_rate){
        super(code,number,AFM);
        this.amount = amount;
        this.annual_rate = annual_rate;
    }

    public double getAmount() {
        return amount;
    }
    public double getAnnual_rate() {
        return annual_rate;
    }

    @Override  
    public String getCode() {
        return super.getCode();
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }
	public String toString(){
        return "Code: "+ super.getCode()+" , Number: "+super.getNumber()+" , Amount: "+String.format("%.2f",getAmount())+" , Annual rate : " +String.format("%.2f",getAnnual_rate()*100)+"%";
    }
}
