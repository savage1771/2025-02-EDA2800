package services;

import java.util.List;
import java.util.Optional;

import model.Cards;
import repositories.CardsRepository;

public class CardsService implements ICardsService {
    private final CardsRepository repository;

    public CardsService() {
        this.repository = new CardsRepository();
    }

    // Constructor para inyección (útil en tests)
    public CardsService(CardsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cards save(Cards card) {
        return repository.save(card);
    }

    @Override
    public Optional<Cards> findById(String cardNumber) {
        return repository.findById(cardNumber);
    }

    @Override
    public List<Cards> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(String cardNumber) {
        return repository.deleteById(cardNumber);
    }

    public boolean existsById(String id){
        return repository.existsById(id);
    }
}
