package com.supinfo.proj.retailr.apistore.service;

import com.supinfo.proj.retailr.apistore.data.entity.Customer;
import com.supinfo.proj.retailr.apistore.data.entity.User;
import com.supinfo.proj.retailr.apistore.data.repository.CustomerRepository;
import com.supinfo.proj.retailr.apistore.service.domain.CustomerProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerProfileService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    public void registerCustomer(CustomerProfile customerProfile) throws Exception {
        if (this.customerRepository.existsByEmail(customerProfile.getEmail())) {
            throw new Exception("an account is already linked to that email address");
        }
        if (this.userDetailService.userExists(customerProfile.getUsername())) {
            throw new Exception("username already exists");
        }
        if (customerProfile.getPassword().equals(customerProfile.getConfirmPassword())) {
            logger.info(customerProfile.toString());
            Customer customer = new Customer.Builder()
                    .withFirstName(customerProfile.getFirstName())
                    .withLastName(customerProfile.getLastName())
                    .withEmail(customerProfile.getEmail())
                    .withAddress(customerProfile.getAddress())
                    .withPhoneNumber(customerProfile.getPhoneNumber())
                    .build();

            User user = new User.Builder()
                    .withUsername(customerProfile.getUsername())
                    .withPassword(customerProfile.getPassword())
                    .withRole("customer")
                    .withCustomer(customer)
                    .withStaff(null)
                    .build();

            try {
                //validation occurs at the model level too (through validation annotations)
                this.customerRepository.save(customer);
                this.userDetailService.createUser(user);
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Passwords are null or do not match");
        }
    }

    public void deleteCustomerAccount(String username) throws Exception {
        if (!this.userDetailService.userExists(username)) {
            throw new Exception("username doesn't exist");
        } else {
            User user = (User)this.userDetailService.loadUserByUsername(username);
            this.userDetailService.deleteUser(user.getUsername());
            this.customerRepository.delete(user.getCustomer());
        }
    }

    public void editCustomerAccount(CustomerProfile customerProfile) throws Exception {

    }
}
