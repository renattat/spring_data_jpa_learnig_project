package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Employee;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@DataJpaTest - for using h2 database
@SpringBootTest
public class EmployeeRepositoryTests {

    @Autowired
    public EmployeeRepository employeeRepository;

    private Employee employee;


    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Renat")
                .lastName("Sim")
                .email("renat@gmail.com")
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // Junit test for get all employees operation
    @DisplayName("Junit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("cena")
                .email("cena@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // Junit test for get employee by id operation
    @DisplayName("unit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit test for get employee by email operation
    @DisplayName("Junit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();


        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit test for update employee operation
    @DisplayName("Junit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdatedEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("john@gmail.com");
        savedEmployee.setFirstName("John");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("john@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("John");
    }

    // Junit test for delete Employee operation
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
//        employeeRepository.delete(employee);
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    // Junit test for custom query using JPQL with index
    @DisplayName("Junit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = "Renat";
        String lastName = "Sim";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        System.out.println(savedEmployee);
    }

    // Junit test for custom query using JPQL with Named params
    @DisplayName("Junit test for custom query using JPQL with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

        String firstName = "Renat";
        String lastName = "Sim";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        System.out.println(savedEmployee);
    }

    // Junit test for custom query using native SQL with index
    @DisplayName("Junit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

//        String firstName = "Renat";
//        String lastName = "Sim";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQL(
                employee.getFirstName(),
                employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        System.out.println(savedEmployee);
    }

    // Junit test for custom query using native SQL with named params
    @DisplayName("Junit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Renat")
//                .lastName("Sim")
//                .email("renat@gmail.com")
//                .build();
        employeeRepository.save(employee);

//        String firstName = "Renat";
//        String lastName = "Sim";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(
                employee.getFirstName(),
                employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        System.out.println(savedEmployee);
    }
}