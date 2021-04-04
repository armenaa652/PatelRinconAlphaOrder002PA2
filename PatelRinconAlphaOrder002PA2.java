 /**
 * @(#)PatelRinconAlphaOrder002PA2.java
 * @author Happen Patel, Lucas Rincon
 * @version 1.0 2021/02/23 1:55 PM
 * 
 * Program Purpose: Code that displays a messages based on input of user. 
 * 
 */

import java.util.Scanner;   //input library
import java.util.Calendar;   //date and time library
import java.util.ArrayList;   //arraylist library

public class PatelRinconAlphaOrder002PA2
{
  private static Scanner input = new Scanner(System.in);//int scanner
  
  private static int firstNmLength = 0;//int firstNmLength
  
  public static int checkValidIntInput(String errorMsg){//checkValidIntInput
    int n = 0;
    boolean validInput = false;
    do{ //do while loop until validInput is false
      validInput = true;
      try{ //try catch loop to check for invalid input
        n = input.nextInt();
      }catch(Exception e){
        System.out.printf("Invalid Input! %s\n", errorMsg);
        validInput = false;
      }
      input.nextLine(); //clear buffer
    }while(validInput == !true);
    return n;
  }
  
  public static double checkValidDoubleInput(String errorMsg){
    double n = 0;
    boolean validInput = false;
    
    do 
    {
      validInput = true;
      try
      {
        n = input.nextDouble();
      }catch(Exception e)
      {
        System.out.printf("Invalid Input! %s\n", errorMsg);
        validInput = false;
      }
      input.nextLine();
    }while(validInput == !true);
    return n;
  }
  
  public static void main(String []args)
  {
    generateReport();
//    exit(1);
  }
  
  public static void generateReport()
  {
    String employeeName = "";//initialize variables
    double payRate = 0, percent401K = 0, grossPayTotal = 0, total401K = 0;
    int trigger = 0, hoursWorked = 0;
    char cont = 'N';
    ArrayList<Double> grossPay = new ArrayList<Double>(), retire401K = new ArrayList<Double>();
    ArrayList<String> employeeNames = new ArrayList<String>();
    System.out.printf("WEEKLY HOURLY PAYROLL SYSTEM\n"//user prompt
             + "Continue? Enter \'Y\' or \'N\':");
    cont = input.nextLine().charAt(0);
    if(Character.toUpperCase(cont) == 'Y')
    {
      do
      {
        employeeName = setEmployeeName();
        hoursWorked = setHoursWorked(employeeName.substring(0, firstNmLength));
        payRate = setPayRate();
        percent401K = set401K();
        employeeNames.add(employeeName);
        grossPay.add(hoursWorked * payRate);
        retire401K.add(hoursWorked * payRate * percent401K * .01);
        System.out.printf("Enter \'Y\' to add another employee or \'N\' to exit:");
        cont = input.nextLine().charAt(0);
      }while(Character.toUpperCase(cont) == 'Y');
    }
    for(int i = 0; i < employeeNames.size(); i++)
    {
      System.out.printf("Employee Name: %s\n"
                        + "Gross Pay: %.2f\n"
                        + "401K: %.2f\n", employeeNames.get(i), grossPay.get(i), retire401K.get(i));
      grossPayTotal += grossPay.get(i);
      total401K += retire401K.get(i);
    }
    System.out.printf("Total Gross Pay: %.2f\n"
                        + "Total 401K: %.2f\n", grossPayTotal, total401K);
    
    Calendar cal = Calendar.getInstance();
    String payrollExpense = String.format("WEEKLY HOURLY EMPLOYEE PAYROLL"
                                            + "%n%nDate: " + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR)
                                            + "%nTime: " + cal.get(Calendar.AM_PM)
                                            + "%n%n%56S %23S", "GROSS PAY", "401K");
    
