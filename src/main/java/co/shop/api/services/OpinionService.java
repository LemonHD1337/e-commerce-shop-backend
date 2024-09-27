package co.shop.api.services;

import co.shop.api.dtos.opinionDto.CreateOpinionDto;
import co.shop.api.dtos.opinionDto.OpinionDto;
import co.shop.api.dtos.opinionDto.UpdateOpinionDto;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IOpinionMapper;
import co.shop.api.interfaces.services.IOpinionService;
import co.shop.api.repositories.OpinionRepository;
import co.shop.api.repositories.ProductRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService implements IOpinionService {
    private final OpinionRepository _opinionRepository;
    private final ProductRepository _productRepository;
    private final IOpinionMapper _opinionMapper;
    private final CentralValidator _validator;

    public OpinionService(
            OpinionRepository opinionRepository,
            IOpinionMapper opinionMapper,
            ProductRepository productRepository,
            CentralValidator validator
    ) {
        this._opinionRepository = opinionRepository;
        this._opinionMapper = opinionMapper;
        this._productRepository = productRepository;
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
        return _opinionRepository
                .findById(id)
                .map(_opinionMapper::toOpinionDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );
    }

    @Override
    public OpinionDto create(CreateOpinionDto createOpinionDto) {
        _validator.validate(createOpinionDto);

       var product = _productRepository
               .findById(createOpinionDto.getProductId())
               .orElseThrow(
                       () -> new ResourceNotFoundException("Product not found with id: " + createOpinionDto.getProductId())
               );

       createOpinionDto.setProduct(product);

        var opinionEntity = _opinionRepository.save(_opinionMapper.fromCreateOpinionDtoToOpinionEntity(createOpinionDto));

        return _opinionMapper.toOpinionDto(opinionEntity);
    }

    @Override
    public OpinionDto update(Long id, UpdateOpinionDto updateOpinionDto) {
        _validator.validate(updateOpinionDto);

        var opinionEntity = _opinionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Opinion not found with id: " + id)
                );

        Long productId = updateOpinionDto.getProductId();

        var product = _productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + productId)
                );

        opinionEntity.setProduct(product);
        opinionEntity.setComment(updateOpinionDto.getComment());
        opinionEntity.setRating(updateOpinionDto.getRating());

        var changedOpinionEntity = _opinionRepository.save(opinionEntity);

        return  _opinionMapper.toOpinionDto(changedOpinionEntity);
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
