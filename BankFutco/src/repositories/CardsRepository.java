package repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Cards;

public class CardsRepository {
    private final List<Cards> storage = new ArrayList<>();

    public CardsRepository() {
        initData();
    }

    private void initData() {
        // registros de ejemplo
        storage.add(new Cards("4000000000000001", "Credit", new BigDecimal("5000.00"), new BigDecimal("1200.00"), new BigDecimal("3800.00")));
        storage.add(new Cards("4000000000000002", "Debit", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        storage.add(new Cards("4000000000000003", "Credit", new BigDecimal("10000.00"), new BigDecimal("2500.00"), new BigDecimal("7500.00")));
        storage.add(new Cards("4000000000000004", "Credit", new BigDecimal("3000.00"), new BigDecimal("500.00"), new BigDecimal("2500.00")));
        storage.add(new Cards("4000000000000005", "Debit", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        storage.add(new Cards("4000000000000006", "Credit", new BigDecimal("7500.00"), new BigDecimal("1500.00"), new BigDecimal("6000.00")));
        storage.add(new Cards("4000000000000007", "Credit", new BigDecimal("2000.00"), new BigDecimal("200.00"), new BigDecimal("1800.00")));
        storage.add(new Cards("4000000000000008", "Debit", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        storage.add(new Cards("4000000000000009", "Credit", new BigDecimal("15000.00"), new BigDecimal("5000.00"), new BigDecimal("10000.00")));
        storage.add(new Cards("4000000000000010", "Credit", new BigDecimal("2500.00"), new BigDecimal("250.00"), new BigDecimal("2250.00")));
    }

    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
    }

    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return storage.stream()
                      .filter(c -> cardNumber.equals(c.getCardNumber()))
                      .findFirst();
    }

    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String cardNumber) {
        return findById(cardNumber)
                .map(storage::remove)
                .orElse(false);
    }

    public boolean existsById(String cardNumber) {
        return storage.stream()
                      .anyMatch(c -> cardNumber != null && cardNumber.equals(c.getCardNumber()));
    }
}
