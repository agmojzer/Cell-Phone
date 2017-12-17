package util;
import java.util.Scanner;

public class Console {
    
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String prompt) {
        while(true) {
        	System.out.print(prompt);
        	String s = sc.nextLine();
        	if (!s.isEmpty()) {
        	return s;
        		} else {System.out.println("Please enter a valid string");
        		continue;
        		}	
        }
    }
    
    
    public static String getString(String prompt, int maxLength) {
    	boolean isValid = false;
    	String s = "";
    	while (!isValid) {
    		s = getString(prompt);
    		if (s.length()>maxLength) {
    			System.out.println("Invald input! Length must be <= " +maxLength+".");
    			
    	}
    		else 
    			isValid = true;
    	}
    	return s;
}
    
    public static boolean getBoolean(String prompt) {
    	boolean b = false;
    	boolean isValid = false;
    	while(!isValid) {
    		String s = getString(prompt);
    		if (s.equalsIgnoreCase("t")||s.equalsIgnoreCase("true")) {
    			b = true;
    			isValid = true;
    		}
    		else if (s.equalsIgnoreCase("f")||s.equalsIgnoreCase("false")) {
    			b = false;
    			isValid = true;
    		}	
    		else
    			System.out.println("Invalid input. Please enter one of the following: T, t, F, or f");
    		
    	}
    	return b;
    }
    
    public static int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(prompt);
            if (i < min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (i > max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return i;
    }
    
    public static long getLong(String prompt, long min, long max) {
        long i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getLong(prompt);
            if (i < min || i> max) {
                System.out.println(
                        "Error! Please enter a valid phone number");
            } else {
                isValid = true;
            }
        }
        return i;
    }
    
    public static long getLong(String prompt) {
        long i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextLong()) {
                i = sc.nextLong();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static double getDouble(String prompt) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                System.out.println("Error! Invalid number. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }

    public static double getDouble(String prompt, double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            d = getDouble(prompt);
            if (d <= min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (d >= max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return d;
    }
}