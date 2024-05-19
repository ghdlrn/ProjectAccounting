package lkm.starterproject.accounting.service;

import lkm.starterproject.accounting.dto.register.FinanceDto;

import java.util.List;

public interface CSVService {

    void init();

    void saveLocalTaxData();

    void saveTaxOfficeData();

    void saveAccountTitleData();
}
