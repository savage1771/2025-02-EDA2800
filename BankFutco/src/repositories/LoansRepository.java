package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Loans;

public class LoansRepository {
    private final List<Loans> storage = new ArrayList<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {

        storage.add(new Loans(LocalDate.of(2024, 1, 10), "Personal", new BigDecimal("5000.00"), new BigDecimal("500.00"), new BigDecimal("4500.00")));
        storage.add(new Loans(LocalDate.of(2024, 2, 5), "Vehicle", new BigDecimal("15000.00"), new BigDecimal("1500.00"), new BigDecimal("13500.00")));
        storage.add(new Loans(LocalDate.of(2024, 3, 1), "Home", new BigDecimal("120000.00"), new BigDecimal("6000.00"), new BigDecimal("114000.00")));
        storage.add(new Loans(LocalDate.of(2024, 4, 12), "Personal", new BigDecimal("2000.00"), new BigDecimal("200.00"), new BigDecimal("1800.00")));
        storage.add(new Loans(LocalDate.of(2024, 5, 20), "Vehicle", new BigDecimal("8000.00"), new BigDecimal("800.00"), new BigDecimal("7200.00")));
        storage.add(new Loans(LocalDate.of(2024, 6, 30), "Home", new BigDecimal("90000.00"), new BigDecimal("4500.00"), new BigDecimal("85500.00")));
        storage.add(new Loans(LocalDate.of(2024, 7, 15), "Personal", new BigDecimal("1200.00"), new BigDecimal("120.00"), new BigDecimal("1080.00")));
        storage.add(new Loans(LocalDate.of(2024, 8, 3), "Vehicle", new BigDecimal("10000.00"), new BigDecimal("1000.00"), new BigDecimal("9000.00")));
        storage.add(new Loans(LocalDate.of(2024, 9, 9), "Home", new BigDecimal("60000.00"), new BigDecimal("3000.00"), new BigDecimal("57000.00")));
        storage.add(new Loans(LocalDate.of(2024, 10, 1), "Personal", new BigDecimal("750.00"), new BigDecimal("75.00"), new BigDecimal("675.00")));
    }

    // Helper: intenta parsear distintos formatos comunes
    private Optional<LocalDate> parseDate(String str) {
        if (str == null) return Optional.empty();
        // Try ISO first
        try {
            return Optional.of(LocalDate.parse(str));
        } catch (DateTimeParseException ignored) {
        }
        // Try dd/MM/yyyy and d/M/yyyy
        DateTimeFormatter[] fmts = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        };
        for (DateTimeFormatter fmt : fmts) {
            try {
                return Optional.of(LocalDate.parse(str, fmt));
            } catch (DateTimeParseException ignored) {
            }
        }
        return Optional.empty();
    }

    // Overloads that accept String id (date)
    public Optional<Loans> findById(String id) {
        return parseDate(id).flatMap(this::findById);
    }

    public Optional<Loans> findById(LocalDate date) {
        if (date == null) return Optional.empty();
        return storage.stream().filter(l -> date.equals(l.getDate())).findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage);
    }

    public Loans save(Loans loan) {
        if (loan == null || loan.getDate() == null) {
            throw new IllegalArgumentException("Loan o fecha no pueden ser nulos");
        }
        storage.removeIf(l -> loan.getDate().equals(l.getDate()));
        storage.add(loan);
        return loan;
    }

    public Loans save(String dateStr, Loans loan) {
        LocalDate date = parseDate(dateStr).orElseThrow(() -> new IllegalArgumentException("Fecha inválida: " + dateStr));
        loan.setDate(date);
        return save(loan);
    }

    public boolean deleteById(LocalDate date) {
        return findById(date).map(storage::remove).orElse(false);
    }

    public boolean deleteById(String id) {
        return parseDate(id).map(this::deleteById).orElse(false);
    }

    public boolean existsById(LocalDate date) {
        return storage.stream().anyMatch(l -> date != null && date.equals(l.getDate()));
    }

    public boolean existsById(String id) {
        return parseDate(id).map(this::existsById).orElse(false);
    }
}
