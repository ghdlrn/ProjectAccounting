package lkm.starterproject.accounting.service.impl.register;


import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CompendiumDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Compendium;
import lkm.starterproject.accounting.mapper.register.CompendiumMapper;
import lkm.starterproject.accounting.repository.register.AccountTitleRepository;
import lkm.starterproject.accounting.repository.register.CompendiumRepository;
import lkm.starterproject.accounting.service.register.CompendiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompendiumServiceImpl implements CompendiumService {

    private final CompendiumRepository compendiumRepository;
    private final AccountTitleRepository accountTitleRepository;
    private final CompendiumMapper compendiumMapper;

    @Override
    @Transactional
    public CompendiumDto createCompendium(Long accountTitleId, CompendiumDto compendiumDto) {
        if (compendiumDto.getContent() == null) {
            throw new IllegalArgumentException("Content는 null이 아니어야 합니다.");
        }

        AccountTitle accountTitle = accountTitleRepository.findById(accountTitleId)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle not found with id: " + accountTitleId));
        Compendium compendium = compendiumMapper.toEntity(compendiumDto);
        compendium.setAccountTitle(accountTitle);

        Long maxCashCode = compendiumRepository.findMaxCodeByAccountTitleId(accountTitleId);
        compendium.setCode(maxCashCode + 1);

        compendium = compendiumRepository.save(compendium);
        return compendiumMapper.toDto(compendium);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompendiumDto> getAllCompendiums(Long accountTitleId) {
        accountTitleRepository.findById(accountTitleId)
                .orElseThrow(() -> new EntityNotFoundException("AccountTitle 정보를 찾을 수 없음"));
        List<Compendium> compendiums = compendiumRepository.findByAccountTitleId(accountTitleId);
        return compendiumMapper.toDtoList(compendiums);
    }

    @Override
    @Transactional(readOnly = true)
    public CompendiumDto getCompendium(Long id) {
        Compendium compendium = compendiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
        return compendiumMapper.toDto(compendium);
    }

    @Override
    @Transactional
    public CompendiumDto updateCompendium(Long id, CompendiumDto compendiumDto) {
        Compendium compendium = compendiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
        compendiumMapper.updateDto(compendiumDto, compendium);
        compendium = compendiumRepository.save(compendium);
        return compendiumMapper.toDto(compendium);
    }

    @Override
    @Transactional
    public void deleteCompendium(Long id) {
        Compendium compendium = compendiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compendium 정보를 찾을 수 없음"));
        compendiumRepository.delete(compendium);
    }
}
