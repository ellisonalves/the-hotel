package com.ellisonalves.thehotel.domain.entity;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
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
	private Room room;

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
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
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCodePrime() {
		return 31;
	}

	@Transient
	public boolean isMissingMandatoryFields() {
		return !(hasGuestId() || hasRoomId() || hasStartDate() || hasEndDate());
	}

	@Transient
	public boolean isStartOrEndDatesBefore(Instant instant) {
		return hasStartAndEndDates() && startDate.isBefore(instant) || endDate.isBefore(instant);
	}

	@Transient
	public boolean isStartDateAfterEndDate() {
		return hasStartAndEndDates() && startDate.isAfter(endDate);
	}

	private boolean hasEndDate() {
		return endDate != null;
	}

	private boolean hasStartDate() {
		return startDate != null;
	}

	private boolean hasRoomId() {
		return room != null && room.getId() != null;
	}

	private boolean hasGuestId() {
		return guest != null && guest.getId() != null;
	}

	private boolean hasStartAndEndDates() {
		return hasStartDate() && hasEndDate();
	}

}
