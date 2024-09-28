package co.shop.api.controllers;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;
import co.shop.api.interfaces.services.IOpinionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<OpinionDto> create(@RequestBody CreateOpinionDto createOpinionDto, HttpServletRequest request){
        var createdOpinion = _opinionService.create(createOpinionDto);

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}").buildAndExpand(createdOpinion.getId()).toUri();

        return ResponseEntity.created(location).body(createdOpinion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpinionDto> update(@PathVariable long id, @RequestBody UpdateOpinionDto updateOpinionDto){
        return ResponseEntity.ok(_opinionService.update(id, updateOpinionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        _opinionService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
