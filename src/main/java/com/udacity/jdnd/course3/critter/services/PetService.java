package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    public Pet getPetById(long petId) {
        return petRepository.getOne(petId);
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);
        List<Pet> pets = new ArrayList<>();
        pets.add(savedPet);
        customer.setPets(pets);
        customerRepository.save(customer);
        return pet;
    }
}
