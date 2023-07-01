package com.ellisonalves.thehotel.rest.endpoints;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
/* 
    private final GuestService guestService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(GuestService guestService, CustomerMapper customerMapper) {
        this.guestService = guestService;
        this.customerMapper = customerMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerDTO find(@PathVariable("id") UUID id) {
        todo return customerMapper.toDTO(guestService.findOne(id).get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerListDTO find() {
        return customerMapper.toCustomerListDTO(guestService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO saved = customerMapper.toDTO(guestService.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.created(URI.create("/customers/" + saved.getId())).body(saved);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerMapper.toDTO(guestService.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.ok(updated);
    }
    */

}
