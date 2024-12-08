package com.notes.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.notes.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.notes.entities.User;
import com.notes.DTOs.NotesDto;
import com.notes.DTOs.UserDto;
import com.notes.repositories.NotesRepo;
import com.notes.repositories.UserRepo;
import com.notes.services.NotesService;
@Service
public class NotesServiceImpl implements NotesService {
	
	@Autowired
	private NotesRepo notesRepo;
	
	@Autowired
	private UserRepo userRepo;


	//create
	@Override
	public NotesDto createNote(NotesDto notesDto,String userId) {
		User user=userRepo.findByEmail(userId);
		notesDto.setDate(new Date());
		
		Note note =DtoToNotes(notesDto);
		note.setUser(user);
		
		Note res=this.notesRepo.save(note);
		return this.NotesToDto(res);
	}

	//update
	@Override
	public NotesDto updateNote(NotesDto notesDto, Integer notesId) {
		Note note = this.notesRepo.findById(notesId).orElseThrow(()->new ResourceAccessException("not found"));
		notesDto.setDate(new Date());
		note.setTitle(notesDto.getTitle());
		note.setDate(notesDto.getDate());
		note.setDescription(notesDto.getDescription());
		Note res=this.notesRepo.save(note);
		return this.NotesToDto(res);
	}

	// delete
	@Override
	public void deleteNote(Integer notesId) {
		Note note =this.notesRepo.findById(notesId).orElseThrow();
		this.notesRepo.delete(note);
	}

	//get 
	@Override
	public NotesDto getNote(Integer notesId) {
		Note note =this.notesRepo.findById(notesId).orElseThrow();
		return this.NotesToDto(note);
	}
	//get by user
	public List<NotesDto> getNoteByUser(String userId) {
		User user=userRepo.findByEmail(userId);
		List<Note> notes = this.notesRepo.findByUser(user);
		System.out.println(user);
		List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
		return allNotes;
	}

	//get all
	@Override
	public List<NotesDto> getAllNote() {
		List<Note> notes = this.notesRepo.findAll();
		List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
		return allNotes;
	}
	
	public NotesDto NotesToDto(Note note) {
		NotesDto notesDto= new NotesDto();
		notesDto.setID(note.getId());
		notesDto.setTitle(note.getTitle());
		notesDto.setDate(note.getDate());
		notesDto.setDescription(note.getDescription());
		notesDto.setUserDto(this.UserToDto(note.getUser()));

		return notesDto;
	}

	public Note DtoToNotes(NotesDto notesDto ) {
		Note note = new Note();
		note.setTitle(notesDto.getTitle());
		note.setDate(notesDto.getDate());
		note.setDescription(notesDto.getDescription());
		return note;
	}
	
	public User DtoToUser(UserDto userDto ) {
		User user= new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto UserToDto(User user ) {
		UserDto userDto= new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
