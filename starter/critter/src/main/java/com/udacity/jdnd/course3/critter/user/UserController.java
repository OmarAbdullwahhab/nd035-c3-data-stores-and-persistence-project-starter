package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer c = new Customer();
        customerDTO.toCustomer(c);
        c = this.customerService.save(c);
        customerDTO.fromCustomer(c);
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> customers =  this.customerService.findAll();
        List<CustomerDTO> dtos = customers.stream().map(x->{
            CustomerDTO dto = new CustomerDTO();
            List<Pet> pets = this.petService.findAllByOwnerId(x.getId());
            x.setPets(pets);
            dto.fromCustomer(x);


            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        CustomerDTO dto = new CustomerDTO();
        Customer c = this.petService.getOwnerByPetId(petId);
        if(c != null)
        {

            c.setPets(this.petService.findAllByOwnerId(c.getId()));
        }
        dto.fromCustomer(c);
        return dto;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employeeDTO.toEmployee(employee);
        employee = this.employeeService.save(employee);
        employeeDTO.fromEmployee(employee);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeDTO dto = new EmployeeDTO();
        Employee e = this.employeeService.findById(employeeId);
        dto.fromEmployee(e);
        return dto;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = this.employeeService.findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        this.employeeService.save(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek serviceDate = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        List<Employee> empls = this.employeeService.findAvailableEmployeesForSkills(skills,serviceDate);
        List<EmployeeDTO> dtos = empls.stream().map(x->{
            EmployeeDTO dto = new EmployeeDTO();
            dto.fromEmployee(x);
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

}
