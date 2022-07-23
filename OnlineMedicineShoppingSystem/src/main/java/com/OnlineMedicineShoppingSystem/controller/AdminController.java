package com.OnlineMedicineShoppingSystem.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineMedicineShoppingSystem.dao.AdminService;
import com.OnlineMedicineShoppingSystem.dao.CartService;
import com.OnlineMedicineShoppingSystem.dao.OrderService;
import com.OnlineMedicineShoppingSystem.dao.ProductService;
import com.OnlineMedicineShoppingSystem.dao.UserService;
import com.OnlineMedicineShoppingSystem.model.Admin;
import com.OnlineMedicineShoppingSystem.model.Products;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    ProductService productservice;

    @Autowired
    CartService cartservice;

    @Autowired
    OrderService orderService;

    static boolean login_status = false;
    static Admin logedin_admin = null;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid Admin a){
        try{
            Admin admin  = adminService.fetchAdminById(a.getAdmin_id());
            if(admin!=null){
                if(admin.getPassword().equals(a.getPassword())){
                    login_status = true;
                    logedin_admin = admin;
                    return new ResponseEntity<String>("Login Successfull",HttpStatus.ACCEPTED);
                }
            }
            return new ResponseEntity<String>("Login Unsuccessfull",HttpStatus.CONFLICT);
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

    @PostMapping("/createproduct")
    public ResponseEntity<Object> createproduct(@RequestBody @Valid Products p){
        try{
            if(productservice.saveProduct(p)){return new ResponseEntity<>("Product Created Succesfully",HttpStatus.OK);}
            return new ResponseEntity<>("Product Created UnSuccesfully",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<Object> updateproduct(@RequestBody @Valid Products p){
        try{
            if(productservice.updateProduct(p,p.getProduct_id())){return new ResponseEntity<>("Product Updated Succesfully",HttpStatus.OK);}
            return new ResponseEntity<>("Product Updated UnSuccesfully",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @DeleteMapping("/deleteproduct/{pid}")
    public ResponseEntity<Object> deleteproduct(@PathVariable int pid){
        try{
            Products product=productservice.deleteProduct(pid);
            if(product!=null){return new ResponseEntity<>("Product Deleted Succesfully {Product id:"+product.getProduct_id()+" prize:"+product.getPrize()+"}" ,HttpStatus.OK);}
            return new ResponseEntity<>("Product Updated UnSuccesfully",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/vieworders")
    public ResponseEntity<Object> vieworders(){
        try{
        return new ResponseEntity<>(orderService.fetchOrder(),HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }
    @GetMapping("/viewuser")
    public ResponseEntity<Object> viewuser(){
        try{
        return new ResponseEntity<>(userService.fetchUsers(),HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);}
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        if(login_status!=false){
            login_status = false;
            logedin_admin = null;
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
    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody @Valid Admin a){
        try{
            if(login_status==true)
            return new ResponseEntity<String>("Logout to register",HttpStatus.CONFLICT);

            if(adminService.saveAdmin(a))
            return new ResponseEntity<String>("Register Successfull",HttpStatus.ACCEPTED);

            return new ResponseEntity<String>("Register UnSuccessfull, Admin id already exsist",HttpStatus.CONFLICT);
        }
        catch(Exception e){return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);}
    }
}
