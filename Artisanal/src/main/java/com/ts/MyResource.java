package com.ts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.dao.AgentDAO;
import com.ts.dao.CustomerDAO;
import com.ts.dao.OrderDetailsDAO;
import com.ts.dao.OrdersDAO;
import com.ts.dao.ProductDAO;
import com.ts.dao.wishlistDAO;
import com.ts.db.HibernateTemplate;
import com.ts.dto.Agent;
import com.ts.dto.Customer;
import com.ts.dto.OrderDetails;
import com.ts.dto.Orders;
import com.ts.dto.Product;
import com.ts.dto.wishlist;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void register(Customer customer) {
		System.out.println("Data Recieved in user Register : " + customer);
		CustomerDAO customerDao = new CustomerDAO();
		customerDao.register(customer);
	
	}
    @Path("registerAgent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void registerAgent(Agent agent) {
		System.out.println("Data Recieved in user Register : " + agent);
		AgentDAO agentDao = new AgentDAO();
		agentDao.register(agent);
	
	}
    @Path("updateCustomer")
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCustomer(Customer customer) {		
		System.out.println("Data Received in update : " + customer);
		CustomerDAO customerDao = new CustomerDAO();
		customerDao.updateCustomer(customer);		
		System.out.println("Customer Record Updated Successfully!!!");
    }
    @Path("updateProduct")
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProduct(Product product) {		
		System.out.println("Data Received in update : " + product);
		ProductDAO productDao = new ProductDAO();
		productDao.updateProduct(product);		
		System.out.println("Product Record Updated Successfully!!!");
    }
    
    @Path("getCustomerByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByUserPass(@PathParam("loginId") String loginId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+loginId+" "+password); 
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustomerByUserPass(loginId, password);
		return customer;
	}
 
    
    @Path("getAgentByUserPass/{loginId}/{password}")
   	@GET
   	@Produces(MediaType.APPLICATION_JSON)
   	public Agent getAgentByUserPass(@PathParam("loginId") String loginId,@PathParam("password") String password) {
   		System.out.println("Recieved path params: "+loginId+" "+password); 
   		AgentDAO agentDAO = new AgentDAO();
   		Agent agent = AgentDAO.getAgentByUserPass(loginId, password);
   		return agent;
   	}
    
    
    @Path("registerProduct")
   	@POST
   	@Consumes(MediaType.MULTIPART_FORM_DATA)
   	public void registerProduct(@FormDataParam("productName") String productName, @FormDataParam("price") Double price ,
   			@FormDataParam("description") String description,@FormDataParam("Image") InputStream fileInputStream,
   			@FormDataParam("Image") FormDataContentDisposition formDataContentDisposition,
   			@FormDataParam("Category") String category ,@FormDataParam("Quantity") int quantity,@FormDataParam("agentId") int agentId) throws IOException {
    		int read=0;
    		byte[] bytes=new byte[1024];
    		String path=this.getClass().getClassLoader().getResource("").getPath();
    		String pathArr[]=path.split("/WEB-INF/classes/");
    		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));
    		while((read=fileInputStream.read(bytes)) != -1) {
    			out.write(bytes,0,read);
    		}
    		out.flush();
    		out.close();
    		
    		Agent agent = new Agent();
    		agent.setAgentId(agentId);
    		
    		Product product = new Product();
    		product.setProName(productName);
    		product.setPrice(price);
    		product.setDescription(description);
    		product.setProImage(formDataContentDisposition.getFileName());
    		product.setCatName(category);
    		product.setQuantity(quantity);
    		product.setAgent(agent);
    		ProductDAO productDao = new ProductDAO();
    		productDao.addProduct(product);
    }
    
    @Path("getProducts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
    	ProductDAO productDao = new ProductDAO();
    	List<Product> productList = productDao.getAllProducts();
    	
    	return productList;
    	
    } 
    
    @Path("getProductsByCat/{pots}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductsByCategory(@PathParam("pots") String catName) {
    	ProductDAO productDao = new ProductDAO();
    	List<Product> productList = productDao.getAllProductsByCat(catName);
    	
    	return productList;
    	
    } 
    
    @Path("confirmOrder")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	public String confirmOrders(@FormDataParam("deliveryAdress") String deliveryAddress ,@FormDataParam("orderedDate") String orderedDate ,
			@FormDataParam("phoneNo") long phoneNo ,@FormDataParam("totalPrice") int totalPrice ,
			@FormDataParam("customerId") int customerId,@FormDataParam("email") String mail ,@FormDataParam("items") String str ) throws ParseException, JsonParseException, JsonMappingException, IOException,MessagingException {
						
    		Orders order = null;
    		
    		ObjectMapper Mapper = new ObjectMapper();
			order = new Orders();
			order.setDeliveryAddress(deliveryAddress);
			order.setOrderedDate(orderedDate);
			order.setPhoneNo(phoneNo);
			order.setTotalPrice(totalPrice);
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			order.setCustomer(customer);
			OrdersDAO dao = new OrdersDAO();
			dao.registerOrder(order);
			
			List<OrderDetails> ordDetails = new ArrayList<OrderDetails>();
			ObjectMapper obj = new ObjectMapper();
			
			JSONParser json = new JSONParser();
			JSONArray arr = (JSONArray)json.parse(str);
			System.out.println("Inside order Details..." + arr.size());
			for(int i = 0; i < arr.size(); i++){
				JSONObject jsonData = (JSONObject)arr.get(i);
				Product product = obj.readValue(jsonData.toString(), Product.class);
				OrderDetails orderDetails = new OrderDetails();
				
				int proId = product.getProId();
				Product product1 = new Product();
				product1.setProId(product.getProId());
				
				Product reqProduct = HibernateTemplate.getObjectById(proId);
				int oldquantity = reqProduct.getQuantity();
				//System.out.println(oldquantity - product.getQuantity());
				reqProduct.setQuantity(oldquantity - product.getQuantity());
				
				orderDetails.setOrders(order);
				orderDetails.setProduct(product1);
				orderDetails.setQuantity(product.getQuantity());
//				product1.setQuantity(product1.getQuantity() - product.getQuantity());
				orderDetails.setUnitPrice(product.getPrice());
				OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
				orderDetailsDao.register(orderDetails);
				
				ProductDAO productDao = new ProductDAO();
				productDao.updateProduct(reqProduct);	
				
			}
			
			
		System.out.println("mail method has been excuting  @ 1");
		String subject="Order Placed";
		String body="Your order is placed successfully which is ordered on " + orderedDate + ".\n It will be delivered to " + deliveryAddress
				+ "within 4 to 5 working days....\nThanks for shopping in our Website.\n Happy Shopping:)";
		String email=mail;
		String host = "smtp.gmail.com";
		String from = "artisanalStore6@gmail.com";
		String pass = "Angular08";

		Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true"); // added this line
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			String[] to = {email}; // added this line

			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses

			for( int i=0; i < to.length; i++ ) 
			{ 
				// changed from a while loop
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i=0; i < toAddress.length; i++)
			{ 
				// changed from a while loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
			System.out.println("mail method has been excuting");
			return "successful";
	}
    @Path("addtowishlist")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addtowishlist(@FormDataParam("productId") int productId,@FormDataParam("productName") String productName, @FormDataParam("price") Double price ,
			@FormDataParam("description") String description,@FormDataParam("Image") String proimage,
			@FormDataParam("Category") String category ,@FormDataParam("Quantity") int quantity, @FormDataParam("customerId") int customerId) throws IOException {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		System.out.println("wishlist data received!!");
		wishlist product = new wishlist();
		product.setProId(productId);
		product.setProName(productName);
		product.setPrice(price);
		product.setDescription(description);
		product.setProImage(proimage);
		product.setCatName(category);
		product.setQuantity(quantity);
		product.setCustomer(customer);
		wishlistDAO wishDao = new wishlistDAO();
		wishDao.addfavorite(product);
				
	}
    @Path("getFavorites")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<wishlist> getFavorites() {
		wishlistDAO wishlistdao = new wishlistDAO();
		List<wishlist> favoriteList = wishlistdao.getAllFavorities();
		System.out.println("get favorities called!!!!");
		return favoriteList;
	}
	 	@Path("getOrders/{customerId}")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<List<OrderDetails>> getOrders(@PathParam("customerId") int customerId) {
	 		
	 		CustomerDAO customerDao = new CustomerDAO();
	 		Customer customer = customerDao.getAllOrdersByCustId(customerId);
		 	List<Orders> ordersList = customer.getOrdersList();
		 	
//		 	System.out.println("In orders..." + ordersList.get(0).getOrderId());
		 	List<List<OrderDetails>> orderDetailsList = new ArrayList<List<OrderDetails>>();
		 	
		 	for(int i = 0; i < ordersList.size(); i++){
		 		orderDetailsList.add(ordersList.get(i).getOrderDetailsList());
		 	}
		 	return orderDetailsList;   	
	 	} 
	 	@Path("cancelOrder/{id}")
		@DELETE
	    @Produces(MediaType.APPLICATION_JSON)
	    public void cancelOrder(@PathParam("id") int id) {		
			System.out.println("Data Received in delete : ");
			OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
			OrderDetails orderDetails = orderDetailsDao.getOrderId(id);
			System.out.println(orderDetails.getOrders().getOrderId()); 
			
			int orderId = orderDetails.getOrders().getOrderId();
			orderDetailsDao.deleteObjects(orderId);
			
			Orders orders = orderDetails.getOrders();
			OrdersDAO ordersDao = new OrdersDAO();
			
			ordersDao.delete(orders);
			System.out.println("Orders Record Deleted Successfully!!!");
//			EmployeeDaoH empDAOH = new EmployeeDaoH();
//			Employee employee = empDAOH.getEmployee(empId);		
//			empDAOH.deleteEmp(employee);
//			System.out.println("Employee Record Deleted Successfully!!!");
	    }
  
}
