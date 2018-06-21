package br.com.challenge.javachallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.challenge.javachallenge.model.Phone;
import br.com.challenge.javachallenge.repository.PhoneRepository;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceTest {

	@InjectMocks
	@Spy
	private PhoneServiceImpl phoneService;

	@Mock
	private PhoneRepository phoneRepository;

	@Before
	public void inicializar() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveListPhones() throws Exception {
		String mensagemErro = "";
		
		List<Phone> phonesSaved = new ArrayList<Phone>();
		Phone p = new Phone();
		phonesSaved.add(p);
		
		try {
			phoneService.saveList(phonesSaved);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "");
	}
	
}
