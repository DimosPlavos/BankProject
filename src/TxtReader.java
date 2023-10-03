import java.io.*;
import java.util.*;

public class TxtReader {

    public static ArrayList <BankingProduct> LoadDataBankitem(String filename, ArrayList <BankingProduct> products){
        try 
        {
            BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
            String line = buff.readLine(); //diavazw kathe grammh ksexwrista

            if(line == null || !line.trim().equalsIgnoreCase("BANKITEM_LIST")){
                throw new Exception(" List not found!"); 
            }

            line = buff.readLine();
            if (line.trim().equals("{")){ 
                boolean out_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h ekswterikh agkylh
                line = buff.readLine();

                while (line !=null) //oso yparxei keimeno, oso dhladh den exei teleiwsei h anagnwsh toy file
                {
                    
                    if(line!= null && line.trim().equalsIgnoreCase("BANKITEM")){

                        line = buff.readLine();
                        if(line != null && line.trim().equals("{")){

                            boolean in_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h eswterikh agkylh
                            String type ="", descr="",numberS="",afmS="",codeS="";
                            double code=0,amount = 0,afm=0,number=0,annualrate=0,perc=0,maxmovement=0,maxannualamount=0;
                            //tis metavlhtes afm, number tis xrhsimopoiw mono ston elegxo egkyrothtas gia to an einai arithmitikes times.
                            
                            //To \\s+ xrhsimopoieitai sto split etsi wste kata to split na agnoountai pollapla kena kai o xarakthras \t
                            while ( !in_agkylh && line!=null && !line.trim().equalsIgnoreCase("BANKITEM")){
                                line = buff.readLine().trim(); 
                                if(line.split("\\s+")[0].equalsIgnoreCase("Type")){
                                    type = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Code")){
                                    try {
                                        code = Double.parseDouble(line.split("\\s+")[1]); 
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    codeS = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Descr")){
                                    //prokeimenou na apofugw ta " " 
                                    String part1 = line.split("\\s+")[1]; 
                                    String part2 = line.split("\\s+")[2];
                                    descr = part1.substring(1) + " "+ part2.substring(0,part2.length()-1);
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("AFM")){
                                    try {
                                        afm = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    afmS = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Number")){
                                    try {
                                        number = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    numberS = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Amount")){
                                    try {
                                        amount = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("AnnualRate")){
                                    try {
                                        annualrate = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Percentage")){
                                    try {
                                        perc = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("MaxMovement")){
                                    try {
                                        maxmovement = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("MaxAnnualAmount")){
                                    try {
                                        maxannualamount = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if (line.trim().equals("}")){
                                    in_agkylh = true;
                                }else if (line.isEmpty()) continue; //gia tis kenes grammes
                                else continue;
                            }

                            if (!in_agkylh) throw new Exception("Wrong! Den ekleise h eswterikh agkylh!");
                            if (code==0 || type=="" || descr==""){
                                System.out.println("Error, den yparxoun ta aparaithta stoixeia!");
                            }else{
                                if (type.equalsIgnoreCase("Loan")){
                                    products.add(new Loan(codeS,numberS,afmS,amount,annualrate));
                                }else if(type.equalsIgnoreCase("Card")){
                                    products.add(new CreditCard(codeS,numberS,afmS,perc,maxmovement,maxannualamount)); 
                                }else {
                                    throw new Exception("Wrong Type of Banking Product!");
                                }
                            }
                        }else {
                            throw new Exception("Error! Needed: { ");
                        }
                    }
                    line = buff.readLine();
                    if (line != null && line.trim().equals("}")) out_agkylh=true;
                }
                if (!out_agkylh) throw new Exception("Wrong! Den ekleise h ekswterikh agkylh!");
            }else {
                throw new Exception("Error! Needed: { ");
            }
            buff.close();
                
        }catch (Exception e) {
            System.out.println(e);
        }
        return products;
    }

    public static ArrayList <Seller> LoadDataSeller(String filename, ArrayList <Seller> sellers){
        try {
            
            BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
            String line = buff.readLine(); //diavazw kathe grammh ksexwrista

            if(line == null || !line.trim().equalsIgnoreCase("SALESMAN_LIST")){
                throw new Exception(" List not found!");
            }
            line = buff.readLine();
            if (line.trim().equals("{")){
                boolean out_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h ekswterikh agkylh
                line = buff.readLine();

                while (line !=null) //oso yparxei keimeno, oso dhladh den exei teleiwsei h anagnwsh toy file
                {
                    if(line!= null && line.trim().equalsIgnoreCase("SALESMAN")){
                        line = buff.readLine();

                        if(line != null && line.trim().equals("{")){
                            boolean in_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h eswterikh agkylh
                            String firstname ="", codeS ="0", lastname="",afmS="0";
                            double code=0,afm=0;    //gia na elegxo an einai arithmhmtikh timh

                            while (!in_agkylh && line !=null && !line.trim().equalsIgnoreCase("SALESMAN") ){ 
                                line = buff.readLine().trim();
                                if(line.split("\\s+")[0].equalsIgnoreCase("Code")){
                                    try {
                                        code = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    codeS = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Surname")){
                                    String name = line.split("\\s+")[1];
                                    lastname = name.substring(1,name.length()-1);
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("Firstname")){
                                    String name = line.split("\\s+")[1];
                                    firstname = name.substring(1,name.length()-1);
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("AFM")){
                                    try {
                                        afm = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    afmS = line.split("\\s+")[1];
                                }else if (line.trim().equals("}")){
                                    in_agkylh = true;
                                }else if (line.isEmpty()) continue; //gia tis kenes grammes
                                else continue;
                            }
                            if (!in_agkylh) throw new Exception("Wrong! Den ekleise h eswterikh agkylh");
                            if (code==0 || firstname.equals("") || lastname.equals("")){
                                System.out.println("Error, den yparxoun ta aparaithta stoixeia!");
                            }else{
                                sellers.add(new Seller(codeS, firstname, lastname, afmS));
                            }
                        }else {
                            throw new Exception("Error! Needed: { ");
                        }
                    }
                    line = buff.readLine();
                    if (line != null && line.trim().equals("}")) out_agkylh = true;
                }
                if (!out_agkylh) throw new Exception("Wrong! Den ekleise h ekswterikh agkylh");
            }else {
                throw new Exception("Error! Needed: { ");
            }
            buff.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sellers;
    }

    
    public static ArrayList <SalesProduct> LoadDataSales(String filename, ArrayList <SalesProduct> sales){
        try  
        {
            BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
            String line = buff.readLine(); //diavazw kathe grammh ksexwrista

            if(line == null || !line.trim().equalsIgnoreCase("SALES_LIST")){
                throw new Exception(" List not found!");
            }
            line = buff.readLine();
            if (line.trim().equals("{")){
                boolean out_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h ekswterikh agkylh
                line = buff.readLine();

                while (line !=null) //oso yparxei keimeno, oso dhladh den exei teleiwsei h anagnwsh toy file
                {
                    if(line!= null && line.trim().equalsIgnoreCase("SALES")){
                        line = buff.readLine();
                        if(line != null && line.trim().equals("{")){
                            boolean in_agkylh = false;  //th xrhsimopoiw gia na elegksw an kleisei h eswterikh agkylh
                            double Scode=0, Bcode=0;    //gia na elegxo an einai arithmhmtikh timh
                            String SCODE ="", BTYPE ="", BCODE="",expl="";
                            while (!in_agkylh && line!=null && !line.trim().equalsIgnoreCase("SALES")){
                                
                                line = buff.readLine().trim();
                                if(line.split("\\s+")[0].equalsIgnoreCase("SALESMAN_CODE")){
                                    try {
                                        Scode = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    SCODE = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("BANKITEM_TYPE")){
                                    BTYPE = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("BANKITEM_CODE")){
                                    try {
                                        Bcode = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    BCODE = line.split("\\s+")[1];
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("EXPLANATION")){
                                    
                                    String e = line.split(" \"")[1];
                                    expl = e.substring(0,e.length()-1);
                                    
                                }else if (line.trim().equals("}")){
                                    in_agkylh = true;
                                }else if (line.isEmpty()) continue;
                                else continue;
                            }
                            if (!in_agkylh) throw new Exception("Wrong! Den ekleise h eswterikh agkylh");
                            if (Scode==0  || Bcode==0){
                                System.out.println("Error, den yparxoun ta aparaithta stoixeia!");
                            }else{
                                    sales.add(new SalesProduct(SCODE,BCODE,expl));
                            }
                        }else {
                            throw new Exception("Error! Needed: { ");
                        }
                    }
                    line = buff.readLine();
                    if (line != null && line.trim().equals("}")) out_agkylh = true;
                }
                if (!out_agkylh) throw new Exception("Wrong! Den ekleise h ekswterikh agkylh");
            }else {
                throw new Exception("Error! Needed: { ");
            } 
            buff.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sales;
    }


    public static  ArrayList <CardMovement> LoadDatacard_mov(String filename, ArrayList <CardMovement> card_mov){
        try 
        {
            BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
            String line = buff.readLine(); //diavazw kathe grammh ksexwrista

            if(line == null || !line.trim().equalsIgnoreCase("TRN_LIST")){
                throw new Exception(" List not found!");
            }
            line = buff.readLine();
            if (line.trim().equals("{")){
                boolean out_agkylh = false; //th xrhsimopoiw gia na elegksw an kleisei h ekswterikh agkylh
                line = buff.readLine();

                while (line !=null) //oso yparxei keimeno, oso dhladh den exei teleiwsei h anagnwsh toy file
                {    
                    if(line!= null && line.trim().equalsIgnoreCase("TRN")){
                        line = buff.readLine();

                        if(line != null && line.trim().equals("{")){
                            boolean in_agkylh = false;  //th xrhsimopoiw gia na elegksw an kleisei h eswterikh agkylh
                            double VAL=0 ,Bcode=0;      //gia na elegxo an einai arithmhmtikh timh
                            String  JUSTIFICATION ="", BCODE="";

                            while (!in_agkylh && line!=null && !line.trim().equalsIgnoreCase("TRN")){
                                line = buff.readLine().trim();
                                if(line.split("\\s+")[0].equalsIgnoreCase("VAL")){
                                    try {
                                        VAL = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("JUSTIFICATION")){
                                    String e = line.split(" \"")[1];
                                    JUSTIFICATION = e.substring(0,e.length()-1);
                                }else if(line.split("\\s+")[0].equalsIgnoreCase("BANKITEM_CODE")){
                                    try {
                                        Bcode = Double.parseDouble(line.split("\\s+")[1]);
                                    } catch (NumberFormatException nfe) {
                                        throw new Exception("Wrong! We wanted an arithmetic value!");
                                    }
                                    BCODE = line.split("\\s+")[1];
                                }else if (line.trim().equals("}")){
                                    in_agkylh = true;
                                }else if (line.isEmpty()) continue;
                                else continue;
                            }
                            if (!in_agkylh) throw new Exception("Wrong! Den ekleise h eswterikh agkylh");
                            if (VAL==0 || JUSTIFICATION.equals("") || Bcode==0){
                                System.out.println("Error, den yparxoun ta aparaithta stoixeia!");
                            }else{
                                card_mov.add(new CardMovement(BCODE,VAL,JUSTIFICATION));
                            }

                        }else {
                            throw new Exception("Error! Needed: { ");
                        }
                    }
                    line = buff.readLine();
                    if (line != null && line.trim().equals("}")) out_agkylh = true;
                }
                if (!out_agkylh) throw new Exception("Wrong! Den ekleise h ekswterikh agkylh");
                
            }else {
                throw new Exception("Error! Needed: { ");
            } buff.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return card_mov;
    }
    
}