public class CreditCard extends BankingProduct {
    private double percentage_ComtoMov;
    private double max_movement;
    private double max_annual_amount;
    private double annual_amount;

    public CreditCard(String number,String AFM,double percentage_ComtoMov, double max_movement, double max_annual_amount){
        super(number,AFM);
        this.percentage_ComtoMov = percentage_ComtoMov;
        this.max_movement = max_movement;
        this.max_annual_amount = max_annual_amount;
    }

    public CreditCard(String code,String number,String AFM,double percentage_ComtoMov, double max_movement, double max_annual_amount){
        super(code,number,AFM);
        this.percentage_ComtoMov = percentage_ComtoMov;
        this.max_movement = max_movement;
        this.max_annual_amount = max_annual_amount;
    }

    public double getPercentage_ComtoMov() {
        return percentage_ComtoMov;
    }
    public double getMax_annual_amount() {
        return max_annual_amount;
    }
    public double getMax_movement() {
        return max_movement;
    }


    public void set_annual_amount(double value){
        this.annual_amount += value;
    }
    public double getAnnual_amount() {
        return annual_amount;
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return super.getCode();
    }
	public String toString(){
        return "Code: "+ super.getCode()+" , Number: "+super.getNumber()+" , Max movement: "+String.format("%.2f",getMax_movement())+" , Max annual amount: "+String.format("%.2f",getMax_annual_amount())+" , Percentage: "+String.format("%.2f",getPercentage_ComtoMov()*100)+"%";
    }
}
