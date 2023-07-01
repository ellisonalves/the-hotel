package com.ellisonalves.thehotel.infrastructure.jpa.repository;

public class RoomControllerITCase extends AbstractIntegrationTestController {

        // @Test
        // public void shouldReadAllRooms() throws Exception {
        //         mockMvc.perform(MockMvcRequestBuilders.get("/rooms"))
        //                         .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        //                         .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[0].doorNumber",
        //                                         CoreMatchers.is(100)));
        // }

        // @Test
        // public void shouldFindOneRoomForAGivenId() throws Exception {
        //         mockMvc.perform(MockMvcRequestBuilders.get("/rooms/101"))
        //                         .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        //                         .andExpect(MockMvcResultMatchers.jsonPath("$.doorNumber", CoreMatchers.is(101)));
        // }

        // @Test
        // public void shouldCreateANewRoom() throws Exception {
        //         CreateRoomView roomDTO = new CreateRoomView();
        //         roomDTO.setDoorNumber(1000);
        //         roomDTO.setPricePerDay(new BigDecimal("35.40"));
        //         roomDTO.setType(RoomType.STANDARD);

        //         MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/rooms");
        //         post
        //                         .accept(MediaType.APPLICATION_JSON_VALUE)
        //                         .contentType(MediaType.APPLICATION_JSON_VALUE)
        //                         .content(toJson(roomDTO));

        //         mockMvc.perform(post)
        //                         .andExpect(MockMvcResultMatchers.status().isCreated())
        //                         .andExpect(MockMvcResultMatchers.jsonPath("$.doorNumber", CoreMatchers.notNullValue()));
        // }

        // @Test
        // public void shouldUpdateAnExistingRoom() throws Exception {
        // RoomDTO roomtDTO =
        // roomMapper.toDTO(roomService.findOne(UUID.randomUUID()).get());
        // roomtDTO.setType(RoomType.PRESIDENT);
        // roomtDTO.setPricePerDay(new BigDecimal("330.40"));

        // MockHttpServletRequestBuilder put = MockMvcRequestBuilders.put("/rooms");
        // put
        // .accept(MediaType.APPLICATION_JSON_VALUE)
        // .contentType(MediaType.APPLICATION_JSON_VALUE)
        // .content(toJson(roomtDTO));

        // mockMvc.perform(put)
        // .andExpect(MockMvcResultMatchers.status().isOk())
        // .andExpect(MockMvcResultMatchers.jsonPath("$.type",
        // CoreMatchers.equalTo("PRESIDENT")))
        // .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay",
        // CoreMatchers.is("330.40")));
        // }
}