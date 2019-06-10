package icare.models;

/**
 *
 * @author Georgy
 */
public class Insurance {
    
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyPhone;
    private String PolicyNumber;
    private int Deductible;
        
    /**
     * Default constructor for this class. 
     */
    public void Insurance(){
    }

    /**
     * @return the CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * @param CompanyName the CompanyName to set
     */
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    /**
     * @return the CompanyAddress
     */
    public String getCompanyAddress() {
        return CompanyAddress;
    }

    /**
     * @param CompanyAddress the CompanyAddress to set
     */
    public void setCompanyAddress(String CompanyAddress) {
        this.CompanyAddress = CompanyAddress;
    }

    /**
     * @return the CompanyPhone
     */
    public String getCompanyPhone() {
        return CompanyPhone;
    }

    /**
     * @param CompanyPhone the CompanyPhone to set
     */
    public void setCompanyPhone(String CompanyPhone) {
        this.CompanyPhone = CompanyPhone;
    }

    /**
     * @return the PolicyNumber
     */
    public String getPolicyNumber() {
        return PolicyNumber;
    }

    /**
     * @param PolicyNumber the PolicyNumber to set
     */
    public void setPolicyNumber(String PolicyNumber) {
        this.PolicyNumber = PolicyNumber;
    }

    /**
     * @return the Deductible
     */
    public int getDeductible() {
        return Deductible;
    }

    /**
     * @param Deductible the Deductible to set
     */
    public void setDeductible(int Deductible) {
        this.Deductible = Deductible;
    }
    
    
}
