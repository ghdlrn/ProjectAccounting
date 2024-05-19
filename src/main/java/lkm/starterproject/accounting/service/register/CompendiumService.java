package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CompendiumDto;

import java.util.List;

public interface CompendiumService {

    CompendiumDto createCompendium(Long accountTitleId, CompendiumDto compendiumDto);

    List<CompendiumDto> getAllCompendiums(Long accountTitleId);

    CompendiumDto getCompendium(Long id);

    CompendiumDto updateCompendium(Long id, CompendiumDto compendiumDto);

    void deleteCompendium(Long id);
}
