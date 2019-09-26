/**
 * 
 */
package com.wipro.promotion.controller;

import java.io.IOException;
import java.util.Arrays;
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

import com.wipro.promotion.model.Promotion;
import com.wipro.promotion.service.PromotionService;

/**
 * @author SPadakula
 *
 */
@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping
    public ResponseEntity<List<Promotion>> getPromotions(@RequestParam(name="productId", required=false) Long productId) {
        return ResponseEntity.ok(service.getAll(productId));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Promotion>> save(@RequestBody @Valid List<Promotion> promotion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(promotion));
    }
    
    @PostMapping
    public ResponseEntity<Promotion> save(@RequestBody @Valid Promotion promotion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(promotion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> save(@PathVariable(required = true) Long id,
            @RequestBody Promotion promotion)
            throws ServletRequestBindingException, MethodArgumentNotValidException {
        //Promotion details = service.getById(id).get();
        promotion.setId(id);
        return ResponseEntity.ok(service.save(promotion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> findById(@PathVariable(required = true) Long id)
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
            return new ResponseEntity<>("promotion not found", httpHeaders, HttpStatus.NOT_FOUND);
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
