package com.ellisonalves.thehotel.application.usecases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
public class CreateBookingUseCaseTest {

	@InjectMocks
	private CreateBookingUseCase useCase;

	@Mock
	private BookingRepository repository;

	/*
	 * @Mock private CreateBookingMapper mapper;
	 * 
	 * @Test void shouldCreateBookingSuccessfully() { final var newBooking =
	 * createBookingRequest();
	 * 
	 * var booking = new Booking();
	 * 
	 * when(repository.findBookings(newBooking.roomId(), newBooking.from(),
	 * newBooking.until())) .thenReturn(Collections.emptyList());
	 * 
	 * when(mapper.toDomain(newBooking)).thenReturn(booking);
	 * 
	 * var actual = useCase.createBooking(newBooking);
	 * 
	 * assertEquals(Result.ok("Created!"), actual);
	 * 
	 * verify(repository, atMostOnce()).persist(any(Booking.class)); }
	 * 
	 * @Test void shouldNotBookWhenTheRoomIsBusy() { var newBooking =
	 * createBookingRequest();
	 * 
	 * var booking = new Booking();
	 * 
	 * when(repository.findBookings(newBooking.roomId(), newBooking.from(),
	 * newBooking.until())) .thenReturn(List.of(booking));
	 * 
	 * var actual = useCase.createBooking(newBooking);
	 * 
	 * assertEquals(Result.err("Booking not available"), actual);
	 * 
	 * verify(repository, never()).persist(booking); }
	 * 
	 * @Test void shouldNotAllowBookingsInThePast() { var yesterday =
	 * Instant.now().minus(1, ChronoUnit.DAYS); var lastWeek = yesterday.minus(5,
	 * ChronoUnit.DAYS);
	 * 
	 * var actual = useCase .createBooking(new CreateBooking(UUID.randomUUID(),
	 * UUID.randomUUID(), lastWeek, yesterday));
	 * 
	 * assertEquals(Result.err("Bookings in the past are not allowed"), actual);
	 * 
	 * verify(repository, never()).persist(any()); }
	 * 
	 * @Test void shouldNotAllowStartDateBeforeEndDate() { var today =
	 * Instant.now(Clock.systemUTC()).plus(1, ChronoUnit.SECONDS); // TODO extract
	 * and mock clock and remove this plus - should work without it var nextWeek =
	 * today.plus(5, ChronoUnit.DAYS);
	 * 
	 * var actual = useCase .createBooking(new CreateBooking(UUID.randomUUID(),
	 * UUID.randomUUID(), nextWeek, today));
	 * 
	 * assertEquals(Result.err("Start date MUST be before end date"), actual);
	 * 
	 * verify(repository, never()).persist(any()); }
	 * 
	 * @Test public void shouldNotAllowMissingWhenThereAreMissingProperties() { var
	 * actual = useCase.createBooking(new CreateBooking(null, null, null, null));
	 * 
	 * assertEquals(Result.err("Missing mandatory fields"), actual);
	 * 
	 * verify(repository, never()).persist(any()); }
	 * 
	 * private CreateBooking createBookingRequest() { return new CreateBooking(
	 * UUID.randomUUID(), UUID.randomUUID(), Instant.now().plus(1, ChronoUnit.DAYS),
	 * Instant.now().plus(3, ChronoUnit.DAYS)); }
	 */
}
