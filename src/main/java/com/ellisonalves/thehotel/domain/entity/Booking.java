package com.ellisonalves.thehotel.domain.entity;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
public class Booking extends BaseEntity<UUID> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	private Guest guest;

	@ManyToOne
	private Accommodation room;

	@Temporal(TemporalType.TIMESTAMP)
	private Instant startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Instant endDate;
	
	@Version
	private Long version;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Accommodation getRoom() {
		return room;
	}

	public void setRoom(Accommodation room) {
		this.room = room;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}

	@PrePersist
	public void prePersist() {
		super.setCreatedAt(Instant.now(Clock.systemUTC()));
	}

	@Override
	public boolean equalTo(Object o) {
		Booking other = (Booking) o;
		return Objects.equals(id, other.getId())
				&& Objects.equals(room, other.room)
				&& Objects.equals(guest, other.guest)
				&& Objects.equals(startDate, other.startDate)
				&& Objects.equals(endDate, other.endDate)
				;
	}

	@Override
	public int hashCodePrime() {
		return 31;
	}

}
