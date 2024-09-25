package co.shop.api.services;

import co.shop.api.dtos.CreateOpinionDto;
import co.shop.api.dtos.OpinionDto;
import co.shop.api.dtos.UpdateOpinionDto;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.IOpinionMapper;
import co.shop.api.interfaces.IOpinionService;
import co.shop.api.repositories.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService implements IOpinionService {
    private final OpinionRepository _opinionRepository;
    private final IOpinionMapper _opinionMapper;

    public OpinionService(OpinionRepository opinionRepository, IOpinionMapper opinionMapper) {
        this._opinionRepository = opinionRepository;
        this._opinionMapper = opinionMapper;
    }

    @Override
    public List<OpinionDto> getAll() {
        return _opinionRepository
                .findAll()
                .stream()
                .map(_opinionMapper::toOpinionDto)
                .toList();
    }

    @Override
    public OpinionDto getById(Long id) {
        return _opinionRepository
                .findById(id)
                .map(_opinionMapper::toOpinionDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );
    }

    @Override
    public OpinionDto create(CreateOpinionDto createOpinionDto) {
        var opinionEntity = _opinionMapper.fromCreateOpinionDtoToOpinionEntity(createOpinionDto);

        _opinionRepository.save(opinionEntity);

        return _opinionMapper.toOpinionDto(opinionEntity);
    }

    @Override
    public OpinionDto update(Long id, UpdateOpinionDto updateOpinionDto) {
        var opinionEntity = _opinionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );

        var changedOpinionEntity = _opinionMapper.fromUpdateOpinionDtoToOpinionEntity(opinionEntity, updateOpinionDto);

        _opinionRepository.save(changedOpinionEntity);

        return _opinionMapper.toOpinionDto(changedOpinionEntity);
    }

    @Override
    public OpinionDto delete(Long id) {
        var deleteOpinionEntity = _opinionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );

        _opinionRepository.delete(deleteOpinionEntity);

        return _opinionMapper.toOpinionDto(deleteOpinionEntity);
    }
}
