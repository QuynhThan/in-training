package com.ms.training.application.service.Impl;

import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.dto.training.AccountDTO;
import com.ms.training.application.service.AccountService;
import com.ms.training.domain.entities.training.Account;
import com.ms.training.domain.entities.training.Employee;
import com.ms.training.domain.entities.training.Lecturer;
import com.ms.training.domain.entities.training.Student;
import com.ms.training.domain.mappers.AccountMapper;
import com.ms.training.domain.mappers.MaintenanceMapper;
import com.ms.training.domain.repositories.AccountRepository;
import com.ms.training.domain.repositories.EmployeeRepository;
import com.ms.training.domain.repositories.LecturerRepository;
import com.ms.training.domain.repositories.StudentRepository;
import com.ms.training.domain.utils.SecurityStorage;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private static final String STAFF = "staff";

    private static final String EMPLOYEE = "employee";
    private static final String STUDENT = "student";
    private static final String LECTURER = "lecturer";
    private static final String CUSTOMER = "customer";
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private StaffRepository staffRepository;


    @Override
    @Transactional
    public AccountDTO addAccount(AccountDTO dto) {
        Account account = AccountMapper.INSTANCE.toAccount(dto);
//        if(Objects.nonNull(dto.getCustomerDTO())){
//            Customer customer = customerRepository.save(CustomerMapper.INSTANCE.toCustomer(dto.getCustomerDTO()));
//            account.setCustomerId(customer.getCustomerId());
//        } else if (Objects.nonNull(dto.getStaffDTO())){
//            Staff staff = staffRepository.save(StaffMapper.INSTANCE.toStaff(dto.getStaffDTO()));
//            account.setStaffId(staff.getStaffId());
//        } else {
//            throw new RuntimeException("Can not create account!!\nPlease try again.");
//        }
//        if(Strings.isEmpty(account.getCustomerId()))
//            account.setCustomerId(null);
//        account.setPassword(encryptPassword(account.getPassword()));
//        accountRepository.save(account);
        return dto;
    }

    @Override
    public UserDTO updateAccount(UserDTO dto) {
//        if(dto.getIs().equals(STAFF)){
//            StaffDTO staff = ActorMapper.INSTANCE.toStaff(dto.getActor());
//            staffService.updateStaff(staff);
//            return dto;
//        }
//        if(dto.getIs().equals(CUSTOMER)){
//            CustomerDTO customer = ActorMapper.INSTANCE.toCustomer(dto.getActor());
//            customerService.updateCustomer(customer);
//            return dto;
//        }
        throw new RuntimeException("Cannot update profile!!");
    }

    @Override
    public UserDTO login(UserDTO dto) {
        String passEncrypted = encryptPassword(dto.getPassword());
        Account account = accountRepository.findFirstByUsernameAndPassword(dto.getUserName(), passEncrypted);
        if (Objects.isNull(account)) {
//            throw new BusinessException("","Username or password Incorrect");
            throw new RuntimeException("Username or password Incorrect");
        }
        Lecturer lecturer = lecturerRepository.findByAccount(account);
        Student student = studentRepository.findByAccount(account);
        Employee employee = employeeRepository.findByAccount(account);
        if (Objects.nonNull(student)) {
            dto.setRole(STUDENT);
            dto.setAccount(MaintenanceMapper.INSTANCE.toAccountDTO(account));
            dto.setProfile(MaintenanceMapper.INSTANCE.toProfileDTO(student.getProfile()));
            dto.setAClass(MaintenanceMapper.INSTANCE.toMyClassDTO(student.getAClass()));
        } else if (Objects.nonNull(employee)) {
            dto.setRole(EMPLOYEE);
            dto.setAccount(MaintenanceMapper.INSTANCE.toAccountDTO(account));
            dto.setProfile(MaintenanceMapper.INSTANCE.toProfileDTO(employee.getProfile()));
        } else if (Objects.nonNull(lecturer)) {
            dto.setRole(LECTURER);
            dto.setAccount(MaintenanceMapper.INSTANCE.toAccountDTO(account));
            dto.setProfile(MaintenanceMapper.INSTANCE.toProfileDTO(lecturer.getProfile()));
        } else {
            throw new RuntimeException("Account invalid!!!");
        }
//        dto.setRoleDTO(roleService.getRoleById(account.getRoleId()));
        //TODO set token for user
        return dto;
    }


    private String encryptPassword(String rawPW){
//        String result = rawPW;
//        try{
//            result = SecurityStorage.toHexString(rawPW);
//        } catch (NoSuchAlgorithmException e){
//            throw new RuntimeException("Cannot encrypt Password!!");
//        }
//        return result;
        return  rawPW;
    }
}
