package com.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1")
public class ShipwreckController {
	
	@Autowired
    private ShipwreckRepository shipwreckRepository;
	
	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list() {
		System.out.println(shipwreckRepository.count());
		for (Shipwreck s: shipwreckRepository.findAll())
		{
			System.out.println("printing Shipwrecks");
			System.out.println(s.getId());
		}
				
        return shipwreckRepository.findAll();
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
    	System.out.println("==========POST============");
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable("id") long id) {
    	System.out.println("Getting your record");
    	System.out.println(id);
    	System.out.println(shipwreckRepository.findById(id));
        return shipwreckRepository.getOne(id);
    	
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable long id, @RequestBody Shipwreck shipwreck) {
    	System.out.println("==========PUT============");
        Shipwreck existingShipwreck = shipwreckRepository.getOne(id);
       
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable long id) {
    	
        Shipwreck existingShipwreck = shipwreckRepository.getOne(id);
    	shipwreckRepository.delete(existingShipwreck);
    	//shipwreckRepository.deleteById(id);
        return existingShipwreck;
    }
}
