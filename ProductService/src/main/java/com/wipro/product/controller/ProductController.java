/**
 * 
 */
package com.wipro.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.product.model.Product;
import com.wipro.product.service.ProductService;

/**
 * @author SPadakula
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name="productId", required=false) Long productId) {
        return ResponseEntity.ok(service.getAll(productId));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> save(@PathVariable(required = true) Long id,
            @RequestBody Product product)
            throws ServletRequestBindingException, MethodArgumentNotValidException {
        Product details = service.getById(id).get();
        details.setId(id);
        return ResponseEntity.ok(service.save(details));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(required = true) Long id)
            throws ServletRequestBindingException {
        return ResponseEntity.ok(service.getById(id).get());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(required = true) Long id) throws ServletRequestBindingException {
        service.delete(id);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public ResponseEntity<Object> handle404(ServletRequestBindingException ex, HttpServletResponse res)
            throws IOException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
            return new ResponseEntity<>("product not found", httpHeaders, HttpStatus.NOT_FOUND);
        } catch (Exception e) {

        }
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handle400(MethodArgumentNotValidException ex, HttpServletResponse res)
            throws IOException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), httpHeaders,
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

        }
        return null;

    }

}
