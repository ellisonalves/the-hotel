package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.domain.repository.AccomodationRepository;

@Repository
public class AccommodationJpaRepository implements AccomodationRepository {

	private final AccommodationSpringJpaRepository repository;

	public AccommodationJpaRepository(AccommodationSpringJpaRepository roomSpringJpaRepository) {
		this.repository = roomSpringJpaRepository;
	}

	@Override
	public Accommodation persist(Accommodation room) {
		return repository.save(room);
	}

	@Override
	public Optional<Accommodation> findById(UUID id) {
		return repository.findById(id);
	}

	@Override
	public void deleteByDoorNumber(String doorNumber) {
		repository.deleteByDoorNumber(doorNumber);
	}

	@Override
	public List<Accommodation> findAll() {
		return repository.findAll().stream().toList();
	}

	@Override
	public Optional<Accommodation> findByDoorNumber(String doorNumber) {
		return repository.findByDoorNumber(doorNumber);
	}

}

interface AccommodationSpringJpaRepository extends JpaRepository<Accommodation, UUID> {

	Optional<Accommodation> findByDoorNumber(String doorNumber);

	void deleteByDoorNumber(String doorNumber);
}
