//abstract klasi gia na min dhmioyrgountai antikeimena ths
public abstract class BankingProduct { //
    protected String code;
    private static int i;
    protected String number;
    protected String AFM;

    public BankingProduct(String number,String AFM){
        this.code = Integer.toString(++i);
        this.number = number;
        this.AFM = AFM;
    }

    public BankingProduct(String code,String number,String AFM){
        this.code = code;;
        this.number = number;
        this.AFM = AFM;
        i = Integer.parseInt(code); // etsi wste otan diavazei enan kwdiko px 1001 na ksekinaei thn paragwgh kwdikou >1001.
    }

    public String getCode() {
        return code;
    }
    
    public String getAFM() {
        return AFM;
    }
    public String getNumber() {
        return number;
    }
}

