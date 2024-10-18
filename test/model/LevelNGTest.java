/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package model;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author DELL-PC
 */
public class LevelNGTest {
    
    public LevelNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getLevel_id method, of class Level.
     */
    @Test
    public void testGetLevel_id() {
        System.out.println("getLevel_id");
        Level instance = new Level(4, "hai");
        int expResult = 4;
        int result = instance.getLevel_id();
        assertEquals(result, expResult);
    }

    /**
     * Test of setLevel_id method, of class Level.
     */
    @Test
    public void testSetLevel_id() {
        System.out.println("setLevel_id");
        int level_id = 3;
        Level instance = new Level();
        instance.setLevel_id(level_id);
        //assertEquals(instance.getLevel_id(), level_id);
    }
    
    @Test(expectedExceptions = Exception.class)
    public void testSetLevel_idNegative() {
        System.out.println("setLevel_id_negative");
        int level_id = -10;
        Level instance = new Level();
        instance.setLevel_id(level_id);
    }

    /**
     * Test of getLevel_name method, of class Level.
     */
    @Test
    public void testGetLevel_name() {
        System.out.println("getLevel_name");
        Level instance = new Level();
        String expResult = "";
        String result = instance.getLevel_name();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLevel_name method, of class Level.
     */
    @Test
    public void testSetLevel_name() {
        System.out.println("setLevel_name");
        String level_name = "";
        Level instance = new Level();
        instance.setLevel_name(level_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Level.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Level instance = new Level();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
