package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Balance;

public class BalanceRepository {
	private final List<Balance> storage = new ArrayList<>();

	public BalanceRepository() {
		initData();
	}


	private void initData() {
		storage.add(new Balance(LocalDate.of(2025, 1, 1), "Apertura año", new BigDecimal("1000.00"), BigDecimal.ZERO, new BigDecimal("1000.00")));
		storage.add(new Balance(LocalDate.of(2025, 1, 3), "Depósito nómina", new BigDecimal("200.00"), BigDecimal.ZERO, new BigDecimal("1200.00")));
		storage.add(new Balance(LocalDate.of(2025, 1, 5), "Depósito", new BigDecimal("150.00"), BigDecimal.ZERO, new BigDecimal("1350.00")));
		storage.add(new Balance(LocalDate.of(2025, 1, 8), "Retiro cajero", BigDecimal.ZERO, new BigDecimal("50.00"), new BigDecimal("1300.00")));
		storage.add(new Balance(LocalDate.of(2025, 1, 15), "Pago servicio", BigDecimal.ZERO, new BigDecimal("100.00"), new BigDecimal("1200.00")));
		storage.add(new Balance(LocalDate.of(2025, 2, 1), "Intereses", new BigDecimal("4.50"), BigDecimal.ZERO, new BigDecimal("1204.50")));
		storage.add(new Balance(LocalDate.of(2025, 2, 5), "Depósito transferencia", new BigDecimal("300.00"), BigDecimal.ZERO, new BigDecimal("1504.50")));
		storage.add(new Balance(LocalDate.of(2025, 2, 10), "Compra supermercado", BigDecimal.ZERO, new BigDecimal("75.00"), new BigDecimal("1429.50")));
		storage.add(new Balance(LocalDate.of(2025, 3, 1), "Salario", new BigDecimal("2000.00"), BigDecimal.ZERO, new BigDecimal("3429.50")));
		storage.add(new Balance(LocalDate.of(2025, 3, 15), "Gastos varios", BigDecimal.ZERO, new BigDecimal("200.00"), new BigDecimal("3229.50")));
		storage.add(new Balance(LocalDate.of(2025, 4, 1), "Transferencia recibida", new BigDecimal("500.00"), BigDecimal.ZERO, new BigDecimal("3729.50")));
		storage.add(new Balance(LocalDate.of(2025, 4, 20), "Pago factura", BigDecimal.ZERO, new BigDecimal("120.00"), new BigDecimal("3609.50")));
		storage.add(new Balance(LocalDate.of(2025, 5, 5), "Depósito", new BigDecimal("250.00"), BigDecimal.ZERO, new BigDecimal("3859.50")));
		storage.add(new Balance(LocalDate.of(2025, 5, 20), "Retiro ATM", BigDecimal.ZERO, new BigDecimal("300.00"), new BigDecimal("3559.50")));
		storage.add(new Balance(LocalDate.of(2025, 6, 1), "Comisión", BigDecimal.ZERO, new BigDecimal("10.00"), new BigDecimal("3549.50")));
		storage.add(new Balance(LocalDate.of(2025, 6, 15), "Depósito cliente", new BigDecimal("100.00"), BigDecimal.ZERO, new BigDecimal("3649.50")));
		storage.add(new Balance(LocalDate.of(2025, 7, 1), "Bonificación", new BigDecimal("50.00"), BigDecimal.ZERO, new BigDecimal("3699.50")));
		storage.add(new Balance(LocalDate.of(2025, 7, 10), "Pago arriendo", BigDecimal.ZERO, new BigDecimal("700.00"), new BigDecimal("2999.50")));
		storage.add(new Balance(LocalDate.of(2025, 8, 1), "Intereses", new BigDecimal("3.20"), BigDecimal.ZERO, new BigDecimal("3002.70")));
		storage.add(new Balance(LocalDate.of(2025, 9, 1), "Ajuste cierre", BigDecimal.ZERO, new BigDecimal("2.70"), new BigDecimal("3000.00")));
	}

	public Balance save(Balance balance) {
		if (balance == null || balance.getDate() == null) {
			throw new IllegalArgumentException("Balance o date no puede ser null");
		}
		storage.removeIf(b -> balance.getDate().equals(b.getDate()));
		storage.add(balance);
		return balance;
	}

	public Optional<Balance> findById(LocalDate date) {
		if (date == null) return Optional.empty();
		return storage.stream().filter(b -> date.equals(b.getDate())).findFirst();
	}

	public List<Balance> findAll() {
		return new ArrayList<>(storage);
	}

	public boolean deleteById(LocalDate date) {
		return findById(date).map(storage::remove).orElse(false);
	}

	public boolean existsById(LocalDate date) {
		return storage.stream().anyMatch(b -> date != null && date.equals(b.getDate()));
	}
}
