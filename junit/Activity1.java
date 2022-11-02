package activities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {

    static ArrayList list;

    @BeforeEach
    public  void setUp(){
        list = new ArrayList<String>();
        list.add("alpha");
        list.add("beta");
    }
    @Test
    public void insertTest(){
        assertEquals(2,list.size(),"Wrong size");
        list.add("gamma");
        assertEquals(3,list.size(),"Wrong size");
        assertEquals("alpha",list.get(0),"Wrong element");
        assertEquals("beta",list.get(1),"Wrong element");
        assertEquals("gamma",list.get(2),"Wrong element");
    }
    @Test
    public void replaceTest(){
        System.out.println(list);
        assertEquals(2,list.size(),"Wrong size");
        list.add("Selenium");
        assertEquals(3,list.size(),"Wrong size");
        list.set(1,"Java");
        assertEquals("alpha",list.get(0),"Wrong element");
        assertEquals("Java",list.get(1),"Wrong element");
        assertEquals("Selenium",list.get(2),"Wrong element");

    }
}
