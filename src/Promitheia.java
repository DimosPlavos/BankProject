import java.util.ArrayList;

public class Promitheia {

    //ypologizei tin promitheia tou pwlhth
    public static double calculate(Seller pwlhths, ArrayList <BankingProduct> products, ArrayList <CardMovement> card_mov, ArrayList <SalesProduct> sales, boolean flag){
        double total_promitheia;
        double sumdaneia =0;
        double promitheia_daneiwn=0;
        double sumtokoi = 0;
        double pososto;
        double promitheiakartwn=0;
        double sumkarta=0;
        for(int i=0;i<sales.size();i++){
              if (pwlhths.getCode().equals(sales.get(i).getSellerCode())) //an vrw ton kwdiko toy pwlhth pou zhtaw sth lista twn pwlhsewn proiontwn
              {
                    String product_code = sales.get(i).getBankProductCode(); //krataw ton kwdiko tou proiontos pou pwleitai, estw A
                    for (int j=0;j<products.size();j++){                     //epaalhptika sth lista twn trapezikwn proiontwn psaxnw  
                        if (product_code.equals(products.get(j).getCode())){ //an tautizetai o kwdikos toy proiontos A me kapoio proion ths listas products
                
                            if (products.get(j) instanceof Loan)             //tsekarw an einai daneio
                            {
                                sumdaneia += ((Loan) products.get(j)).getAmount();  //sunolo twn posothtwn twn daneiwn
                                sumtokoi += (((Loan) products.get(j)).getAnnual_rate()) * ((Loan) products.get(j)).getAmount(); //tokos = epitokio * posothta daneiou
                            }
                            else if (products.get(j) instanceof CreditCard){  //tsekarw an einai pistwtikh karta
                                for (int w=0;w<card_mov.size();w++){
                                    if (card_mov.get(w).getCardCode().equals(products.get(j).getCode())){
                                        sumkarta += card_mov.get(w).getTransactionValue();   //h sumkarta exei to synolo twn transaction values ths kartas 
                                    }
                                }
                                promitheiakartwn += sumkarta * ((CreditCard) products.get(j)).getPercentage_ComtoMov();
                            }
                        }
                    }
                }
        }
        
                         
        if (sumdaneia <=500000) pososto = 0.01;
        else if(sumdaneia<=2000000) pososto = 0.02;
        else pososto = 0.025;
        promitheia_daneiwn = pososto * sumdaneia;
       
        if (sumtokoi < promitheia_daneiwn) {
            promitheia_daneiwn = sumtokoi;  //symfwna me ypodeiksh thn ekfwnisis
        }
        
        total_promitheia = promitheia_daneiwn + promitheiakartwn;
        if (flag == true){        //xrhsimopoiw thn flag etsi wste na ektypwnontai mono sto case 6 ths main! 
            System.out.println("Promitheia daneiwn: "+String.format("%.2f",promitheia_daneiwn)+", Promitheia kartwn: "+String.format("%.2f",promitheiakartwn) + ", Synolikh Promitheia: "+ String.format("%.2f",total_promitheia));
            return 0;
        } else { return total_promitheia;}
        
    }
    //h promitheia olwn twn pwlhtwn
    public static double promitheia_olwn(ArrayList <Seller> sellers, ArrayList <BankingProduct> products, ArrayList <CardMovement> card_mov, ArrayList <SalesProduct> sales){
        double sum = 0;
        for(int i=0;i<sellers.size();i++){
            sum += Promitheia.calculate(sellers.get(i), products, card_mov,sales, false);
        }
        return sum;
    
    }
}
