package lkm.starterproject.accounting.repository.register;

public interface CompendiumRepositoryCustom {

    Long findMaxCashCodeByAccountTitleId(Long accountTitleId);
    Long findMaxReplacementCodeByAccountTitleId(Long accountTitleId);
}
