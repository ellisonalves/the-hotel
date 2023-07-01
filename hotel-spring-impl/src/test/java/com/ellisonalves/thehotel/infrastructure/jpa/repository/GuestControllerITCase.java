package com.ellisonalves.thehotel.infrastructure.jpa.repository;

public class GuestControllerITCase extends AbstractIntegrationTestController {

        // @Autowired
        // private GuestService guestService;

        // @Autowired
        // private CustomerMapper customerMapper;

        // @Test
        // public void shouldReadAllCustomers() throws Exception {
        // mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
        // .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        // .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id",
        // CoreMatchers.is(1)));
        // }

        // @Test
        // public void shouldReadOneCustomerForAGivenId() throws Exception {
        // mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
        // .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        // .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)));
        // }

        // @Test
        // public void shouldPersistAGivenCustomer() throws Exception {
        // CustomerDTO customerDTO = new CustomerDTO();
        // customerDTO.setDocumentNumber("123123123");
        // customerDTO.setEmail("email@email.com");
        // customerDTO.setGender(GenderType.MALE);
        // customerDTO.setName("Rocky");
        // customerDTO.setNationality("American");
        // customerDTO.setPhone("123123123");
        // customerDTO.setAddress("My Address");

        // MockHttpServletRequestBuilder post =
        // MockMvcRequestBuilders.post("/customers");
        // post
        // .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        // .contentType(MediaType.APPLICATION_JSON_UTF8)
        // .content(toJson(customerDTO));

        // mockMvc.perform(post)
        // .andExpect(MockMvcResultMatchers.status().isCreated())
        // .andExpect(MockMvcResultMatchers.jsonPath("$.id",
        // CoreMatchers.notNullValue()));
        // }

        // @Test
        // public void shouldUpdateAGivenCustomer() throws Exception {
        // CustomerDTO customerDTO =
        // customerMapper.toDTO(guestService.findOne(1l).get());
        // customerDTO.setNationality("GERMAN");

        // MockHttpServletRequestBuilder put = MockMvcRequestBuilders.put("/customers");
        // put
        // .accept(MediaType.APPLICATION_JSON_VALUE)
        // .contentType(MediaType.APPLICATION_JSON_VALUE)
        // .content(toJson(customerDTO));

        // mockMvc.perform(put)
        // .andExpect(MockMvcResultMatchers.status().isOk())
        // .andExpect(MockMvcResultMatchers.jsonPath("$.nationality",
        // CoreMatchers.equalTo("GERMAN")));
        // }

}