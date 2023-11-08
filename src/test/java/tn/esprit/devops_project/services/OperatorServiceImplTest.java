package tn.esprit.devops_project.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Transactional
class OperatorServiceImplTest {
    @Autowired
    private OperatorServiceImpl operatorService;

    @MockBean
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorRepository operatorRepository1;


    @Test
    void retrieveAllOperators1() {
        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(new Operator(1L, "John", "Doe", "password", new HashSet<>()));
        operatorList.add(new Operator(2L, "Jane", "Smith", "password", new HashSet<>()));
        when(operatorRepository.findAll()).thenReturn(operatorList);
        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();
        assertEquals(operatorList, retrievedOperators);
        verify(operatorRepository, times(1)).findAll();
    }


    @Test
    void retrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        operator.setFname("ali");
        operator.setLname("alienz");
        operator.setPassword("password");
        when(operatorRepository1.findById(operatorId)).thenReturn(Optional.of(operator));
        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);
        assertNotNull(retrievedOperator);
        assertEquals(operatorId, retrievedOperator.getIdOperateur());
        assertEquals("ali", retrievedOperator.getFname());
        assertEquals("alienz", retrievedOperator.getLname());
        assertEquals("password", retrievedOperator.getPassword());
    }



    @Test
    @Transactional
    void addOperator() {
        Operator operator = new Operator();
        operator.setFname("alienz");
        operator.setLname("alienz");
        operator.setPassword("password123");
        when(operatorRepository.save(any(Operator.class))).thenReturn(operator);
        Operator savedOperator = operatorService.addOperator(operator);
        verify(operatorRepository, times(1)).save(operator);
        assertNotNull(savedOperator);
        assertEquals("alienz", savedOperator.getFname());
        assertEquals("alienz", savedOperator.getLname());
        assertEquals("password123", savedOperator.getPassword());
    }
    @Test
    void addOperator1() {
        Operator operator = new Operator();

        operator.setFname("alienz");
        operator.setLname("alienz");
        operator.setPassword("password123");
        operatorRepository1.save(operator);
        assertNotNull(operator);
        assertEquals("alienz", operator.getFname());
        assertEquals("alienz", operator.getLname());
        assertEquals("password123", operator.getPassword());

    }



    @Test
    void deleteOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator(operatorId, "alienz", "alienz", "password", new HashSet<>());
        doNothing().when(operatorRepository).deleteById(operatorId);
        operatorService.deleteOperator(operatorId);
        verify(operatorRepository, times(1)).deleteById(operatorId);
    }
    @Test
    void deleteOperator1() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("ali");
        operator.setLname("alienz");
        operator.setPassword("password");
        operatorService.addOperator(operator);
        operatorService.deleteOperator(1L);

    }




    @Test
    void updateOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("ali");
        operator.setLname("alienz");
        operator.setPassword("password");
        when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));
        when(operatorRepository.save(any(Operator.class))).thenReturn(operator);
        Operator updatedOperator = operatorService.updateOperator(operator);
        assertNotNull(updatedOperator);
        assertEquals("ali", updatedOperator.getFname());
        assertEquals("alienz", updatedOperator.getLname());
        assertEquals("password", updatedOperator.getPassword());
        verify(operatorRepository, times(1)).save(operator);
    }
    @Test
    void updateOperator1() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("ali");
        operator.setLname("ali");
        operator.setPassword("password");
        operatorService.addOperator(operator);
        operator.setFname("alienz");
        operator.setLname("alienz");
        operator.setPassword("newpassword");
        operatorService.updateOperator(operator);
        assertNotNull(operator);
        assertEquals("alienz", operator.getFname());
        assertEquals("alienz", operator.getLname());
        assertEquals("newpassword", operator.getPassword());


    }



}
