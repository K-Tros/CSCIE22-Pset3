

import java.io.*;
import java.util.*;

/**
 * A class for representing a string using a linked list.  Each
 * character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list.  The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter.  This
 * approach allows us to use recursion to write many of the methods.
 */
public class StringNode {
    private char ch;
    private StringNode next;

    /**
     * Constructor
     */
    public StringNode(char c, StringNode n) {
        ch = c;
        next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short, returns null.
     */
    private static StringNode getNode(StringNode str, int i) {
    	if (i < 0 || str == null)
            return null;
    	for (int j = 0; j < i; j++)
    	{
    		str = str.next;
    	}
    	
    	return str;
    	
    }

    /*****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /**
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNode str, int i) {
        if (str == null)
            throw new IllegalArgumentException("the string is empty");
        
        StringNode node = getNode(str, i);

        if (node != null) 
            return node.ch;     
        else
            throw new IllegalArgumentException("invalid index: " + i);
    }

    /**
     * concat - returns the concatenation of two linked-list strings
     */
    public static StringNode concat(StringNode str1, StringNode str2) {
    	StringNode cat = copy(str1);
    	StringNode temp = cat;
    	
    	while (temp.next != null)
    	{
    		temp = temp.next;
    	}
    	
    	temp.next = copy(str2);
    	
    	return cat;
    }
    
    /**
     * contains - returns true if the linked-list string str contains
     * at least one occurrence of the character ch, and false otherwise.
     */
    public static boolean contains(StringNode str, char ch) {
    	
    	while (str != null)
    	{
    		if (str.ch == ch) return true;
    		str = str.next;
    	};
    	
    	return false;
    	
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNode convert(String s) {
        if (s.length() == 0)
            return null;

        StringNode firstNode = new StringNode(s.charAt(0), null);
        StringNode prevNode = firstNode;
        StringNode nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }
    
    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNode copy(StringNode str) {
    	if (str == null)
    		return null;
    	
    	StringNode copyFirst = new StringNode(str.ch, null);
    	StringNode temp = copyFirst;
    	str = str.next;
    	
    	while (str != null)
    	{
    		StringNode next = new StringNode(str.ch, null);
    		temp.next = next;
    		temp = next;
    		str = str.next;
    	}
    	
        return copyFirst;
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNode deleteChar(StringNode str, int i) {
        if (str == null)
            throw new IllegalArgumentException("string is empty");
        else if (i < 0) 
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0) 
            str = str.next;
        else {
            StringNode prevNode = getNode(str, i-1);
            if (prevNode != null && prevNode.next != null) 
                prevNode.next = prevNode.next.next;
            else
                throw new IllegalArgumentException("invalid index: " + i);
        }

        return str;
    }

    /**
     * indexOf - returns the position of the first occurrence of
     * character ch in the given linked-list string.  If there is
     * none, returns -1.
     */
    public static int indexOf(StringNode str, char ch) {
    	int count = 0;
    	
    	while (str != null)
    	{
    		if (str.ch == ch) return count;
    		str = str.next;
    		count++;
    	};
    	
    	return -1;
    	
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNode insertChar(StringNode str, int i, char ch) {
        StringNode newNode, prevNode;

        if (i < 0) 
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0) {
            newNode = new StringNode(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i-1);
            if (prevNode != null) {
                newNode = new StringNode(ch, prevNode.next);
                prevNode.next = newNode;
            } else 
                throw new IllegalArgumentException("invalid index: " + i);
        }

        return str;
    }

    /**
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNode insertSorted(StringNode str, char ch) {
        StringNode newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNode(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else 
            trail.next = newNode;

        return str;
    }

    /**
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNode str) {
        if (str == null)
            return  0;
        
        int length = 1;
        
        while (str.next != null)
        {
        	str = str.next;
        	length++;
        }
        
        return length;
    }

    /**
     * numOccurrences - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccurrences(StringNode str, char ch) {
    	int numOccur = 0;
        StringNode trav = str;

        while (trav != null) {
            if (trav.ch == ch)
                numOccur++;

            trav = trav.next;
        }

        return numOccur;
    }

    /**
     * print - recursively writes the specified linked-list string to System.out
     */
    public static void print(StringNode str) {
        while (str != null)
        {
        	System.out.print(str.ch);
        	str = str.next;
        }
    }

    /**
     * printReverse - recursively writes the reverse of the specified 
     * linked-list string to System.out
     */
    public static void printReverse(StringNode str) {
        if (str == null || str.next == null)
            return;
        
        str = reverse(str);
        
        StringNode temp = str;
        while (temp != null)
        {
        	System.out.print(temp.ch);
        	temp = temp.next;
        }
        
        reverse(str);
        
    }
    
    /**
     * This method will reverse a linked list.
     * @param str Head of the linked list to reverse
     * @return The head of the new linked list
     */
    private static StringNode reverse(StringNode str) {
    	StringNode second = str.next;
        StringNode current = second.next;
        StringNode next;
        StringNode prev = second;
        
        str.next = null;
        second.next = str;
        
        while (current != null)
        {
        	next = current.next;
        	current.next = prev;
        	prev = current;
        	current = next;
        	
        }
        
        return prev;
    }
    
    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNode read(InputStream in) throws IOException {
    	StringNode str = null;
        StringNode current;
        char ch = (char)in.read();

        if (ch != '\n') {
            current = new StringNode(ch, null); // create the initial node
            str = current;                      // we will return it

            ch = (char)in.read();               // read next character
            while (ch != '\n') {
                current.next = new StringNode(ch, null);
                current = current.next;

                ch = (char)in.read();
            }
        }

        return str;
    }
    
    /*
     * toString - creates and returns the Java string that
     * the current StringNode represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        StringNode trav = this;   // start trav on the current node
            
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
         
        return str;
    }
    
    /**
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     */
    public static void toUpperCase(StringNode str) {  
    	while (str != null) {
    		str.ch = Character.toUpperCase(str.ch);
    		toUpperCase(str.next);
    	}	
    } 
    
    /**
     * This method will print the characters in the linked list forwards, and then
     * print it again backwards.
     * @param str The StringNode to start with
     */
    public static void printMirrored(StringNode str) {
    	if (str == null) 
    		return;
    	System.out.print(str.ch);
    	printMirrored(str.next);
    	System.out.print(str.ch);
    }
    
    /**
     * This method takes the head of the linked list passed in and returns a sub segment
     * of that linked list as a new linked list, whose bounds are given by start and end
     * 
     * @param str Head of the linked list
     * @param start The start index of the sub segment
     * @param end The end index of the sub segment
     * @return A pointer to the head of the new sublist
     */
    public static StringNode substring(StringNode str, int start, int end) {
    	if (start == end)
    		return null;
    	if (str == null || start < 0)
    		throw new IllegalArgumentException();
    	if (start == 0) 
    	{
    		StringNode temp = new StringNode(str.ch, null);
    		temp.ch = str.ch;
    		temp.next = substring(str.next, start, end - 1);
    		return temp;
    	}
    	
    	return substring(str.next, start - 1, end - 1);
    		
    }
    
    /**
     * Returns the index of the nth time the character ch appears in str
     * @param str Head of the linked list to search
     * @param n How many instances of the character to find
     * @param ch The character to look for
     * @return The index of the nth appears of ch in str
     */
    public static int nthIndexOf(StringNode str, int n, char ch) {
    	if (str == null || n <= 0)
    		return -1;
    	
    	int temp = 0;
    	
    	if (str.ch == ch)
    	{
    		temp = nthIndexOf(str.next, n - 1, ch);
    		if (temp == -1)
    			return 0;
    		return ++temp;
    	}
    	
    	temp = nthIndexOf(str.next, n, ch);
    	if (temp == -1)
    		return -1;
    	
    	return ++temp;
    }
              
    public static void main(String[] args) throws IOException {
        StringNode copy, str, str1, str2, str3;
        String line;
        int n;
        char ch;

        // convert, print, and toUpperCase
        str = StringNode.convert("finen");
        System.out.print("Here's a string: "); 
        StringNode.print(str);
        System.out.println();
        System.out.print("Here it is in upper-case letters: "); 
        StringNode.toUpperCase(str);
        StringNode.print(str);
        System.out.println();
        System.out.println();

        Scanner in = new Scanner(System.in);
        
        // read, toString, length, and printReverse.
        System.out.print("Type a string: ");
        String s = in.nextLine();
        str1 = StringNode.convert(s);
        System.out.print("Your string is: "); 
        System.out.println(str1);        // implicit toString call
        System.out.print("\nHere it is reversed: ");  
        StringNode.printReverse(str1);
        System.out.println("\nIts length is " + StringNode.length(str1) + 
            " characters.");

        // charAt
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to get (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        try {
            ch = StringNode.charAt(str1, n);
            System.out.println("That character is " + ch);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
    
        // contains and indexOf
        System.out.print("\nWhat character to search for? ");
        line = in.nextLine();
        ch = line.charAt(0);
        System.out.print("Using contains to see if " + ch + " is in the string...");
        if (StringNode.contains(str1, ch)) {
            System.out.println("it is.");
        } else {
            System.out.println("it is not.");
        }
        n = StringNode.indexOf(str1, ch);
        System.out.println("indexOf returns: " + n);
        
        // deleteChar and copy
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        copy = StringNode.copy(str1);
        try {
            str1 = StringNode.deleteChar(str1, n);
            StringNode.print(str1);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        System.out.print("\nUnchanged copy: ");
        StringNode.print(copy);
        System.out.println();

        // insertChar
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to insert before (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        System.out.print("What character to insert? ");
        line = in.nextLine();
        try {
            str1 = StringNode.insertChar(str1, n, line.charAt(0));
            StringNode.print(str1);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        
        // concat
        System.out.print("\nType another string: ");
        s = in.nextLine();
        str2 = StringNode.convert(s);
        System.out.println("Its length is " + StringNode.length(str2) + 
            " characters.");
        System.out.print("\nconcatenation = ");
        StringNode.print(StringNode.concat(str1, str2));
        System.out.println();

        // insertSorted
        System.out.print("\nType a string of characters in alphabetical order: ");
        s = in.nextLine();
        str3 = StringNode.convert(s);
        System.out.print("What character to insert in order? ");
        line = in.nextLine();
        str3 = StringNode.insertSorted(str3, line.charAt(0));
        StringNode.print(str3);
        System.out.println();
    }
}
