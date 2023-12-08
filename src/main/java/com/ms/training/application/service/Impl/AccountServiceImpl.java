package com.ms.training.application.service.Impl;

import com.ms.training.application.dto.*;
import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.service.AccountService;
import com.ms.training.domain.entities.Account;
import com.ms.training.domain.mappers.AccountMapper;
import com.ms.training.domain.utils.SecurityStorage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    AccountRepository accountRepository;

    private static final String STAFF = "staff";

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
//        Account account = accountRepository.findByUsernameAndPassword(dto.getUserName(), passEncrypted);
//        if(Objects.isNull(account)){
////            throw new BusinessException("","Username or password Incorrect");
//            throw new RuntimeException("Username or password Incorrect");
//        }
//        if(Strings.isNotEmpty(account.getCustomerId())) {
//            Actor actorData = ActorMapper.INSTANCE.toActor(customerService.getCustomerById(account.getCustomerId()));
//            dto.setActor(actorData);
//            dto.setIs(CUSTOMER);
//        }
//        else if(Strings.isNotEmpty(account.getStaffId())){
//            dto.setActor(ActorMapper.INSTANCE.toActor(staffService.getStaffById(account.getStaffId())));
//            dto.setIs(STAFF);
//            dto.setIsAdmin(true);
//        }
//        else {
//            throw new RuntimeException("Account invalid!!!");
//        }
//        dto.setRoleDTO(roleService.getRoleById(account.getRoleId()));
        //TODO set token for user
        if (dto.getPassword().equals("123"))
            return dto;
        else {
            throw new RuntimeException("Account invalid!!!");
        }
    }


    private String encryptPassword(String rawPW){
        String result = rawPW;
        try{
            result = SecurityStorage.toHexString(rawPW);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Cannot encrypt Password!!");
        }
        return result;
    }
}
