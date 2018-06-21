package br.com.challenge.javachallenge.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.javachallenge.model.Phone;
import br.com.challenge.javachallenge.repository.PhoneRepository;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	PhoneRepository phoneRepository;

	@Override
	@Transactional
	public List<Phone> saveList(List<Phone> phones) {
		List<Phone> phonesSaved = new ArrayList<Phone>();

		if (phones != null) {
			for (Phone p : phones) {
				Phone phoneSaved = phoneRepository.save(p);
				phonesSaved.add(phoneSaved);
			}
		}
		
		return phonesSaved;
	}

}
