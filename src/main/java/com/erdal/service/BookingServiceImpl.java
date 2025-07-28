package com.erdal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.erdal.DTO.BookingDTO;
import com.erdal.DTO.SaloonDTO;
import com.erdal.DTO.ServiceOfferingDTO;
import com.erdal.DTO.UserDTO;
import com.erdal.exeptions.BookingBadRequestExeption;
import com.erdal.exeptions.BookingErrorMessages;
import com.erdal.exeptions.BookingNotFoundExeption;
import com.erdal.mapper.BookingMapper;
import com.erdal.model.Booking;
import com.erdal.model.SaloonReport;
import com.erdal.repository.BookingRepository;
import com.erdal.requests.BookingRequest;
import com.erdal.status.BookingStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;

	@Override
	public BookingDTO createBooking(BookingRequest bookingRequest, UserDTO userDTO, SaloonDTO saloonDTO,
			Set<ServiceOfferingDTO> serviceOfferingDTOset) {

		int totalDuration = serviceOfferingDTOset.stream().mapToInt(serviceDTO -> serviceDTO.getDuration()).sum();

		LocalDateTime bookingStartTime = bookingRequest.getStartTime();

		LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

		Boolean isSlotAvailable = isTimeSlotAvailable(saloonDTO, bookingStartTime, bookingEndTime);

		if (!isSlotAvailable) {

			throw new BookingBadRequestExeption(BookingErrorMessages.TIME_NOT_AVAILABLE);

		}

		Integer totalPrice = serviceOfferingDTOset.stream()
				.mapToInt(serviceOfferingDTO -> serviceOfferingDTO.getPrice()).sum();

		Set<Long> listIds = serviceOfferingDTOset.stream().map(s -> s.getId()).collect(Collectors.toSet());

		Booking booking = new Booking();
		booking.setCostumerId(userDTO.getId());
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setEndTime(bookingEndTime);
		booking.setStartTime(bookingStartTime);
		booking.setServiceOfferinIds(listIds);
		booking.setSaloonId(saloonDTO.getId());
		booking.setTotalPrice(totalPrice);

		Booking newBooking = bookingRepository.save(booking);

		return BookingMapper.mapToBookingDTO(newBooking);

	}

	// -------this metod is related to createBooking
	public Boolean isTimeSlotAvailable(SaloonDTO saloonDTO, LocalDateTime bookingStartTime,
			LocalDateTime bookingCloseTime) {

		List<BookingDTO> existingBookingsDTOs = getBookingBySaloonId(saloonDTO.getId());
		
		List<Booking> existingBookings=BookingMapper.mapToDtoToBooking(existingBookingsDTOs);

		LocalDateTime saloonOpenTime = saloonDTO.getOpenTime().atTime(bookingStartTime.toLocalTime());
		LocalDateTime saloonCloseTime = saloonDTO.getCloseTime().atTime(bookingCloseTime.toLocalTime());

		if (bookingStartTime.isBefore(saloonOpenTime) || bookingCloseTime.isAfter(saloonCloseTime)) {
			throw new BookingBadRequestExeption(BookingErrorMessages.SALOON_IS_NOT_OPEN);
		}

		for (Booking existingBooking : existingBookings) {
			LocalDateTime existingBookingStratTime = existingBooking.getStartTime();
			LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

			if (bookingStartTime.isBefore(existingBookingEndTime)
					&& bookingCloseTime.isAfter(existingBookingStratTime)) {

				throw new BookingBadRequestExeption(BookingErrorMessages.TIME_NOT_AVAILABLE);

			}
			if ((bookingStartTime.isEqual(existingBookingEndTime) || bookingStartTime.isEqual(existingBookingStratTime))
					|| (bookingCloseTime.isEqual(existingBookingStratTime)
							|| bookingCloseTime.isEqual(existingBookingEndTime))) {

				throw new BookingBadRequestExeption(BookingErrorMessages.TIME_NOT_AVAILABLE);

			}

		}

		return true;

	}

	@Override
	public List<BookingDTO> getBookingsByBookingsCostemerId(Long customerId) {

		List<Booking> bookings = bookingRepository.findByCustomerId(customerId);

		return BookingMapper.mapAllListToBookingDTO(bookings);
	}

	@Override
	public List<BookingDTO> getBookingBySaloonId(Long saloonId) {

		List<Booking> bookings = bookingRepository.findBySaloonId(saloonId);
		return BookingMapper.mapAllListToBookingDTO(bookings);
	}

	@Override
	public Booking getBookingById(Long id) {

		return getByBookingId(id);
	}

	@Override
	public Booking upDateBooking(Long id, BookingStatus bookingStatus) {
		Booking booking = getBookingById(id);
		booking.setBookingStatus(bookingStatus);

		return bookingRepository.save(booking);
	}

	@Override
	public void deleteBookingById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BookingDTO> getBookingByDate(LocalDate localDate, Long saloonId) {
		List<BookingDTO> allBookings = getBookingBySaloonId(saloonId);
		if (localDate == null) {
			return allBookings;
		}

		List<BookingDTO> bookings = allBookings.stream().filter(
				booking -> isSameDate(booking.getStartTime(), localDate) || isSameDate(booking.getEndTime(), localDate))
				.collect(Collectors.toList());

		return bookings;

	}

    //--------- this metod blongs to get booking by date
	private boolean isSameDate(LocalDateTime dateTime, LocalDate localDate) {

		return dateTime.toLocalDate().isEqual(localDate);
	}

	@Override
	public SaloonReport getSaloonReport(Long saloonId) {
		// TODO Auto-generated method stub
		return null;
	}

	// ------ Metods belongs to this class
	private Booking getByBookingId(Long id) {

		return bookingRepository.findById(id)
				.orElseThrow(() -> new BookingNotFoundExeption(BookingErrorMessages.BOOKING_NOT_FOUND));
	}

}
