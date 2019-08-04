package icare.models;

/**
 * The list of supported users' roles
 * @author Dmitry Mikhailov
 */
public enum RoleEnum {

    Unknown("unknown"),
    Patient("Patient"),
    Staff("Staff");

    private final String name;

    private RoleEnum(String s) {
        name = s;
    }

    public String toString() {
        return name;
    }

}
