package co.shop.api.services;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;
import co.shop.api.entities.Opinion;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IOpinionMapper;
import co.shop.api.interfaces.services.IOpinionService;
import co.shop.api.repositories.OpinionRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService implements IOpinionService {
    private final OpinionRepository _opinionRepository;
    private final IOpinionMapper _opinionMapper;
    private final CentralValidator _validator;

    public OpinionService(
            OpinionRepository opinionRepository,
            IOpinionMapper opinionMapper,
            CentralValidator validator
    ) {
        this._opinionRepository = opinionRepository;
        this._opinionMapper = opinionMapper;
        this._validator = validator;
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
        return _opinionMapper.toOpinionDto(getOpinionEntityById(id));
    }

    @Override
    public OpinionDto create(CreateOpinionDto createOpinionDto) {
       validate(createOpinionDto);
        var opinionEntity = _opinionRepository.save(_opinionMapper.fromCreateOpinionDtoToOpinionEntity(createOpinionDto));
        return _opinionMapper.toOpinionDto(opinionEntity);
    }

    @Override
    public OpinionDto update(Long id, UpdateOpinionDto updateOpinionDto) {
        validate(updateOpinionDto);
        var opinionEntity = getOpinionEntityById(id);
        var changedOpinionEntity = _opinionMapper.fromUpdateOpinionDtoToOpinionEntity(opinionEntity, updateOpinionDto);
        return  _opinionMapper.toOpinionDto(_opinionRepository.save(changedOpinionEntity));
    }

    @Override
    public OpinionDto delete(Long id) {
        var deleteOpinionEntity = getOpinionEntityById(id);
        _opinionRepository.delete(deleteOpinionEntity);
        return _opinionMapper.toOpinionDto(deleteOpinionEntity);
    }

    private void validate(Object obj){
        _validator.validate(obj);
    }

    private Opinion getOpinionEntityById(Long id){
        return _opinionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );
    }
}
