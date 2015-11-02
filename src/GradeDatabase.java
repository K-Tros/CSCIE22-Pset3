

import java.util.*;

/**
 * A simple in-memory database of student and grade information.
 */
public class GradeDatabase {
    /* 
     * A private inner class for storing information about a student.
     */
    private class StudentRecord {
        private int id;
        private String lastName;
        private String firstName;
        
        StudentRecord(int id, String lastName, String firstName) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
        }
    }
    
    /* 
     * A private inner class for storing information about a student's
     * grade on a particular assignment.
     */
    private class GradeRecord {
        private int studentID;
        private String assignment;    // e.g., "PS 1" or "midterm"
        private int grade;
        
        GradeRecord(int studentID, String assignment, int grade) {
            this.studentID = studentID;
            this.assignment = assignment;
            this.grade = grade;
        }
    }
    
    /**** add your instance variables here ****/
    private LLList studentTable;
    private LLList gradeTable;
    
    
    public GradeDatabase() {
        /** complete the constructor below **/
        studentTable = new LLList();
        gradeTable = new LLList();
        
    }
    
    /**
     * addStudent - add a record for the student with the specified information
     * This will always add the new item to the beginning of the list, giving a time
     * efficiency of O(1).
     * 
     * Adding in this way allows for very fast updates to the tables. Since it is likely
     * that grades will be entered much more frequently than reports will be run, it
     * will generate much less frustration for users who are actively entering grades,
     * as opposed to running reports which may only occur a dozen times a year, at most.
     */
    public void addStudent(int id, String last, String first) {
    	StudentRecord sRecord = new StudentRecord(id, last, first);
        studentTable.addItem(sRecord, 0);
    }
    
    /**
     * addGrade - add a record for the grade entry with the specified details
     * This will always add the new item to the beginning of the list, giving a time
     * efficiency of O(1).
     * 
     * Adding in this way allows for very fast updates to the tables. Since it is likely
     * that grades will be entered much more frequently than reports will be run, it
     * will generate much less frustration for users who are actively entering grades,
     * as opposed to running reports which may only occur a dozen times a year, at most.
     */
    public void addGrade(int id, String asst, int grade) {
        GradeRecord gRecord = new GradeRecord(id, asst, grade);
        gradeTable.addItem(gRecord, 0);
    }
    
    /**
     * printStudents - print the entries in the student table
     */
    public void printStudents() {
        System.out.println();
        System.out.println("id\tlast\t\tfirst");
        System.out.println("--------------------------------------------");
        
        StudentRecord sRecord;
        for (int i = 0; i < studentTable.length(); i++)
        {
        	sRecord = (StudentRecord) studentTable.getItem(i);
        	System.out.printf("%d\t%s\t\t%s\n",
        			sRecord.id,
        			sRecord.lastName,
        			sRecord.firstName);
        }
        
    }
    
    /**
     * printGrades - print the entries in the grade table
     */
    public void printGrades() {
        System.out.println();
        System.out.println("id\tassignment\tgrade");
        System.out.println("--------------------------------------------");
        
        GradeRecord gRecord;
        for (int i = 0; i < gradeTable.length(); i++)
        {
        	gRecord = (GradeRecord) gradeTable.getItem(i);
        	System.out.printf("%d\t%s\t%d\n",
        			gRecord.studentID,
        			gRecord.assignment,
        			gRecord.grade);
        }
        
    }
    
    /**
     * printStudentsGrades - print a "join" of the student and grade
     * tables.  See the problem set handout for more details.
     * 
     * This method is not very time efficient due to the way in which items are added to
     * the tables. In order to allow for fast updates, items are added to the tables in
     * the order that they are entered and are not sorted in any way when added. This
     * sacrifices efficiency in printing for constant time efficiency in making updates.
     * Users will generally be making many more updates to the tables over the course of
     * their work, so slowness in making updates will be more frustrating to a user that
     * has to be active during the process. When printing a report, however, it can be
     * started and then the user can walk away while it compiles. This, it would make
     * sense for adding to the tables to be more efficient at the cost of efficiency
     * when printing.
     */
    public void printStudentsGrades() {
        System.out.println();
        System.out.println("last\t\tfirst\tassignment\tgrade");
        System.out.println("------------------------------------------------");
        
        StudentRecord sRecord;
        GradeRecord gRecord;
        ListIterator sIter = studentTable.iterator();
        
        while (sIter.hasNext())
        {
        	sRecord = (StudentRecord) sIter.next();
        	ListIterator gIter = gradeTable.iterator();
        	while (gIter.hasNext())
        	{
        		gRecord = (GradeRecord) gIter.next();
        		if (sRecord.id == gRecord.studentID) {
        			System.out.printf("%s\t\t%s\t%s\t%d\n",
        					sRecord.lastName,
        					sRecord.firstName,
        					gRecord.assignment,
        					gRecord.grade);
        		}
        	}
        }
        
        /*
        for (int i = 0; i < studentTable.length(); i++)
        {
        	sRecord = (StudentRecord) studentTable.getItem(i);
        	for (int j = 0; j < gradeTable.length(); j++) 
        	{
        		gRecord = (GradeRecord) gradeTable.getItem(j);
        		if (sRecord.id == gRecord.studentID) 
        		{
        			System.out.printf("%s\t\t%s\t%s\t%d\n",
        					sRecord.lastName,
        					sRecord.firstName,
        					gRecord.assignment,
        					gRecord.grade);
        		}
        	}
        }
        */
        
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String last, first, asst;
        int id, grade, op;
        
        GradeDatabase db = new GradeDatabase();
        
        while (true) {
            System.out.println();
            System.out.println("(1) Add student");
            System.out.println("(2) Add grade");
            System.out.println("(3) Print students");
            System.out.println("(4) Print grades");
            System.out.println("(5) Print each student's grades");
            System.out.println("(6) Exit");
            System.out.print("Which operation (1-6)? ");
            op = in.nextInt();
            in.nextLine();
            
            switch (op) {
                case 1:
                    System.out.print("    id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    last: ");
                    last = in.nextLine();
                    System.out.print("    first: ");
                    first = in.nextLine();
                    
                    db.addStudent(id, last, first);
                    break;
                case 2:
                    System.out.print("    student id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    assignment: ");
                    asst = in.nextLine();
                    System.out.print("    grade: ");
                    grade = in.nextInt();
                    in.nextLine();
                    
                    db.addGrade(id, asst, grade);
                    break;
                case 3:
                    db.printStudents();
                    break;
                case 4:
                    db.printGrades();
                    break;
                case 5:
                    db.printStudentsGrades();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.  " + 
                                       "Please enter a number from 1-6.");
            }
        }
    }
}
