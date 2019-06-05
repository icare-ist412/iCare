/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.models;

/**
 *
 * @author David Ortiz
 */
public class Patient extends User {
    private String firstName;
    private String lastName;
    private Credential credential;

    /**
     *
     * @param firstName
     * @param lastName
     * @param credential
     */
    public Patient(String firstName, String lastName) {
        super(firstName, lastName);
    }


}
