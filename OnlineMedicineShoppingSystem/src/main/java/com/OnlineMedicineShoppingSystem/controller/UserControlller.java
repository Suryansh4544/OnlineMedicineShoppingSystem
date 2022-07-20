package com.OnlineMedicineShoppingSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineMedicineShoppingSystem.dao.CartService;
import com.OnlineMedicineShoppingSystem.dao.OrderService;
import com.OnlineMedicineShoppingSystem.dao.ProductService;
import com.OnlineMedicineShoppingSystem.dao.UserService;
import com.OnlineMedicineShoppingSystem.model.Cart;
import com.OnlineMedicineShoppingSystem.model.Order;
import com.OnlineMedicineShoppingSystem.model.Products;
import com.OnlineMedicineShoppingSystem.model.Users;

@RestController
@RequestMapping("/user")
public class UserControlller {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productservice;

    @Autowired
    CartService cartservice;

    @Autowired
    OrderService orderService;

    static boolean login_status = false;
    static Users logedin_user = null;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid Users u){
        try{
            Users user  = userService.fetchUserById(u.getUser_id());
            if(user!=null){
                if(user.getPassword().equals(u.getPassword())){
                    login_status = true;
                    logedin_user = user;
                    return new ResponseEntity<String>("Login Successfull",HttpStatus.ACCEPTED);
                }
            }
            return new ResponseEntity<String>("Login Unsuccessfull",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody @Valid Users u){
        try{
            if(login_status==true)
            return new ResponseEntity<String>("Logout to register",HttpStatus.CONFLICT);

            if(userService.saveUser(u))
            return new ResponseEntity<String>("Register Successfull",HttpStatus.ACCEPTED);

            return new ResponseEntity<String>("Register UnSuccessfull, User id already exsist",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/viewproducts")
    public ResponseEntity<Object> products(){
        try{
        return new ResponseEntity<>(productservice.fetchProducts(),HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @PostMapping("/addtocart")
    public ResponseEntity<String> addtocart( @RequestBody Map<String,?> json){
        try{
        Cart c;
            if(json.containsKey("product_id") && json.containsKey("quantity")){
                if(login_status!=true){return new ResponseEntity<String>("Login to add to cart",HttpStatus.CONFLICT);}
                c = new Cart(logedin_user.getUser_id(),(int)json.get("product_id"),(int)json.get("quantity"));
                if(productservice.fetchProductById(c.getProduct_id())==null){return new ResponseEntity<String>("Product id not present",HttpStatus.CONFLICT);}
                if(cartservice.saveCart(c)){return new ResponseEntity<String>("Added Successfully to Cart",HttpStatus.OK);}
                return new ResponseEntity<String>("Item already in the cart, Please update it",HttpStatus.CONFLICT);
            }
            return new ResponseEntity<String>("Invalid Input",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);}
    }
    @PutMapping("/updatetocart")
    public ResponseEntity<String> updatetocart(@Valid @RequestBody Map<String,?> json){
        try{
        Cart c;
            if(json.containsKey("product_id") && json.containsKey("quantity")){
                c = new Cart(logedin_user.getUser_id(),(int)json.get("product_id"),(int)json.get("quantity"));
                if(productservice.fetchProductById(c.getProduct_id())==null){return new ResponseEntity<String>("Product id not present",HttpStatus.CONFLICT);}
                if(login_status!=true){return new ResponseEntity<String>("Login to Update cart",HttpStatus.CONFLICT);}
                if(cartservice.updateCart(c,c.getUser_id(),c.getProduct_id())){return new ResponseEntity<String>("Cart Successfully updated",HttpStatus.OK);}
            }   
            return new ResponseEntity<String>("Invalid Input",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/viewcart")
    public ResponseEntity<Object> viewcart(){
        try{
            System.out.println(login_status);
            if(login_status==false){return new ResponseEntity<>("Login to view cart",HttpStatus.CONFLICT);}
            return new ResponseEntity<>(cartservice.fetchCartByUid(logedin_user.getUser_id()),HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/pay")
    public ResponseEntity<String> pay(){
        try{
            if(login_status==false){new ResponseEntity<>("Login to pay",HttpStatus.CONFLICT);}
            List<Cart> items = cartservice.fetchCartByUid(logedin_user.getUser_id());
            int cost=0;
            for(int i=0;i<items.size();i++){
                Cart curritem = items.get(i);
                Products currproduct = productservice.fetchProductById(curritem.getProduct_id());
                cost+=currproduct.getPrize()*curritem.getQuantity();
                System.out.println( curritem.getProduct_id());
                System.out.println(logedin_user.getUser_id());
                cartservice.deleteCart(logedin_user.getUser_id(), curritem.getProduct_id());
                Order currOrder = new Order(logedin_user.getUser_id(), curritem.getProduct_id(), curritem.getQuantity(), cost);
                System.out.println(currOrder.toString());
                orderService.saveOrder(currOrder);
            }
            return new ResponseEntity<>("Pay "+cost+" By Credit Card Online",HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/vieworders")
    public ResponseEntity<Object> vieworder(){
        try{
            if(login_status==false){return new ResponseEntity<>("Login to view orders",HttpStatus.CONFLICT);}
            return new ResponseEntity<>(orderService.fetchOrderByUid(logedin_user.getUser_id()),HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        if(login_status!=false){
            login_status = false;
            logedin_user = null;
            return new ResponseEntity<String>("Logout Succesfull",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login to logout",HttpStatus.CONFLICT);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName =   error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}



