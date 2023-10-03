
public class Seller {
    private String code;
    private String firstName;
    private String lastName;
    private String AFM;
    private static int i;


    public Seller(){}

    public Seller(String firstName, String lastName, String AFM){
        this.firstName = firstName;
        this.lastName = lastName;
        this.AFM = AFM;
        this.code = Integer.toString(++i);
    }

    public Seller(String code,String firstName, String lastName, String AFM){
        this.firstName = firstName;
        this.lastName = lastName;
        this.AFM = AFM;
        this.code = code;
        i = Integer.parseInt(code); // etsi wste otan diavazei enan kwdiko px 1001 na ksekinaei thn paragwgh kwdikou >1001.
    }

    public String getCode() {
        return code;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAFM() {
        return AFM;
    }
	public String toString(){
        return "Seller Code: "+getCode()+" , First Name: "+getFirstName()+" , LastName: "+getLastName()+" , AFM : "+getAFM();
    }
    public String toString_withoutAFM(){
        return "Seller Code: "+getCode()+" , First Name: "+getFirstName()+" , LastName: "+getLastName();
    }
}