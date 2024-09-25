package co.shop.api.controllers;

import co.shop.api.dtos.CreateOpinionDto;
import co.shop.api.dtos.OpinionDto;
import co.shop.api.dtos.UpdateOpinionDto;
import co.shop.api.interfaces.IOpinionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/opinion")
public class OpinionController {

    private final IOpinionService _opinionService;

    public OpinionController(IOpinionService opinionService) {
        this._opinionService = opinionService;
    }

    @GetMapping
    public ResponseEntity<List<OpinionDto>> getAll(){
        return ResponseEntity.ok(_opinionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpinionDto> getById(@PathVariable long id){
        return ResponseEntity.ok(_opinionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OpinionDto> create(@RequestBody CreateOpinionDto createOpinionDto){
        return ResponseEntity.ok(_opinionService.create(createOpinionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpinionDto> update(@PathVariable long id, @RequestBody UpdateOpinionDto updateOpinionDto){
        return ResponseEntity.ok(_opinionService.update(id, updateOpinionDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        return ResponseEntity.noContent().build();
    }

}
