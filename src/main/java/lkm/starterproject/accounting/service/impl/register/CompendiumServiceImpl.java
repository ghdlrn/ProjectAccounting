package lkm.starterproject.accounting.service.impl.register;


import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.Compendium;
import lkm.starterproject.accounting.repository.register.CompendiumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompendiumServiceImpl {

    private final CompendiumRepository compendiumRepository;
    private final CompendiumMapper compendiumMapper;

    public CompendiumServiceImpl(CompendiumRepository compendiumRepository, CompendiumMapper compendiumMapper) {
        this.compendiumRepository = compendiumRepository;
        this.compendiumMapper = compendiumMapper;
    }

    @Transactional
    public CompendiumDto createCompendium(CompendiumDto compendiumDto) {
        Compendium compendium = compendiumMapper.toEntity(compendiumDto);
        compendium = compendiumRepository.save(compendium);
        return compendiumMapper.toDto(compendium);
    }

    @Transactional(readOnly = true)
    public List<CompendiumDto> getAllCompendiums() {
        return compendiumRepository.findAll().stream()
                .map(compendiumMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompendiumDto getCompendium(Long id) {
        return compendiumRepository.findById(id)
                .map(compendiumMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
    }

    @Transactional
    public CompendiumDto updateCompendium(Long id, CompendiumDto compendiumDto) {
        Compendium compendium = compendiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
        compendiumMapper.updateDto(compendiumDto, compendium);
        compendium = compendiumRepository.save(compendium);
        return compendiumMapper.toDto(compendium);
    }

    @Transactional
    public void deleteCompendium(Long id) {
        Compendium compendium = compendiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
        compendiumRepository.delete(compendium);
    }
}
