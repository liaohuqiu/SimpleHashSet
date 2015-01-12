package in.srain.cube.set.hash.test;

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import in.srain.cube.set.hash.SimpleHashSet;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;


public class SimpleHashSetTest extends TestCase {

    SimpleHashSet hs;
    Object[] objArray;

    /**
     * @tests java.util.SimpleHashSet#SimpleHashSet()
     */
    public void test_Constructor() {
        // Test for method java.util.SimpleHashSet()
        SimpleHashSet hs2 = new SimpleHashSet();
        assertEquals("Created incorrect SimpleHashSet", 0, hs2.size());
    }

    /**
     * @tests java.util.SimpleHashSet#SimpleHashSet(int)
     */

    public void test_ConstructorI() {
        // Test for method java.util.SimpleHashSet(int)
        SimpleHashSet hs2 = new SimpleHashSet(5);
        assertEquals("Created incorrect SimpleHashSet", 0, hs2.size());
        try {
            new SimpleHashSet(-1);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Failed to throw IllegalArgumentException for capacity < 0");
    }

    /**
     * @tests java.util.SimpleHashSet#SimpleHashSet(int)
     */
    public void test_ConstructorIF() {
        // Test for method java.util.SimpleHashSet(int)
        SimpleHashSet hs2 = new SimpleHashSet(5);
        assertEquals("Created incorrect SimpleHashSet", 0, hs2.size());
    }

    /**
     * @tests java.util.SimpleHashSet#SimpleHashSet(java.util.Collection)
     */
    public void test_ConstructorLjava_util_Collection() {
        // Test for method java.util.SimpleHashSet(java.util.Collection)
        SimpleHashSet hs2 = new SimpleHashSet(Arrays.asList(objArray));
        for (int counter = 0; counter < objArray.length; counter++)
            assertTrue("SimpleHashSet does not contain correct elements", hs
                    .contains(objArray[counter]));
        assertTrue("SimpleHashSet created from collection incorrect size",
                hs2.size() == objArray.length);
        try {
            new SimpleHashSet(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            //expected
        }
    }

    /**
     * @tests java.util.SimpleHashSet#add(java.lang.Object)
     */
    public void test_addLjava_lang_Object() {
        // Test for method boolean java.util.SimpleHashSet.add(java.lang.Object)
        int size = hs.size();
        hs.add(new Integer(8));
        assertTrue("Added element already contained by set", hs.size() == size);
        hs.add(new Integer(-9));
        assertTrue("Failed to increment set size after add",
                hs.size() == size + 1);
        assertTrue("Failed to add element to set", hs.contains(new Integer(-9)));
    }

    /**
     * @tests java.util.SimpleHashSet#clear()
     */
    public void test_clear() {
        // Test for method void java.util.SimpleHashSet.clear()
        Set orgSet = (Set) hs.clone();
        hs.clear();
        Iterator i = orgSet.iterator();
        assertEquals("Returned non-zero size after clear", 0, hs.size());
        while (i.hasNext())
            assertTrue("Failed to clear set", !hs.contains(i.next()));
    }

    /**
     * @tests java.util.SimpleHashSet#clone()
     */
    public void test_clone() {
        // Test for method java.lang.Object java.util.SimpleHashSet.clone()
        SimpleHashSet hs2 = (SimpleHashSet) hs.clone();
        assertTrue("clone returned an equivalent SimpleHashSet", hs != hs2);
        assertTrue("clone did not return an equal SimpleHashSet", hs.equals(hs2));
    }

    /**
     * @tests java.util.SimpleHashSet#contains(java.lang.Object)
     */
    public void test_containsLjava_lang_Object() {
        // Test for method boolean java.util.SimpleHashSet.contains(java.lang.Object)
        assertTrue("Returned false for valid object", hs.contains(objArray[90]));
        assertTrue("Returned true for invalid Object", !hs.contains(new Object()));
        SimpleHashSet s = new SimpleHashSet();
        s.add(null);
        assertTrue("Cannot handle null", s.contains(null));
    }

    /**
     * @tests java.util.SimpleHashSet#isEmpty()
     */
    public void test_isEmpty() {
        // Test for method boolean java.util.SimpleHashSet.isEmpty()
        assertTrue("Empty set returned false", new SimpleHashSet().isEmpty());
        assertTrue("Non-empty set returned true", !hs.isEmpty());
    }

    /**
     * @tests java.util.SimpleHashSet#iterator()
     */
    public void test_iterator() {
        // Test for method java.util.Iterator java.util.SimpleHashSet.iterator()
        Iterator i = hs.iterator();
        int x = 0;
        while (i.hasNext()) {
            assertTrue("Failed to iterate over all elements", hs.contains(i
                    .next()));
            ++x;
        }
        assertTrue("Returned iteration of incorrect size", hs.size() == x);
        SimpleHashSet s = new SimpleHashSet();
        s.add(null);
        assertNull("Cannot handle null", s.iterator().next());
    }

    /**
     * @tests java.util.SimpleHashSet#remove(java.lang.Object)
     */
    public void test_removeLjava_lang_Object() {
        // Test for method boolean java.util.SimpleHashSet.remove(java.lang.Object)
        int size = hs.size();
        hs.remove(new Integer(98));
        assertTrue("Failed to remove element", !hs.contains(new Integer(98)));
        assertTrue("Failed to decrement set size", hs.size() == size - 1);
        SimpleHashSet s = new SimpleHashSet();
        s.add(null);
        assertTrue("Cannot handle null", s.remove(null));
        assertFalse(hs.remove(new Integer(-98)));
    }

    /**
     * @tests java.util.SimpleHashSet#size()
     */
    public void test_size() {
        // Test for method int java.util.SimpleHashSet.size()
        assertTrue("Returned incorrect size", hs.size() == (objArray.length + 1));
        hs.clear();
        assertEquals("Cleared set returned non-zero size", 0, hs.size());
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        objArray = new Object[1000];
        for (int i = 0; i < objArray.length; i++) {
            objArray[i] = new Integer(i);
        }
        hs = new SimpleHashSet();
        for (int i = 0; i < objArray.length; i++) {
            hs.add(objArray[i]);
        }
        hs.add(null);
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
        hs = null;
        objArray = null;
    }
}

