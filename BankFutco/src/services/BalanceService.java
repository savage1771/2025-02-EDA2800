package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import model.Balance;
import repositories.BalanceRepository;

public class BalanceService implements IBalanceService {
	private final BalanceRepository repository;

	public BalanceService() {
		this.repository = new BalanceRepository();
	}

	private LocalDate parseDate(String dateStr) {
		if (dateStr == null) return null;
		try {
			// Intenta formato ISO
			return LocalDate.parse(dateStr);
		} catch (DateTimeParseException e) {
			try {
				// Intenta formato dd/MM/yyyy
				return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException ex) {
				throw new IllegalArgumentException("Formato de fecha inválido: " + dateStr);
			}
		}
	}

	@Override
	public Balance save(Balance balance) {
		return repository.save(balance);
	}

	@Override
	public Optional<Balance> findById(String id) {
		LocalDate date = parseDate(id);
		return repository.findById(date);
	}

	@Override
	public List<Balance> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean deleteById(String id) {
		LocalDate date = parseDate(id);
		return repository.deleteById(date);
	}


	public boolean existsById(String id) {
		LocalDate date = parseDate(id);
		return repository.existsById(date);
	}
}
