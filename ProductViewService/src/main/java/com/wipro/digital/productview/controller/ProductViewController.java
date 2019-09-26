/**
 * 
 */
package com.wipro.digital.productview.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.wipro.digital.productview.model.Inventory;
import com.wipro.digital.productview.model.Price;
import com.wipro.digital.productview.model.Product;
import com.wipro.digital.productview.model.Promotion;

/**
 * @author USET
 *
 */
@RestController
@RefreshScope
public class ProductViewController {
	
    @Autowired
    RestTemplate restTemplate;
    
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@Value("${basepath.save : /saveprices}")
	private String savePricesBasePath;
	
	@Value("${basepath.getproductpricebyid : /getprice/}")
	private String getPricesBasePath;
	
	@PostMapping("/")
    public Product save(@RequestBody @Valid Product product) {
	    product  = saveProduct(product);
	    savePrice(product.getId(), product.getPrice());
	    saveInventory(product.getId(), product.getInventory());
	    savePromotion(product.getId(), product.getPromotions());
	    
	       
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("product-price", false);
        String baseURL = instanceInfo.getHomePageUrl();
        System.out.println("Base URL" + baseURL);
        System.out.println("savePricesBasePath" + savePricesBasePath);
        baseURL = baseURL + savePricesBasePath;
        return product;
    }
	
	private void savePromotion(Integer integer, List<Promotion> list) {
	    //restTemplate.postForObject("https://product-service/promotion", list);
        
    }

    private void saveInventory(Integer integer, Inventory inventory) {
        restTemplate.postForObject("https://product-service/inventory", inventory, Inventory.class);
        
    }

    private void savePrice(Integer integer, Price price) {
        restTemplate.postForObject("https://product-service/price", price, Price.class);
        
    }

    private Product saveProduct(Product product) {
        product = restTemplate.postForObject("https://product-service/product", product, Product.class);
        return product;
        
    }

    @GetMapping("/")
	public String getAllProducts() {
        List<Inventory> skus = restTemplate.getForEntity("https://inventory-service/skus?productId="+productId, List.class).getBody();
        
        
		RestTemplate restTemplate = restTemplateBuilder.build();
		//InstanceInfo productInfo = eurekaClient.getNextServerFromEureka("product", false);
		InstanceInfo priceInfo = eurekaClient.getNextServerFromEureka("product-price", false);
		InstanceInfo inventoryInfo = eurekaClient.getNextServerFromEureka("product-inventory", false);
		String baseURL = priceInfo.getHomePageUrl();
		System.out.println("Base URL" + baseURL);
		System.out.println("savePricesBasePath" + savePricesBasePath);
		baseURL = baseURL + savePricesBasePath;
		return restTemplate.getForObject(baseURL, String.class);
	}
	
	@GetMapping("/{id}")
	public String invokePriceService(@PathVariable(required = true) Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("product-price", false);
		String baseURL = instanceInfo.getHomePageUrl();
		System.out.println("Base URL" + baseURL);
		System.out.println("getPricesBasePath" + getPricesBasePath);
		baseURL = baseURL + getPricesBasePath +id;
		return restTemplate.getForObject(baseURL, String.class);
	}
}
