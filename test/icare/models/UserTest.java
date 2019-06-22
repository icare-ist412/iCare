/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icare.models;

import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Ortiz
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @org.junit.Test
    public void testGetRoleType() {
        System.out.println("getRoleType");
        User instance = null;
        String expResult = "";
        String result = instance.getRoleType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetCredential() {
        System.out.println("getCredential");
        User instance = null;
        Credential expResult = null;
        Credential result = instance.getCredential();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetBirthdate() {
        System.out.println("getBirthdate");
        User instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.getBirthdate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        User instance = null;
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetLastName() {
        System.out.println("getLastName");
        User instance = null;
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetFullName() {
        System.out.println("getFullName");
        User instance = null;
        String expResult = "";
        String result = instance.getFullName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testUpdateCredential() {
        System.out.println("updateCredential");
        String password = "";
        User instance = null;
        instance.updateCredential(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        String userID = "";
        String password = "";
        User instance = null;
        boolean expResult = false;
        boolean result = instance.authenticate(userID, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testGetUserID() {
        System.out.println("getUserID");
        User instance = null;
        String expResult = "";
        String result = instance.getUserID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        User instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
