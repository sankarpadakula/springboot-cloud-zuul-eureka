/**
 * 
 */
package com.wipro.inventory.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.inventory.model.InventoryDetails;
import com.wipro.inventory.service.InventoryService;

/**
 * @author SPadakula
 *
 */
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping
    public ResponseEntity<List<InventoryDetails>> getInventories() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<InventoryDetails> save(@RequestBody @Valid InventoryDetails inventory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDetails> save(@PathVariable(required = true) Long id,
            @RequestBody InventoryDetails inventory)
            throws ServletRequestBindingException, MethodArgumentNotValidException {
        InventoryDetails details = service.getById(id).get();
        details.setId(id);
        if (inventory.getProductId() != null)
            details.setProductId(inventory.getProductId());
        if (inventory.getCount() != null)
            details.setCount(inventory.getCount());
        if (inventory.getDescription() != null) {
            if (inventory.getDescription().length() > 500)
                throw new MethodArgumentNotValidException(null, null);
            details.setDescription(inventory.getDescription());
        }
        if (inventory.getName() != null) {
            if (inventory.getName().length() > 100)
                throw new MethodArgumentNotValidException(null, null);
            details.setName(inventory.getName());
        }
        if (inventory.getPrice() != null)
            details.setPrice(inventory.getPrice());
        return ResponseEntity.ok(service.save(details));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDetails> findById(@PathVariable(required = true) Long id)
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
            return new ResponseEntity<>("Inventory not found", httpHeaders, HttpStatus.NOT_FOUND);
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
