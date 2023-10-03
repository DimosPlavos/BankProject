// Name: Dimosthenis Plavos     AM:3200156
// Name: Panagiotis Nyktarakis  AM:3200124 


import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        //=========================================DHMIOURGW TA ZHTOUMENA ARRAYLISTS===========================================//

        //KANW READ TO TXT ARXEIO KAI FTIAXNW TA ARRAYLISTS
        
        ArrayList <BankingProduct> products = new ArrayList<>();
        products = TxtReader.LoadDataBankitem("file1.txt",products);
       
        ArrayList <Seller> sellers = new ArrayList<>();
        sellers = TxtReader.LoadDataSeller("file2.txt", sellers);

        ArrayList <SalesProduct> sales = new ArrayList<>();
        sales = TxtReader.LoadDataSales("file3.txt",sales );

        ArrayList <CardMovement> card_mov = new ArrayList<>();
        card_mov = TxtReader.LoadDatacard_mov("file4.txt",card_mov );  
       
        /*ArrayList <Seller> sellers = new ArrayList<>();
        sellers.add(new Seller("dimos","plavos","32475"));
        sellers.add(new Seller("panagiotis","nyktarakis","21864"));
        sellers.add(new Seller("kostas","georgiou","26910"));

         ArrayList <BankingProduct> products = new ArrayList<>();
        products.add(new Loan("69803972","23819",20000.0,0.32));
        products.add(new Loan("69823847","98256",37400.0,0.44));
        products.add(new Loan("69320492","10758",32500.0,0.17));
        products.add(new CreditCard("69098223","48649",0.56,2000.0 ,400000.0));
        products.add(new CreditCard("69696296","24839",0.38,3000.0 ,500000.0));
        products.add(new CreditCard("69210439","26792",0.66,5000.0 ,550000.0));
        

        ArrayList <SalesProduct> sales = new ArrayList<>();
        sales.add(new SalesProduct(sellers.get(0).getCode(),products.get(0).getCode(),"to buy a house"));
        sales.add(new SalesProduct(sellers.get(1).getCode(),products.get(1).getCode(),"to buy a car"));
        sales.add(new SalesProduct(sellers.get(2).getCode(),products.get(2).getCode(),"for a start-up"));
        sales.add(new SalesProduct(sellers.get(0).getCode(),products.get(3).getCode(),"to pay the bills"));
        sales.add(new SalesProduct(sellers.get(1).getCode(),products.get(4).getCode(),"for vacations"));
        sales.add(new SalesProduct(sellers.get(2).getCode(),products.get(5).getCode(),"for pay the house rent"));
            
        ArrayList <CardMovement> card_mov = new ArrayList<>();
        card_mov.add(new CardMovement(products.get(3).getCode(),25.0,"food in KFC"));
        card_mov.add(new CardMovement(products.get(3).getCode(),300.0,"hotel Grande Bretagne"));
        card_mov.add(new CardMovement(products.get(3).getCode(),12.0,"taxi"));
        card_mov.add(new CardMovement(products.get(3).getCode(),100.0,"night club"));
        card_mov.add(new CardMovement(products.get(4).getCode(),90.0,"food in reastaurant"));
        card_mov.add(new CardMovement(products.get(4).getCode(),18.0,"cocktail"));
        card_mov.add(new CardMovement(products.get(4).getCode(),170.0,"shopping"));
        card_mov.add(new CardMovement(products.get(4).getCode(),29.0,"pharmacy"));
        card_mov.add(new CardMovement(products.get(5).getCode(),50.0,"gym payment"));
        card_mov.add(new CardMovement(products.get(5).getCode(),460.0,"furniture shopping"));
        card_mov.add(new CardMovement(products.get(5).getCode(),21.5,"food delivery"));
        card_mov.add(new CardMovement(products.get(5).getCode(),6.0,"coffee"));
        
        */
        //=================SUGKEWNTRWNW TA AMOUNTS TWN MOVEMENTS KATHE PISTWTIKHS KARTAS====================//

        for (int i=0; i<products.size();i++){
            if (products.get(i) instanceof CreditCard){
                CreditCard pk = (CreditCard)products.get(i);
                for(CardMovement cm:card_mov){
                    if (cm.getCardCode().equals(pk.getCode())){
                        pk.set_annual_amount(cm.getTransactionValue());
                    }
                }
            }
            
        }

        //================================================================================================//
        //============================================= M E N U ==========================================//
        //================================================================================================//

        Scanner s = new Scanner(System.in);
        System.out.println("\nWelcome to Banking Application!\nThe functions are:");

        boolean isOver = false;
        while(!isOver) {
            System.out.println("================================ M E N U ================================");
            System.out.println("=========================================================================");
            System.out.println("1. Add new seller.");
            System.out.println("2. Add new banking product.");
            System.out.println("3. Add new sales product.");
            System.out.println("4. Add new card movement.");
            System.out.println("5. Show the loans.");
            System.out.println("6. Calculating a seller's commission.");
            System.out.println("7. Display credit card transactions related to the seller.");
            System.out.println("8. Calculation of the commission of all the sellers of the bank.");
            System.out.println("9. Display the final commission amount of all sellers.");
            System.out.println("10. Renew the files of Sales of Products and Card Movements.");
            System.out.println("0. EXIT.");
            System.out.println("=========================================================================\n");
            System.out.print("Choose an option: ");

            int answer = Integer.parseInt(s.nextLine());
            switch(answer){
                case 1:
                    System.out.print("Give a seller's first name: ");
                    String fn = s.nextLine();
                    System.out.print("Give a seller's last name: ");
                    String ln = s.nextLine();
                    System.out.print("Give seller's AFM : ");
                    String afm = s.nextLine();
                    sellers.add(new Seller(fn, ln, afm));

                    for (Seller t:sellers){
                        System.out.println(t);
                    }
					break;
				case 2:
                    System.out.println("Choose: \n1.Loan \n2.Credit Card ");
                    String choice = s.nextLine();
                    while(!choice.equals("1") && !choice.equals("2")){
                        System.out.print("Give either 1 or 2 as an answer! Type again: ");
                        choice = s.nextLine();
                    }
                    System.out.print("Give number: ");
                    String num = s.nextLine();
                    System.out.print("Give AFM client: ");
                    String AFM = s.nextLine();
                
                    if (choice.equals("1")){
                        System.out.print("Give amount: ");
                        String am = s.nextLine();
                        System.out.print("Give annual_rate: " );
                        String ar = s.nextLine();
                        products.add(new Loan(num, AFM, Double.parseDouble(am), Double.parseDouble(ar)));
                    }
                    else if (choice.equals("2")){
                        System.out.print("Give percentage: ");
                        String per = s.nextLine();
                        System.out.print("Give max movement: ");
                        String mm = s.nextLine();
                        System.out.print("Give max annual amount: ");
                        String ma = s.nextLine();
                        products.add(new CreditCard(num, AFM,Double.parseDouble(per),Double.parseDouble(mm),Double.parseDouble(ma)));
                    }

                    for (BankingProduct t:products){
                        System.out.println(t);
                    }
					break;
				case 3:
                    //-------------------vres ton pwlhth pou epilegei---------//
                    int j =1; 
                    Seller chosen_seller;
                    for (Seller r:sellers){
                        System.out.println(j++ +". "+r);
                    }
                    System.out.print("Choose a seller: ");
                    int k = Integer.parseInt(s.nextLine());
                    chosen_seller = sellers.get(k-1);
                    //-------------------vres to trapeziko proion pou epilegei---------//

                    /*H xrisi tou j ginetai gia praktikous logous, etsi wste an gia paradeigma o kwdikos toy xristi einai enas
                    me polla pshfia (px 18629023), na dinoume thn eykolia ston xristi na dialegei me arithmisi apo to 1 kai panw.
                    */

                    j=1; 
                    BankingProduct chosen_banking_product;
                    for (BankingProduct bp:products){
                        System.out.println(j++ +". "+bp);
                    }
                    System.out.print("Choose the banking product: ");
                    k = Integer.parseInt(s.nextLine());
                    chosen_banking_product = products.get(k-1);

                    //---------------------------explanation toy xrhsth----------------//
                    System.out.print("Give explanation: ");
                    String explanation = s.nextLine();

                    sales.add(new SalesProduct(chosen_seller.getCode(),chosen_banking_product.getCode(),explanation));
                    break;

				case 4:
                //------------------emfanizw tis pistotikes kartes---------------// 
                ArrayList<CreditCard> creditcards = new ArrayList<>(); //arraylist me ta credit cards mono
                j=1;
                for (BankingProduct pr : products){
                    if (pr instanceof CreditCard){
                        creditcards.add((CreditCard)pr);
                        System.out.println(j++ +". "+pr);
                    }
                }

                System.out.print("Choose the CreditCard :");
                String Cc = s.nextLine();
                CreditCard pistwtikh_karta = (CreditCard) creditcards.get(Integer.parseInt(Cc) -1);
                String kwdikos = pistwtikh_karta.getCode(); //kwdikos ths credit card
               
                System.out.print("Give Transaction Value: ");
                double TranV = Double.parseDouble(s.nextLine());

                boolean f = true;
                if (pistwtikh_karta.getAnnual_amount()+TranV > pistwtikh_karta.getMax_annual_amount()) f = false; // an kseperastei to megisto ethsio poso pistwshs, f=false

                while (TranV > pistwtikh_karta.getMax_movement() || f==false){
                    if (f==false) System.out.println("Kseperases to max annual amount! The transaction value should be <= "+String.format("%.2f",(pistwtikh_karta.getMax_annual_amount() - pistwtikh_karta.getAnnual_amount())));
                    else System.out.println("Lathos, theloume transaction value <= "+ pistwtikh_karta.getMax_movement());
                    System.out.print("Give again Transaction Value: ");
                    TranV = Double.parseDouble(s.nextLine());
                    if (pistwtikh_karta.getAnnual_amount()+TranV <= pistwtikh_karta.getMax_annual_amount()) f = true;
                }
                pistwtikh_karta.set_annual_amount(TranV); //prosthetw tin posothta tou transaction value gia th sygkekrimenh pistwtikh karta
                
                System.out.print("Give Explanation: ");
                String Expl = s.nextLine();

                card_mov.add(new CardMovement(kwdikos, TranV,Expl));
                break;
				case 5:
					System.out.println(" The loans are:");
					for(BankingProduct loans:products) 
						if (loans instanceof Loan){
							System.out.println(loans);
						}
                    
					break;
                case 6:
                    j =1; 
                    for (Seller r:sellers){
                        System.out.println(j++ +". "+r);
                    }
                    System.out.print("Choose a seller: ");
                    k = Integer.parseInt(s.nextLine());
                    chosen_seller = sellers.get(k-1);
                    Promitheia.calculate(chosen_seller, products, card_mov,sales,true);
                    break;
                case 7:
                    j =1; 
                    for (Seller r:sellers){
                        System.out.println(j++ +". "+r);
                    }
                    System.out.print("Choose a seller: ");
                    k = Integer.parseInt(s.nextLine());
                    chosen_seller = sellers.get(k-1);
                    for(int i=0;i<sales.size();i++){
                        if (chosen_seller.getCode().equals(sales.get(i).getSellerCode())) //gia ton epilegmeno pwlhth
                        {
                            String product_code = sales.get(i).getBankProductCode(); //krataw to product code
                            for (j=0;j<products.size();j++){                            //epanalhptika sth lista twn products, tsekarw an vrw ton parapanw code
                                if (product_code.equals(products.get(j).getCode())){
                                    if (products.get(j) instanceof CreditCard){
                                        for (int w=0;w<card_mov.size();w++){
                                            if (card_mov.get(w).getCardCode().equals(products.get(j).getCode())){
                                                System.out.println(card_mov.get(w));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 8:
                    double sum = Promitheia.promitheia_olwn(sellers, products, card_mov,sales);
                    System.out.println("Total promitheies: "+ String.format("%.2f",sum));  
                    break;
                case 9:
                    j =1; 
                    for (Seller r:sellers){
                        System.out.println(j++ +". "+r.toString_withoutAFM()+" Promitheia: " +String.format("%.2f",Promitheia.calculate(r, products, card_mov,sales, false)));
                    }
                    System.out.println("Promitheia olwn: "+String.format("%.2f",Promitheia.promitheia_olwn(sellers, products, card_mov,sales)));
                    break;

                case 10:
                    System.out.println("Saving Sales of Products...");
                    TxtWriter.WriteDataSalesPr("file3.txt", sales);
                    System.out.println("Saved!");

                    System.out.println("Saving Card Movements... ");
                    TxtWriter.WriteDataCardMovs("file4.txt", card_mov);
                    System.out.println("Saved!");
                    break;
                case 0:
                    isOver = true;
                    break;
            }
        }
    }
}
