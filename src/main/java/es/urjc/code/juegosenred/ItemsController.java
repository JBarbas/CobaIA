package es.urjc.code.juegosenred;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

@RestController
@RequestMapping("/items")
public class ItemsController {

	static Map<Long, Item> items = new ConcurrentHashMap<>(); 
	static AtomicLong nextId = new AtomicLong(0);
	
	
	
	@GetMapping
	public static Collection<Item> items() {
		return items.values();
	}
	
	@GetMapping(value = "/{password}")
	public String pass() {
		return "HolAAAAAAAA";
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public static Item nuevoItem(@RequestBody Item item) {

		long id = nextId.incrementAndGet();
		item.setId(id);
		items.put(id, item);
		
		return item;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> actualizaItem(@PathVariable long id, @RequestBody Item itemActualizado) {

		Item savedItem = items.get(itemActualizado.getId());

		if (savedItem != null) {

			items.put(id, itemActualizado);

			return new ResponseEntity<>(itemActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id) {

		Item savedItem = items.get(id);

		if (savedItem != null) {
			return new ResponseEntity<>(savedItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public static ResponseEntity<Item> borraItem(@PathVariable long id) {

		Item savedItem = items.get(id);

		if (savedItem != null) {
			items.remove(savedItem.getId());
			return new ResponseEntity<>(savedItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
