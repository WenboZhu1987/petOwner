package com.wenbo.petOwner.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenbo.petOwner.model.Owner;
import com.wenbo.petOwner.model.Pet;
import com.wenbo.petOwner.repository.OwnerRepository;
import com.wenbo.petOwner.repository.PetRepository;
import com.wenbo.petOwner.vo.PetOwnerVo;
import com.wenbo.petOwner.vo.PetVo;

@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	PetRepository petRepository;

	@Autowired
	OwnerRepository ownerRepository;

	// -------------------Retrieve All
	// Pets---------------------------------------------

	@GetMapping("/pets")
	@CrossOrigin(origins = "*")
	// TODO change me!
	public List<PetVo> getAllPets() {

		List<Pet> petList = petRepository.findAll();
		List<PetVo> petVoList = new ArrayList<PetVo>();
		for (Pet pet : petList) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			PetVo petVo = new PetVo();
			BeanUtils.copyProperties(pet, petVo);
			petVo.setOwner_id(pet.getOwner().getId());
			petVo.setBirthday(sdf.format(pet.getBirthday()));
			petVoList.add(petVo);
		}

		return petVoList;
	}

	// -------------------Create New
	// Pet---------------------------------------------

	@PostMapping("/pets")
	@CrossOrigin(origins = "*")
	public Pet createPet(@Valid @RequestBody PetOwnerVo petOwnerVo) {
		
		Pet newPet = null;

		if (!petOwnerVo.getBirthday().equals("") && !petOwnerVo.getCity().equals("")
				&& !petOwnerVo.getFirst_name().equals("")
				&& !petOwnerVo.getLast_name().equals("")
				&& !petOwnerVo.getName().equals("")) {
			
			System.out.println("***********"+petOwnerVo.getName());

			Owner owner = new Owner(petOwnerVo.getFirst_name(),
					petOwnerVo.getLast_name(), petOwnerVo.getCity());
			ownerRepository.save(owner);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = dateFormat.parse(petOwnerVo.getBirthday());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Pet pet = new Pet(petOwnerVo.getName(), date, owner);

			newPet = petRepository.save(pet);
			Owner newOwner = ownerRepository.getOne(pet.getOwner().getId());
			newOwner.setPet(newPet);
			ownerRepository.save(newOwner);
		}

		return newPet;

	}

}
