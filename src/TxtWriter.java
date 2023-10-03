import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class TxtWriter {
    
    public static void WriteDataSalesPr(String filename, ArrayList <SalesProduct> sales){ 
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter(new File(filename)));
            buff.write("SALES_LIST\n{\n");
            for(int j=0; j<sales.size(); j++){
                buff.write("\tSALES\n\t{\n");
                buff.write("\t\tSALESMAN_CODE "+ sales.get(j).getSellerCode()+"\n");
                buff.write("\t\tEXPLANATION \""+ sales.get(j).getExplanation()+"\"\n");
                buff.write("\t\tBANKITEM_CODE "+ sales.get(j).getBankProductCode()+"\n");
                buff.write("\t}\n\n");
            }
            buff.write("}");
            buff.close();
        }
        catch (Exception e){
           System.out.println(e);
        }
    }

    public static void WriteDataCardMovs(String filename, ArrayList <CardMovement> card_mov){
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter(new File(filename)));
            buff.write("TRN_LIST\n{\n");
            for(int j=0; j<card_mov.size(); j++){
                buff.write("\tTRN\n\t{\n");
                buff.write("\t\tBANKITEM_CODE "+ card_mov.get(j).getCardCode()+"\n");
                buff.write("\t\tJUSTIFICATION \""+ card_mov.get(j).getExplanation()+"\"\n");
                buff.write("\t\tVAL "+ card_mov.get(j).getTransactionValue()+"\n");
                buff.write("\t}\n\n");
            }
            buff.write("}");
            buff.close();
        }
        catch (Exception e){
           System.out.println(e);
        }
    }

}
