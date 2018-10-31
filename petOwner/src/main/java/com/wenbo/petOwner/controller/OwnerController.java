package com.wenbo.petOwner.controller;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.wenbo.petOwner.model.Owner;
import com.wenbo.petOwner.repository.OwnerRepository;
import com.wenbo.petOwner.vo.OwnerVo;


@RestController
@RequestMapping("/owner")
public class OwnerController {
	

    @Autowired
    OwnerRepository ownerRepository;

    // Get All Owners
    @GetMapping("/owners")
    @CrossOrigin(origins = "*") 
    public List<OwnerVo> getAllOwners() {
    	List<Owner> ownerList =  ownerRepository.findAll();
    	List<OwnerVo> ownerVoList = new ArrayList<OwnerVo>();
    	for (Owner owner : ownerList) {
    		OwnerVo ownerVo = new OwnerVo();
			BeanUtils.copyProperties(owner, ownerVo);
			ownerVo.setPet_id(owner.getId());
			ownerVoList.add(ownerVo);
    	}
    	
        return ownerVoList;
    }
    
    
}