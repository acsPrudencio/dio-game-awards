package me.dio.gameawards.domain.controller.games;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.gameawards.domain.controller.BaseRestController;
import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.domain.service.impl.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController extends BaseRestController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "Retorna todos os jogos cadastrados!")
    @GetMapping()
    public ResponseEntity<List<Game>> findAll(){
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Retorna o jogo com o id informado!")
    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id){
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Cadastra um novo jogo!")
    @PostMapping()
    public ResponseEntity<Game> insert(@RequestBody Game game){
        gameService.insert(game);
        return ResponseEntity.ok(game);
    }
    @Operation(summary = "Atualiza um jogo com base no id informado!")
    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game){
        gameService.update(id, game);
        return ResponseEntity.ok(game);
    }
    @Operation(summary = "Vota em um jogo com base em seu id")
    @PatchMapping("/{id}/vote")
    public ResponseEntity<Game> vote(@PathVariable Long id){
        gameService.vote(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleta um jogo com base no id informado!")
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> delete(@PathVariable Long id){
        gameService.delete(id);
        return ResponseEntity.ok().build();
    }
}
