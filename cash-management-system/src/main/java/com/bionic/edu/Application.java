package com.bionic.edu;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Named
public class Application{
	@Inject
    MerchantService merchantService;
	
	@Inject
	CustomerService customerService;

	@Inject
	PaymentService paymentService;
	
    @SuppressWarnings("resource")
    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
    	Application application = (Application)context.getBean("application");
    	//application.printMerchantName(1);
    	//int id = application.addMerchant();
        //System.out.println("New merchant id = " + id);
    	
    	// application.removeMerchant(6);
    	
    	//application.upadateAccount();
    	
    	/* customer */
    	//application.printCustomerName(1);
    	//int id = application.addCustomer();
    	//System.out.println("New customer id = " + id);
    	
    	/* !customer */
    	//application.findAll();
    	
    	//PaymentService paymentService = context.getBean(PaymentService.class);
        //application.getPaymentsForMerchant(3);
    	
        // application.getCustomersWithLargePays(500.0);
    	
        // application.getPaymentSum();
    	
        // application.getSumToPay();
    	
        // application.getTotalReport();
    	
        // application.findPayment(2);
    	
    	/*
    	MerchantService merchantService = 	context.getBean(MerchantService.class);
        List<Merchant> list = merchantService.getSortedByNeedToPay();
        for(Merchant m: list) {
        	System.out.println("=======================");
        	System.out.println(m.getName());
        	Collection<Payment> payments = m.getPayments();
        	for (Payment p : payments) {
    	      System.out.println(p.toString());
        	}
        }
        */
    	
    	/*CustomerService customerService = 	context.getBean(CustomerService.class);
        Customer customer = customerService.findById(1);
        if (customer != null){
        	System.out.println(customer.toString());
         	Collection<Merchant> merchants = customer.getMerchants();
         	for (Merchant m : merchants) {
         		System.out.println(m.getName());
        	}
        }*/
    	
