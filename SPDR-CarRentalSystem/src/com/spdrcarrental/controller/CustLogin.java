package com.spdrcarrental.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.spdrcarrental.bean.Car;
import com.spdrcarrental.bean.Customer;
import com.spdrcarrental.bean.Rent;
import com.spdrcarrental.dao.CarDAOImpl;
import com.spdrcarrental.dao.CustomerBillingInfoImpl;
import com.spdrcarrental.factory.CarDAOFactory;
import com.spdrcarrental.factory.ConnectionFactory;
import com.spdrcarrental.factory.CustomerRegistrationFactory;
import com.spdrcarrental.service.CustomerRegistrationService;

//String SQL = "SELECT * FROM customer WHERE username=' "+name+" ' and password=' "+password+" ' ";



public class CustLogin  {

	MyApplication mapp=new MyApplication();
	String status="";
	public void custlogin(Customer cust) {
		Car car1=new Car();
		//Customer cust=new Customer();
		CustomerRegistrationService mycar=CustomerRegistrationFactory.getRegistration();
		CarDAOImpl myCarDao1=CarDAOFactory.getCarDAOImpl();
		Scanner sc = new Scanner(System.in);
		MyApplication myap=new MyApplication();
		CustomerBillingInfoImpl cb=new CustomerBillingInfoImpl();



		System.out.print("Enter Username : ");
		cust.setUsername(sc.next());
		System.out.print("Enter Password : ");
		cust.setPassword(sc.next());


		//String status="";

		try {
			Connection con=ConnectionFactory.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM customer WHERE username=' "+cust.getUsername()+" ' and password=' "+cust.getPassword()+" ' ");

			Boolean b=rs.next();
			if(b==true)
			{
				while (rs.next()) {
					cust.setCustId(rs.getInt(1));
					cust.setCname(rs.getString(2));
					cust.setAddress(rs.getString(3));
					cust.setEmail(rs.getString(4));
					cust.setPhoneNo(rs.getLong(5));
					cust.setUsername(rs.getString(6));
					cust.setPassword(rs.getString(7));


				}//while{

				System.out.println("\n\n*****************************************LOGIN SUCCESSFUL************************************");
				do {


					System.out.println("\n1.Show Availbele Cars \n2.Book Car\n3.View My Billing\n4.Update My Data \n0.logout and Go TO home Page ");
					System.out.println("\nEnter ur choice");
					int op=sc.nextInt();


					switch(op)
					{
					case 1:
						
						System.out.println("***************** Availble Cars***********************\n\n");
						myCarDao1.showCarDetails(car1);
						break;

					case 2:
						System.out.println("book  ur drive with us by just providing info your id,carid, orderdate,return date...we happy to serve you");
						System.out.println("-----------------------------------------------------------------------------------------------------");
						Rent rent=new Rent();
						System.out.println("Enter Customer id :  ");
						rent.setCustId(sc.nextInt());
						System.out.println("\n Enter Car id:");
						rent.setCarid(sc.nextInt());
						System.out.println("\n Enter order date in year/month/date format :");
						String odate=sc.next();
						//LocalDate date=LocalDate.parse(odate);
						rent.setOrderdate(odate);
						System.out.println("Your Entered Date is : "+odate);

						System.out.println("\n Enter return date in year/month/date format :");
						String rdate=sc.next();
						//LocalDate date1=LocalDate.parse(rdate);
						rent.setReturndate(rdate);
						System.out.println("Your Enterd Date is :"+rdate);
						//						
						mycar.bookCarService(rent);

						break;

					case 3:  

						System.out.println("Enter your cutomer id : ");
						cust.setCustId(sc.nextInt());
						System.out.println("show my billing details");
						cb.showBilling(cust);

						break;

					case 4:
						System.out.println("update cutomer(update my details");
						System.out.println("You can modify your name ,address,EmialId,Phone No ,Password ");
						System.out.println("press 1 to update ");
						int choice=sc.nextInt();
						if(choice==1)
						{
							System.out.println("Enter the New Name :");
							cust.setCname(sc.next());
							System.out.println("Enter new Address :");
							cust.setAddress(sc.next());
							System.out.println("Enter new EmailId :");
							cust.setEmail(sc.next());
							System.out.println("Enter Your Phone NO :");
							cust.setPhoneNo(sc.nextLong());
							System.out.println("Enter new Password :");
							cust.setPassword(sc.next());
							String status4=mycar.updateCustData(cust);
							System.out.println(status4);
						}
						else {

							break;
						}
					case 0:
						mapp.homepage();
						//System.exit(0);
						break;
					default:
						System.out.println("Enter valid option");
					}
				}while(true);


			}
			else {
				System.out.println("................Invalid Username and Password.........or do registeration");
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			status="\nRegistration Unsccessfull Due to Wrong Connection..";
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}
}

//



//int rowcount=stmt.executeUpdate("insert into admin values('admin','admin123')");

//System.out.println("\nYour Details : \n");
//System.out.println(cust.getCname());
//System.out.println(databasecname);
//System.out.println(databaseemail);
//System.out.println(databasenumber);
//System.out.println(databaseUsername);
//System.out.println(databasePassword);


//int databaseCustid=0;
//String databasecname="";
//String databaseaddress="";
//String databaseemail="";
//String databasenumber="";
//String databaseUsername ="";
//String databasePassword ="";



//databaseCustid=rs.getInt(1);
//					databasecname=rs.getString(2);
//					databaseaddress=rs.getString(3);
//					databaseemail=rs.getString(4);
//					databasenumber=rs.getString(5);
//					databaseUsername = rs.getString(6);
//					databasePassword = rs.getString(7);