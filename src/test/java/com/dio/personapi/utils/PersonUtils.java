package com.dio.personapi.utils;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {
	private static final String FIRST_NAME = "Lucas";
	private static final String LAST_NAME = "Sargeiro";
	private static final String CPF_NUMBER = "123.456.789-10";
	private static final long PERSON_ID = 1L;
	public static final LocalDate BIRTH_DATE = LocalDate.of(1998, 06, 10);

	public static PersonDTO createFakeDTO() {
		return PersonDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("10-06-1998")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}

	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}
}
