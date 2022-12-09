package me.dio.gameawards.domain.service;

import lombok.Data;
import lombok.Getter;
import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.domain.repository.GameRepository;
import me.dio.gameawards.domain.service.exception.BusinessException;
import me.dio.gameawards.domain.service.exception.NoContentException;
import me.dio.gameawards.domain.service.impl.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(()-> new NoContentException("Not found the game with id: "+ id));
    }

    @Override
    public void insert(Game game) {
        if (Objects.nonNull(game.getId())){
            throw new BusinessException("The game id can be NULL");
        }
        gameRepository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        game.setId(id);
        if (!game.getId().equals(gameDb.getId())){
            throw new BusinessException("The given id does not belong to this game!");
        }
        gameRepository.save(game);
    }

    @Override
    public void delete(Long id) {
        if (Objects.isNull(id)){
            throw new BusinessException("The game ID can't be NULL");
        } else {
            Game game = findById(id);
            if (Objects.nonNull(game)){
                gameRepository.deleteById(id);
            }
        }
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.votar();
        update(id, gameDb);
    }
}
