package com.ellisonalves.thehotel.interfaces.endpoints;

import com.ellisonalves.thehotel.domain.services.CustomerService;
import com.ellisonalves.thehotel.domain.types.GenderType;
import com.ellisonalves.thehotel.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.interfaces.mapper.CustomerMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CustomerControllerITCase extends AbstractIntegrationTestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void shouldReadAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id", CoreMatchers.is(1)));
    }

    @Test
    public void shouldReadOneCustomerForAGivenId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)));
    }

    @Test
    public void shouldPersistAGivenCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setDocumentNumber("123123123");
        customerDTO.setEmail("email@email.com");
        customerDTO.setGender(GenderType.MALE);
        customerDTO.setName("Rocky");
        customerDTO.setNationality("American");
        customerDTO.setPhone("123123123");
        customerDTO.setAddress("My Address");

        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/customers");
        post
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(customerDTO));

        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()));
    }

    @Test
    public void shouldUpdateAGivenCustomer() throws Exception {
        CustomerDTO customerDTO = customerMapper.toDTO(customerService.findOne(1l));
        customerDTO.setNationality("GERMAN");

        MockHttpServletRequestBuilder put = MockMvcRequestBuilders.put("/customers");
        put
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(customerDTO));

        mockMvc.perform(put)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nationality", CoreMatchers.equalTo("GERMAN")));
    }

}