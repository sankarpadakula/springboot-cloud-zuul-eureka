/**
 * 
 */
package com.bankaccount.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wipro.digital.price.controller.PriceController;

/**
 * @author USET
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PriceController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	/**
	 * Test method for {@link com.wipro.digital.price.controller.PriceController#getHomePage()}.
	 */
	@Test
	public final void testGetHomePage() {
		String expected = "Hello!";
		try {
			mvc.perform(get("/home").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", is(expected)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Ignore
	public final void testfindAccountById() throws Exception {
		mvc.perform(get("/accounts/{1}", 1).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Mike"))
		.andExpect(jsonPath("$.balance").value(12828));
	}

}
