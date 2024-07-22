package pe.devsu.development.clientservicetest.adapters.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.clientservicetest.dto.Customer;
import pe.devsu.development.clientservicetest.service.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CustomerResource {
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final CustomerService customerService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Customer>> findAll() {
        try {
            List<Customer> customerList = customerService.findAll();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> findById(@PathVariable Integer id) {
        try {
            Customer foundCustomer = customerService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>("success", "", foundCustomer));
        } catch (Exception e) {
            log.error("Error retrieving customer with id {}: {}", id, e.getMessage(), e);
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<ApiResponse<Customer>> save(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.save(customer);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("success", "", savedCustomer));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> update(@PathVariable Integer id,
            @RequestBody Customer customer) {
        try {
            customer.setId(id); // Ensure the ID is set for the update
            Customer updatedCustomer = customerService.update(customer);
            return ResponseEntity.ok(new ApiResponse<>("success", "Customer updated successfully", updatedCustomer));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> partialUpdate(@PathVariable Integer id,
            @RequestBody Customer customer) {
        try {
            customer.setId(id); // Ensure the ID is set for the update
            Customer updatedCustomer = customerService.partialUpdate(customer);
            return ResponseEntity
                    .ok(new ApiResponse<>("success", "Customer partially updated successfully", updatedCustomer));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            Customer customer = new Customer();
            customer.setId(id); // Set the ID for deletion
            customerService.delete(customer);
            return ResponseEntity.ok(new ApiResponse<>("success", "Customer deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "Internal server error", null));
        }
    }
}