    	// application.findAllCustomers();
    	//int id = application.addPayment();
    	//System.out.println("New payment id = " + id);
    	application.findAllPayments();
    }
    
    public int addMerchant(){
    	Merchant merchant = new Merchant();
    	merchant.setAccount("555555555");
    	merchant.setBankName("Erste Bank");
    	merchant.setCharge(1.2);
    	merchant.setMinSum(145.0);
    	merchant.setName("N&M");
    	merchant.setPeriod((short)1);
    	merchant.setSwift("X85T44wwq");
    	merchantService.save(merchant);
    	return merchant.getId();
    }
    
    public void printMerchantName(int id){
        Merchant m1 = merchantService.findById(id);
        System.out.println("name = " + m1.getName());     	
    }
    
    public void printCustomerName(int id) {
    	Customer c1 = customerService.findById(id);
    	System.out.println("name = " + c1.getName());
    }
    
    public void removeMerchant(int id){
        merchantService.remove(id);
    }
    
    public void upadateAccount(){
        merchantService.updateAccount(2, "5555555555");
    }
    
    public int addCustomer() {
    	Customer customer = new Customer();
    	customer.setName("Tim");
    	customer.setAddress("Zurich");
    	customer.setEmail("tim@gmail.com");
    	customer.setCcno("111");
    	customer.setCctype("222");
    	java.util.Date utilDate = new java.util.Date();
    	java.sql.Date maturity = new java.sql.Date(utilDate.getTime());
    	customer.setMaturity(maturity);
    	customerService.save(customer);
    	return customer.getId();
    }
    
    public void findAll(){
        List<Merchant> list = merchantService.findAll();
        System.out.println("           name              charge");
        for(Merchant m: list){
            System.out.format("%1$25s     %2$4.1f  %n", 	  
    	       m.getName(), m.getCharge());
        }
    }
    
    /*
    public void getPaymentsForMerchant(int id){
        List<Payment> list = paymentService.findByMerchantId(id);
        System.out.println("        date        merchant   sum  ");
        for(Payment p: list){
        	SimpleDateFormat dtFrm = new 	 
    	       SimpleDateFormat("dd.MM.yyyy HH:mm"); 
        	String txDate = dtFrm.format(p.getDt());
           	System.out.format("  %1s   %2$3d     %3$6.2f   %n", 	   
    	       txDate, p.getMerchantId(), p.getSumPayed());
        }
    } */
    
    public void getCustomersWithLargePays(double limit){
        List<String> list = customerService.getNames(limit);
        for(String s: list)
            System.out.println(s); 
    }
    
    public void getPaymentSum(){
        double sum = paymentService.getPaymentSum();
            System.out.println("total = " + sum); 
    }
    
    public void getSumToPay(){
        List<Merchant> list = 	merchantService.getSortedByNeedToPay();
        System.out.println("        Merchant           Sum to Pay");
        for(Merchant m: list){
               System.out.format("%1$-25s  %2$8.2f  %n", m.getName(), 	m .getNeedToSend()); 
         }
    }
    
    public void getTotalReport(){
        List<Result> list = merchantService.getTotalReport();
        for(Result r: list)
            System.out.format("%1$25s  %2$8.2f %n", 	r.getName(), r.getSum()); 
    }
    
    public void findPayment(int id){
        Payment p = paymentService.findById(id);
        SimpleDateFormat fmt = new 	SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.println("Date = " + fmt.format(p.getDt()) + "   	merchant = " + p.getMerchant().getName() + "   sum = " + p.getSumPayed());
    }
    
    public void findAllCustomers(){
    	List<Customer> list = customerService.findAll();
        System.out.println("name                     address");
        for(Customer c: list){
            System.out.format("%1$25s     %2$40s  %n", 	  
    	       c.getName(), c.getAddress());
        }
    	/*
        List<Merchant> list = merchantService.findAll();
        System.out.println("           name              charge");
        for(Merchant m: list){
            System.out.format("%1$25s     %2$4.1f  %n", 	  
    	       m.getName(), m.getCharge());
        }
        */
    }
    
    /*
     * private int id;
private double chargePayed;
private double sumPayed;
private String goods;
private java.sql.Timestamp dt;
private int customerId;
@ManyToOne
@JoinColumn(name="merchantId")
private Merchant merchant;*/
    public int addPayment() {
    	Payment payment = new Payment();
    	payment.setChargePayed(0.22);
    	payment.setSumPayed(22);
    	payment.setGoods("Lindt EXCELLENCE 85%, 100g, 10 pcs");
    	payment.setDt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    	payment.setCustomerId(1);
    	payment.setMerchant(merchantService.findById(1));
    	paymentService.save(payment);
    	return payment.getId();
    }
    
    public void findAllPayments() {
    	List<Payment> list = paymentService.findAll();
    	System.out.println();
    	for (Payment p : list) {
    		System.out.format("%1$25s   %2$25s   %3$4.1f  %n",
    				p.getDt()./*toGMTString()*/toString(), p.getGoods(), p.getSumPayed()); 
    	}
    }
    
/*    public int addPayment() {
    	Customer customer = new Customer();
    	customer.setName("Tim");
    	customer.setAddress("Zurich");
    	customer.setEmail("tim@gmail.com");
    	customer.setCcno("111");
    	customer.setCctype("222");
    	java.util.Date utilDate = new java.util.Date();
    	java.sql.Date maturity = new java.sql.Date(utilDate.getTime());
    	customer.setMaturity(maturity);
    	customerService.save(customer);
    	return customer.getId();
    }
    
    public void findAllPayments(){
        List<Merchant> list = merchantService.findAll();
        System.out.println("           name              charge");
        for(Merchant m: list){
            System.out.format("%1$25s     %2$4.1f  %n", 	  
    	       m.getName(), m.getCharge());
        }
    }*/
 }
