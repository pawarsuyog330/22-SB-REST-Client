package com.ashokit.client.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ashokit.client.constants.AppConstants;
import com.ashokit.client.model.Hotel;
import com.ashokit.client.model.Room;

@Component
public class MyAppRunner implements ApplicationRunner {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Display Hotel End-point
		RequestEntity<Void> requestEntity = RequestEntity.get(AppConstants.HOTEL_GET_ENDPOINT, 1)
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<Hotel> re = restTemplate.exchange(requestEntity, Hotel.class);
		Hotel hotel = re.getBody();

		System.out.println(hotel);

		// Add Hotel End-point
		Hotel hotel1 = new Hotel();
		hotel1.setId(5);
		hotel1.setName("Savera Hotels");
		hotel1.setDescription("2 Star Hotel");
		hotel1.setActive(true);
		hotel1.setZipcode(422009);
		List<Room> lstRooms = new ArrayList<>();
		Room r1 = new Room();
		r1.setId(5001);
		r1.setDisplayName("Suite Room");
		r1.setPrice(2000.0);
		r1.setQuantity(5);

		Room r2 = new Room();
		r2.setId(5002);
		r2.setDisplayName("AC Room");
		r2.setPrice(3000.0);
		r2.setQuantity(10);

		lstRooms.add(r1);
		lstRooms.add(r2);

		hotel1.setRooms(lstRooms);

		RequestEntity<Hotel> reqEntity = RequestEntity.post(AppConstants.HOTEL_POST_ENDPOINT)
				.contentType(MediaType.APPLICATION_XML).body(hotel);
		ResponseEntity<String> re1 = restTemplate.exchange(reqEntity, String.class);
		System.out.println(re1.getBody());

		RequestEntity<Void> rEntity = RequestEntity.get(AppConstants.HOTEL_GET_ENDPOINT1, 1, 1001)
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<Room> rE = restTemplate.exchange(rEntity, Room.class);
		Room room = rE.getBody();
		System.out.println(room);

		// Delete Hotel Room End-point
		RequestEntity<Void> roomEntity = RequestEntity.delete(AppConstants.HOTEL_DELETE_ENDPOINT, 5002).build();

		ResponseEntity<Void> reseponseEn = restTemplate.exchange(roomEntity, Void.class);
		System.out.println(reseponseEn);

		// Delete Hotel End-point
		RequestEntity<Void> hotelEntity = RequestEntity.delete(AppConstants.HOTEL_DELETE_ENDPOINT1, 5).build();

		ResponseEntity<Void> reseponseEnt = restTemplate.exchange(hotelEntity, Void.class);
		System.out.println(reseponseEnt);

	}

}
