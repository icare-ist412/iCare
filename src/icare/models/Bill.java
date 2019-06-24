package icare.models;
import java.time.LocalDate;
/**
 *
 * @author Georgy
 */
public class Bill {
    private int billID;
    private double amount;
    private LocalDate billPostedDate;
    private String patientID;
    
    /**
     * Default constructor for this class. 
     */
    public void Bill(){
    }
    
    /**
     * @param postedAmount sets the amount of the Bill
     * @param patientID sets the ID of the patient on the Bill
     */
    public void postBillToPatient(String patientID, double postedAmount){
        this.amount = postedAmount;
        this.patientID = patientID;
        this.setBillPostedDate(LocalDate.now());
    } 
    
   /**
     * Gets the amount of current bill
     * @return A double representing the amount of the bill
     */
    public double getBillAmount()
    {
           return this.amount;
    }
    
    /**
     * @param newAmount changes amount posted on the bill
     */
    public void setBillAmount(double newAmount)
    {
        this.amount = newAmount;
    }
    
    /**
     * Gets the patientID from the bill
     * @return A String representing patientID
     */
    public String getBillPatientID()
    {
           return this.patientID;
    }
    
    /**
     * @param patientID changes patient ID  posted on the bill
     */
    public void setBillpatientID(String patientID)
    {
        this.patientID = patientID;
    }
    
    /**
     * Gets the billID from the bill
     * @return An int representing bill ID
     */
    public int getBillID()
    {
        return this.billID;
    }
    
    /**
     * Sets the billID from the bill
     */
    public void setBillID(int billID){
        this.billID = billID;
        
    }

    /**
     * @return the billPostedDate
     */
    public LocalDate getBillPostedDate() {
        return billPostedDate;
    }

    /**
     * @param billPostedDate the billPostedDate to set
     */
    public void setBillPostedDate(LocalDate billPostedDate) {
        this.billPostedDate = billPostedDate;
    }
}
