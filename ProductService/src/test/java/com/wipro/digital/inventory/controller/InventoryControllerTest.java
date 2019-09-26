/**
 * 
 */
package com.wipro.digital.inventory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author SPADAKULA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InventoryControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public final void testfindAccountById() throws Exception {
		mvc.perform(get("/inventory/{1}", 1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		//.andExpect(jsonPath("$.productId").value(1))
		//.andExpect(jsonPath("$.color").value("black"));
	}

}
