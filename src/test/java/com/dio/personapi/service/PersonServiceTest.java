package com.dio.personapi.service;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.dto.response.MessageResponseDTO;
import com.dio.personapi.entity.Person;
import com.dio.personapi.exception.PersonNotFoundException;
import com.dio.personapi.mapper.PersonMapper;
import com.dio.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dio.personapi.utils.PersonUtils.createFakeDTO;
import static com.dio.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest{
	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapper personMapper;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSuccessSavedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();

		when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO successMessage = personService.createPerson(personDTO);

		assertEquals("Person successfully created with ID: 1", successMessage.getMessage());
	}

	@Test
	void testGivenValidPersonIdThenReturnThisPerson() throws PersonNotFoundException {
		PersonDTO expectedPersonDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();
		expectedPersonDTO.setId(expectedSavedPerson.getId());

		when(personRepository.findById(expectedSavedPerson.getId())).thenReturn(Optional.of(expectedSavedPerson));
		PersonDTO personDTO = personService.findById(expectedSavedPerson.getId());

		assertEquals(expectedSavedPerson.getId(), personDTO.getId());
		assertEquals(expectedSavedPerson.getFirstName(), personDTO.getFirstName());
		assertEquals(expectedSavedPerson.getLastName(), personDTO.getLastName());
		assertEquals(expectedSavedPerson.getCpf(), personDTO.getCpf());
		assertEquals(expectedSavedPerson.getBirthDate().toString(), personDTO.getBirthDate());
	}

	@Test
	void testGivenInvalidPersonIdThenThrowException() {
		var invalidPersonId = 1L;
		when(personRepository.findById(invalidPersonId))
				.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.findById(invalidPersonId));
	}

	@Test
	void testGivenNoDataThenReturnAllPeopleRegistered() {
		List<Person> expectedRegisteredPeople = Collections.singletonList(createFakeEntity());
		PersonDTO personDTO = createFakeDTO();
		personDTO.setId(expectedRegisteredPeople.get(0).getId());

		when(personRepository.findAll()).thenReturn(expectedRegisteredPeople);

		List<PersonDTO> expectedPeopleDTOList = personService.listAll();

		assertFalse(expectedPeopleDTOList.isEmpty());
		assertEquals(expectedPeopleDTOList.get(0).getId(), personDTO.getId());
	}

	@Test
	void testGivenValidPersonIdAndUpdateInfoThenReturnSuccesOnUpdate() throws PersonNotFoundException {
		var updatedPersonId = 2L;

		PersonDTO updatePersonDTORequest = createFakeDTO();
		updatePersonDTORequest.setId(updatedPersonId);
		updatePersonDTORequest.setLastName("Sargeiro Gomes");

		Person expectedPersonToUpdate = createFakeEntity();
		expectedPersonToUpdate.setId(updatedPersonId);

		Person expectedPersonUpdated = createFakeEntity();
		expectedPersonUpdated.setId(updatedPersonId);
		expectedPersonToUpdate.setLastName(updatePersonDTORequest.getLastName());

		when(personRepository.findById(updatedPersonId)).thenReturn(Optional.of(expectedPersonUpdated));
		when(personRepository.save(any(Person.class))).thenReturn(expectedPersonUpdated);

		MessageResponseDTO successMessage = personService.updateById(updatedPersonId, updatePersonDTORequest);

		assertEquals("Person successfully updated with ID: 2", successMessage.getMessage());
	}

	@Test
	void testGivenInvalidPersonIdAndUpdateInfoThenThrowExceptionOnUpdate() throws PersonNotFoundException {
		var invalidPersonId = 1L;

		PersonDTO updatePersonDTORequest = createFakeDTO();
		updatePersonDTORequest.setId(invalidPersonId);
		updatePersonDTORequest.setLastName("Peleias updated");

		when(personRepository.findById(invalidPersonId))
				.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.updateById(invalidPersonId, updatePersonDTORequest));
	}

	@Test
	void testGivenValidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
		var deletedPersonId = 1L;
		Person expectedPersonToDelete = createFakeEntity();

		when(personRepository.findById(deletedPersonId)).thenReturn(Optional.of(expectedPersonToDelete));
		personService.delete(deletedPersonId);

		verify(personRepository, times(1)).deleteById(deletedPersonId);
	}

	@Test
	void testGivenInvalidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
		var invalidPersonId = 1L;

		when(personRepository.findById(invalidPersonId))
				.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.delete(invalidPersonId));
	}

}
