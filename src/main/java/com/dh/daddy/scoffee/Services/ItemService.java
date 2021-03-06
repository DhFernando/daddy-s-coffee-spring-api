package com.dh.daddy.scoffee.Services;

import com.dh.daddy.scoffee.Interfaces.IItemRepository;
import com.dh.daddy.scoffee.Models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {

    @Autowired
    private IItemRepository iItemRepository;

    public List<Item> getAllItems() { return  iItemRepository.findAll(); }
    public Item saveItem(Item item){ return iItemRepository.save(item); }
    public Item findItem(Integer id){ return iItemRepository.fetchItemById(id); }
    public void remove(Item item){ iItemRepository.delete(item); }

}