    System.out.printf(payrollExpense);
    System.out.printf("\nExiting Weekly Hourly Payroll System\n");
  }
  
  /**
   * Uses nextLine method from the scanner library to enter the users
   * first and last name. This method calls method isAlpha to validate
   * the user input and check that it is all alphabetic.
   */
  public static String setEmployeeName()
  {
    String first = "", last = "";//initialize variables
    boolean validInput = false;
    
    System.out.printf("Enter the employee's first name press enter than the last name press enter:\n");//user prompt
    
    while(validInput == !true)//while loop 
    {//error validation
      first = input.nextLine();//user input
      validInput = isAlpha(first);
      if(!validInput)
      {
        System.out.printf("Enter valid first name:\n");//error message
      }
    }
    
    firstNmLength = first.length();//set value for first name length
    validInput = false;//intialize input validation
    
    while(validInput == !true)
    {//error validation
      last = input.nextLine();//user input
      validInput = isAlpha(last);
      if(!validInput)
      {
        System.out.printf("Enter valid last name:\n");//error message
      }
    }
    return first + " " + last;//returns name
  }//END setEmployeeName(): static String
  
  public static int setHoursWorked(String first)
  {
    int hoursWorked = 0;
    
    System.out.printf("Enter the number of hours worked for " + first + ":");
    hoursWorked = checkValidIntInput("Re-enter the number of hours worked for " + first + ":");
    hoursWorked = testHoursWorked(hoursWorked);
    
    return hoursWorked;
  }
  
  public static int testHoursWorked(int hoursWorked)
  {
    boolean validInput = false;
    while(validInput == !true)
    {
      if(hoursWorked > 40)
      {
        System.out.printf("Hours worked cannot EXCEED 40. Please re-enter:");
        hoursWorked = checkValidIntInput("Re-enter the number of hours "
                      + "worked not less than 5 or greater than 40:");
      }
      else if(hoursWorked < 5)
      {
        System.out.printf("Hours worked cannot be LESS than 5. Please re-enter:");
        hoursWorked = checkValidIntInput("Re-enter the number of hours "
                      + "worked not less than 5 or greater than 40:");
      }else
      {
        validInput = true;
      }
    }
    return hoursWorked;
  }
  
  public static double setPayRate(){
    double payRate = 0;
    System.out.printf("Enter the employee's hourly pay rate:");
    payRate = checkValidDoubleInput("Re-enter the employee's hourly pay rate:");
    payRate = testPayRate(payRate);
    return payRate;
  }
  
  public static double testPayRate(double payRate){
    boolean validInput = false;
    while(validInput == !true){
      if(payRate > 26){
        System.out.printf("Hourly pay cannot EXCEED $26.00. Please re-enter:");
        payRate = checkValidDoubleInput("Re-enter an hourly pay rate that is not less than $7.25 or greater than $26.00:");
      }
      else if(payRate < 7.25){
        System.out.printf("Hourly pay cannot be LESS than $7.25. Please re-enter:");
        payRate = checkValidDoubleInput("Re-enter an hourly pay rate that is not less than $7.25 or greater than $26.00:");
      }else{
        validInput = true;
      }
    }
    return payRate;
  }
  
  public static double set401K(){
    double percent401K = 0;
    System.out.printf("Enter the employee\'s 401K contribution as a percentage of salary (not to exceed 10%%):");
    percent401K = checkValidDoubleInput("Re-enter the employee\'s 401K contribution as a percentage of salary (not to exceed 10%%):");
    percent401K = test401K(percent401K);
    return percent401K;
  }
  
  public static double test401K(double percent401K){
    boolean validInput = false;
    while(validInput == !true){
      if(percent401K > 10){
        System.out.printf("Contribution cannot EXCEED 10%%. Please re-enter:");
        percent401K = checkValidDoubleInput("Re-enter the employee\'s 401K contribution as a percentage of salary (not to exceed 10%%):");
      }
      else if(percent401K < 0){
        System.out.printf("Contribution cannot be LESS than 0%%. Please re-enter:");
        percent401K = checkValidDoubleInput("Re-enter the employee\'s 401K contribution as a percentage of salary (not to exceed 10%%):");
      }else{
        validInput = true;
      }
    }
    return percent401K;
  }
  
  public static String formatDollarSign(String employeeName, double grossPay, double retire401K){
    return "Howdy";
  }
  
  /**
   * Using lambda expression to check for names that are alphabetic.
   * The :: tells the compiler to call the isLetter method from
   * the Character class. The chars() is a Java 9 String class
   * method.
   */
  public static boolean isAlpha(String name)
  {
  return name != null && name.chars().allMatch(Character::isLetter);
  }//END isAlpha(String): static boolean
}
       